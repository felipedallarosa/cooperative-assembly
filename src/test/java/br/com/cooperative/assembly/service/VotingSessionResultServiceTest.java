package br.com.cooperative.assembly.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.domain.Vote;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VotingSessionResultDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.repository.VoteRepository;
import br.com.cooperative.assembly.repository.VotingSessionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingSessionResultServiceTest {

    private static final String DOCUMENT = "83714006087";
    private static final Long ONE = 1L;
    private static final Long ZERO = 0L;

    private VotingSessionResultService votingSessionResultService;

    @Mock
    private MessageService messageService;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private VoteRepository voteRepository;

    @Before
    public void setUp() {
        votingSessionResultService = new VotingSessionResultService(messageService, votingSessionRepository, voteRepository);
    }

    @Test
    public void shouldOpenVotingSessionSucessuful(){

        Agenda agenda = Agenda.builder().id(ONE).build();
        VotingSession votingSessionDb = VotingSession.builder().id(ONE).agenda(agenda)
            .opened(true).finishVotingSession(LocalDateTime.now().plusMinutes(10)).build();
        Vote voteDb = Vote.builder().id(ONE).decision(true).document(DOCUMENT).votingSession(votingSessionDb).build();

        when(votingSessionRepository.findById(ONE)).thenReturn(Optional.of(votingSessionDb));

        when(voteRepository.findByVotingSession(any())).thenReturn(Arrays.asList(voteDb));

        when(votingSessionRepository.save(any(VotingSession.class))).thenReturn(votingSessionDb);

        VotingSessionResultDto response =  votingSessionResultService.generateResultById(ONE);

        assertEquals( ONE , response.getVoteYes() );
        assertEquals( ZERO , response.getVoteNo() );
        assertEquals( ONE , response.getVotingSession().getId() );
    }

    @Test(expected = BusinessException.class)
    public void tryOpenVotingSessionButNoVOtingSessionFound(){

        when(votingSessionRepository.findById(ONE)).thenReturn(Optional.empty());

        VotingSessionResultDto response =  votingSessionResultService.generateResultById(ONE);

    }
}