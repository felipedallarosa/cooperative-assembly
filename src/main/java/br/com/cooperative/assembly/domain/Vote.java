package br.com.cooperative.assembly.domain;

import static br.com.cooperative.assembly.domain.DecisionVote.NO;
import static br.com.cooperative.assembly.domain.DecisionVote.YES;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Table(name = "VOTE")
public class Vote {

    private static final String SEQUENCE_NAME = "VOTE_SEQ";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VOTE_SESSION_ID", foreignKey = @ForeignKey(name = "VOTE_VOTING_SESSION_FK"))
    @NotNull
    private VotingSession votingSession;

    @Column(name = "DOCUMENT")
    @NotNull
    private String document;

    @Column(name = "DECISION")
    @Enumerated(EnumType.STRING)
    private DecisionVote decision;

    public boolean isVoteYes(){ return YES.equals(this.decision); }

    public boolean isVoteNo(){
        return NO.equals(this.decision);
    }
}
