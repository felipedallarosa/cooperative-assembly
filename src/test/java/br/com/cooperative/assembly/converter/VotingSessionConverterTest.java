package br.com.cooperative.assembly.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.dto.VotingSessionDto;

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
        VotingSessionDto out =  votingSessionConverter.toVotingSessionDto(ONE, ONE);

        assertEquals( ONE , out.getAgendaId() );
    }

    @Test
    public void shouldVotingSessionConverterSucessufulWhenNullTime(){
        VotingSessionDto out =  votingSessionConverter.toVotingSessionDto(ONE, null);

        assertEquals( ONE , out.getAgendaId() );
    }

}