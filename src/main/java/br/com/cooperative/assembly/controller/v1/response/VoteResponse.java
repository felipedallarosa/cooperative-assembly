package br.com.cooperative.assembly.controller.v1.response;

import br.com.cooperative.assembly.domain.DecisionVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VoteResponse {

    private Long id;

    private Long voteSessionId;

    private String document;

    private DecisionVote decision;

}
