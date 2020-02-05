package br.com.cooperative.assembly.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.cooperative.assembly.controller.v1.adapter.VotingSessionAdapter;
import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.facede.message.producer.VotingSessionResultProducer;
import br.com.cooperative.assembly.service.VotingSessionService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VotingSessionScheduler  {

    private VotingSessionAdapter votingSessionAdapter;

    private VotingSessionService votingSessionService;

    private VotingSessionResultProducer votingSessionResultProducer;

    public VotingSessionScheduler(final VotingSessionAdapter votingSessionAdapter,
        final VotingSessionService votingSessionService,
        final VotingSessionResultProducer votingSessionResultProducer) {
        this.votingSessionAdapter = votingSessionAdapter;
        this.votingSessionService = votingSessionService;
        this.votingSessionResultProducer = votingSessionResultProducer;
    }

    @Scheduled(cron = "1 * * * * *")
    public void verifyInvalidOpenedVotingSession() {
        log.warn("Starting Scheduling...");

        votingSessionService.findAllInvalidOpenedSessionAndClose()
            .forEach(votingSession -> {
                VotingSessionResultResponse result = votingSessionAdapter.handleRequest(votingSession);
                log.warn("Sending Voting Session Result: " + result);
                votingSessionResultProducer.send(result);
            });

        log.warn("Scheduling finished...");
    }
}
