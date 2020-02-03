package br.com.cooperative.assembly.scheduler;

import static br.com.cooperative.assembly.config.CooperativeAssemblyConfiguration.EXCHANGE_NAME;
import static br.com.cooperative.assembly.config.VotingSessionRabbitResultConfiguration.ROUTING_KEY_NAME;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.controller.adapter.VotingSessionAdapter;
import br.com.cooperative.assembly.controller.response.VotingSessionResponse;
import br.com.cooperative.assembly.controller.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.domain.Vote;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.facede.message.VotingSessionProducer;
import br.com.cooperative.assembly.repository.VoteRepository;
import br.com.cooperative.assembly.service.MessageService;
import br.com.cooperative.assembly.service.UserInfoService;
import br.com.cooperative.assembly.service.VoteService;
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
    private VotingSessionProducer votingSessionProducer;

    @Before
    public void setUp() {
        votingSessionScheduler = new VotingSessionScheduler(votingSessionAdapter, votingSessionService, votingSessionProducer);
    }

    @Test

    public void verifyInvalidOpenedVotingSession() {
        // given
        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();
        VotingSession votingSession = VotingSession.builder().id(ONE).build();
        VotingSessionResultResponse response = VotingSessionResultResponse.builder()
                                                                            .votingSession(votingSession)
                                                                            .voteYes(ONE)
                                                                            .voteNo(ONE)
                                                                        .build();

        when(votingSessionService.findAllInvalidOpenedSessionAndClose()).thenReturn(Arrays.asList(votingSession));

        when(votingSessionAdapter.handleRequest(any(VotingSession.class))).thenReturn(response);

        // when
        votingSessionScheduler.verifyInvalidOpenedVotingSession();

        //then
        verify(votingSessionProducer).send(any());
    }

}