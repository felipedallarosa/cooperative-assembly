package br.com.cooperative.assembly.facede.message.consumer;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cooperative.assembly.cache.service.VoteDtoAsyncService;
import br.com.cooperative.assembly.dto.VoteDto;

@RunWith(MockitoJUnitRunner.class)
public class VoteConsumerTest {

    private VoteConsumer voteConsumer;

    @Mock
    private VoteDtoAsyncService voteDtoAsyncService;

    @Before
    public void setUp() {
        voteConsumer = new VoteConsumer(voteDtoAsyncService);
    }

    @Test
    public void shouldReceiveVoteRequest() {
            VoteDto vote = VoteDto.builder().build();
            voteConsumer.receive(vote);

            verify(voteDtoAsyncService).executeReceiveRequest(any());
    }

}