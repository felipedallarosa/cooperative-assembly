package br.com.cooperative.assembly.controller.adapter;

import static br.com.cooperative.assembly.converter.VoteConverter.toVoteDto;
import static br.com.cooperative.assembly.converter.VoteConverter.toVoteResponse;

import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.controller.response.VoteResponse;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.service.VoteService;

@Component
public class VoteAdapter {

    private VoteService voteService;

    public VoteAdapter(final VoteService voteService) {
        this.voteService = voteService;
    }

    public VoteResponse handleRequest(final Long votingSessionId, final VoteRequest voteRequest) {

        VoteDto voteDto = toVoteDto(votingSessionId, voteRequest);

        return toVoteResponse(voteService.registryVote(voteDto));
    }
}
