package br.com.cooperative.assembly.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "VOTING_SESSION")
public class VotingSession {

    private static final String SEQUENCE_NAME = "VOTING_SESSION_SEQ";

    @Id
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AGENDA_ID", foreignKey = @ForeignKey(name = "VOTING_SESSION_AGENDA_FK"))
    @NotNull
    private Agenda agenda;

    @Column(name = "FINISH_VOTING_SESSION")
    @NotNull
    private LocalDateTime finishVotingSession;

    @Column(name = "OPENED")
    @NotNull
    private boolean opened;

    public boolean shouldClose() {
        return this.getFinishVotingSession().isBefore(LocalDateTime.now());
    }
}
