package br.com.cooperative.assembly.dto;

import br.com.cooperative.assembly.domain.VotingSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class VotingSessionResultDto {

    private VotingSession votingSession;
    private Long voteYes;
    private Long voteNo;

}
