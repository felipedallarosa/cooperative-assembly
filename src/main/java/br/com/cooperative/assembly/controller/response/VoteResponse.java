package br.com.cooperative.assembly.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VoteResponse {

    private Long voteSessionId;

    private String document;

    private boolean decision;

}
