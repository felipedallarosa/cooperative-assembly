package br.com.cooperative.assembly.scheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.controller.v1.adapter.VotingSessionAdapter;
import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.facede.message.producer.VotingSessionResultProducer;
import br.com.cooperative.assembly.service.VotingSessionService;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingSessionSchedulerTest {

    private static final String DOCUMENT = "83714006087";
    private static final Long ONE = 1L;

    private VotingSessionScheduler votingSessionScheduler;

    @Mock
    private VotingSessionAdapter votingSessionAdapter;

    @Mock
    private VotingSessionService votingSessionService;

    @Mock
    private VotingSessionResultProducer votingSessionResultProducer;

    @Before
    public void setUp() {
        votingSessionScheduler = new VotingSessionScheduler(votingSessionAdapter, votingSessionService,
            votingSessionResultProducer);
    }

    @Test
    public void verifyInvalidOpenedVotingSession() {

        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();
        VotingSession votingSession = VotingSession.builder().id(ONE).build();
        VotingSessionResultResponse response = VotingSessionResultResponse.builder()
                                                                            .votingSession(votingSession)
                                                                            .voteYes(ONE)
                                                                            .voteNo(ONE)
                                                                        .build();

        when(votingSessionService.findAllInvalidOpenedSessionAndClose()).thenReturn(Arrays.asList(votingSession));

        when(votingSessionAdapter.handleRequest(any(VotingSession.class))).thenReturn(response);

        votingSessionScheduler.verifyInvalidOpenedVotingSession();

        verify(votingSessionResultProducer).send(any());
    }

}