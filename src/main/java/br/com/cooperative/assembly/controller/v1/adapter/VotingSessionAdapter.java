package br.com.cooperative.assembly.controller.v1.adapter;

import static br.com.cooperative.assembly.converter.VotingSessionConverter.toVotingSessionResponse;
import static br.com.cooperative.assembly.converter.VotingSessionResultConverter.toVotingSessionResultResponse;

import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.controller.v1.response.VotingSessionResponse;
import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.converter.VotingSessionConverter;
import br.com.cooperative.assembly.domain.VotingSession;
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

    public VotingSessionResponse handleRequest(final Long agendaId, final Long timeVotingSession) {

        VotingSessionDto votingSessionDto = votingSessionConverter.toVotingSessionDto(agendaId, timeVotingSession);

        return toVotingSessionResponse(votingSessionService.openVotingSession(votingSessionDto));
    }

    public VotingSessionResultResponse handleRequest(final VotingSession votingSession) {

        return toVotingSessionResultResponse(votingSessionResultService.generateResult(votingSession));
    }

    public VotingSessionResultResponse handleRequest(final Long votingSessionId) {

        return toVotingSessionResultResponse(votingSessionResultService.generateResultById(votingSessionId));
    }
}
