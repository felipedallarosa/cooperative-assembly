package br.com.cooperative.assembly.controller.v1.response;

import br.com.cooperative.assembly.domain.VotingSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VotingSessionResultResponse {

    private VotingSession votingSession;
    private Long voteYes;
    private Long voteNo;

}
