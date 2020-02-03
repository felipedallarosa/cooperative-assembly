package br.com.cooperative.assembly.controller.adapter;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.controller.response.VotingSessionResponse;
import br.com.cooperative.assembly.controller.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.converter.VotingSessionConverter;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VotingSessionDto;
import br.com.cooperative.assembly.dto.VotingSessionResultDto;
import br.com.cooperative.assembly.service.VotingSessionResultService;
import br.com.cooperative.assembly.service.VotingSessionService;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingSessionAdapterTest {

    private static final Long ONE = 1L;

    private VotingSessionAdapter votingSessionAdapter;

    @Mock
    private VotingSessionService votingSessionService;

    @Mock
    private VotingSessionResultService votingSessionResultService;

    @Mock
    private VotingSessionConverter votingSessionConverter;

    @Before
    public void setUp() {
        votingSessionAdapter = new VotingSessionAdapter(votingSessionService, votingSessionResultService, votingSessionConverter);
    }

    @Test
    public void shouldVotingSessionAdapterSucessuful(){
        // given
        VotingSessionDto response = VotingSessionDto.builder().agendaId(ONE).opened(true)
            .finishVotingSession(LocalDateTime.now()).id(ONE).build();

        when(votingSessionService.openVotingSession(any())).thenReturn( response );

        // when
        VotingSessionResponse out =  votingSessionAdapter.handleRequest(ONE, ONE);

        //then
        assertEquals( ONE , out.getId() );
        assertEquals( ONE , out.getAgendaId() );
    }

    @Test
    public void shouldVotingSessionAdapterSucessufulWhenNullTime(){
        // given
        VotingSessionDto response = VotingSessionDto.builder().agendaId(ONE).opened(true)
            .finishVotingSession(LocalDateTime.now()).id(ONE).build();

        when(votingSessionService.openVotingSession(any())).thenReturn( response );

        // when
        VotingSessionResponse out =  votingSessionAdapter.handleRequest(ONE, null);

        //then
        assertEquals( ONE , out.getId() );
        assertEquals( ONE , out.getAgendaId() );
    }

    @Test
    public void shouldVotingResultAdapterSucessufulById(){
        // given
        VotingSession votingSession = VotingSession.builder().id(ONE).build();

        VotingSessionResultDto response = VotingSessionResultDto.builder().voteNo(ONE).voteYes(ONE)
                                                                .votingSession(votingSession).build();

        when(votingSessionResultService.generateResultById(any())).thenReturn( response );

        // when
        VotingSessionResultResponse out =  votingSessionAdapter.handleRequest(ONE);

        //then
        assertEquals( ONE , out.getVoteNo() );
        assertEquals( ONE , out.getVoteYes() );
        assertEquals( ONE , out.getVotingSession().getId() );
    }


    @Test
    public void shouldVotingResultAdapterSucessufulBySchedualer(){
        // given
        VotingSession votingSession = VotingSession.builder().id(ONE).build();

        VotingSessionResultDto response = VotingSessionResultDto.builder().voteNo(ONE).voteYes(ONE)
            .votingSession(votingSession).build();

        when(votingSessionResultService.generateResult(any())).thenReturn( response );

        // when
        VotingSessionResultResponse out =  votingSessionAdapter.handleRequest(votingSession);

        //then
        assertEquals( ONE , out.getVoteNo() );
        assertEquals( ONE , out.getVoteYes() );
        assertEquals( ONE , out.getVotingSession().getId() );
    }
}