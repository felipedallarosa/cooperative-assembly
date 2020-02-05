package br.com.cooperative.assembly.dto;

import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.domain.VotingSessionResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VotingSessionResultDto {

    private VotingSession votingSession;
    private Long voteYes;
    private Long voteNo;
    private VotingSessionResultStatus status;

}
