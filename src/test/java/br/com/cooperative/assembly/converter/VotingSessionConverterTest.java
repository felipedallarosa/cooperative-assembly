package br.com.cooperative.assembly.converter;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.controller.adapter.AgendaAdapter;
import br.com.cooperative.assembly.controller.adapter.VotingSessionAdapter;
import br.com.cooperative.assembly.controller.response.VotingSessionResponse;
import br.com.cooperative.assembly.dto.VotingSessionDto;
import br.com.cooperative.assembly.service.VotingSessionService;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingSessionConverterTest {

    private static final Long ONE = 1L;

    private VotingSessionConverter votingSessionConverter;

    @Before
    public void setUp() {
        votingSessionConverter = new VotingSessionConverter();
    }
    @Test
    public void shouldVotingSessionConverterSucessuful(){
        // given

        // when
        VotingSessionDto out =  votingSessionConverter.toVotingSessionDto(ONE, ONE);

        //then
        assertEquals( ONE , out.getAgendaId() );
    }

    @Test
    public void shouldVotingSessionConverterSucessufulWhenNullTime(){
        // given

        // when
        VotingSessionDto out =  votingSessionConverter.toVotingSessionDto(ONE, null);

        //then
        assertEquals( ONE , out.getAgendaId() );
    }

}