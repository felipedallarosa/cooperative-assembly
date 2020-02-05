package br.com.cooperative.assembly.controller.v2.adapter;

import static br.com.cooperative.assembly.converter.VoteConverter.toVoteDto;
import static br.com.cooperative.assembly.converter.VoteConverter.toVoteResponse;

import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.cache.service.VoteAsyncService;
import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.controller.v1.response.VoteResponse;
import br.com.cooperative.assembly.dto.VoteDto;
@Component
public class VoteAsyncAdapter {

    private VoteAsyncService voteAsyncService;

    public VoteAsyncAdapter(final VoteAsyncService voteAsyncService) {
        this.voteAsyncService = voteAsyncService;
    }

    public void handleRequest(final Long votingSessionId, final VoteRequest voteRequest) {

        VoteDto voteDto = toVoteDto(votingSessionId, voteRequest);

        voteAsyncService.executeSendRequest(voteDto);
    }

    public VoteResponse handleRequest(final Long votingSessionId, final String document) {

        return toVoteResponse(voteAsyncService.executeReceiveRequest(votingSessionId, document));

    }
}
