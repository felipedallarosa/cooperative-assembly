package br.com.cooperative.assembly.controller.adapter;

import static br.com.cooperative.assembly.converter.VotingSessionConverter.toVotingSessionResponse;
import static br.com.cooperative.assembly.converter.VotingSessionResultConverter.toVotingSessionResultResponse;

import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.controller.request.VotingSessionRequest;
import br.com.cooperative.assembly.controller.response.VotingSessionResponse;
import br.com.cooperative.assembly.controller.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.converter.VotingSessionConverter;
import br.com.cooperative.assembly.dto.VotingSessionDto;
import br.com.cooperative.assembly.service.VotingSessionResultService;
import br.com.cooperative.assembly.service.VotingSessionService;

@Component
public class VotingSessionAdapter {

    private VotingSessionService votingSessionService;

    private VotingSessionResultService votingSessionResultService;

    private VotingSessionConverter votingSessionConverter;

    public VotingSessionAdapter(final VotingSessionService votingSessionService,
        final VotingSessionResultService votingSessionResultService,
        final VotingSessionConverter votingSessionConverter) {
        this.votingSessionService = votingSessionService;
        this.votingSessionResultService = votingSessionResultService;
        this.votingSessionConverter = votingSessionConverter;
    }

    public VotingSessionResponse handleRequest(final Long agendaId, final VotingSessionRequest votingSessionRequest) {

        VotingSessionDto votingSessionDto = votingSessionConverter.toVotingSessionDto(agendaId, votingSessionRequest);

        return toVotingSessionResponse(votingSessionService.openVotingSession(votingSessionDto));
    }

    public VotingSessionResultResponse handleRequest(final Long votingSessionId) {

        return toVotingSessionResultResponse(votingSessionResultService.generateResult(votingSessionId));
    }

}
