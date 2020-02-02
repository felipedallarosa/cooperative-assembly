package br.com.cooperative.assembly.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.cooperative.assembly.controller.adapter.VotingSessionAdapter;
import br.com.cooperative.assembly.controller.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.facede.message.VotingSessionProducer;
import br.com.cooperative.assembly.service.VotingSessionService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VotingSessionScheduler  {

    private VotingSessionAdapter votingSessionAdapter;

    private VotingSessionService votingSessionService;

    private VotingSessionProducer votingSessionProducer;

    public VotingSessionScheduler(final VotingSessionAdapter votingSessionAdapter,
        final VotingSessionService votingSessionService,
        final VotingSessionProducer votingSessionProducer) {
        this.votingSessionAdapter = votingSessionAdapter;
        this.votingSessionService = votingSessionService;
        this.votingSessionProducer = votingSessionProducer;
    }

    @Scheduled(cron = "1 * * * * *")
    public void verifyInvalidOpenedVotingSession() {
        log.warn("Starting Scheduling...");

        votingSessionService.findAllInvalidOpenedSessionAndClose()
            .forEach(votingSession -> {
                VotingSessionResultResponse result = votingSessionAdapter.handleRequest(votingSession);
                log.warn("Sending Voting Session Result: " + result);
                votingSessionProducer.send(result.toString());
            });

        log.warn("Scheduling finished...");
    }
}
