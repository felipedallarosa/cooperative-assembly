package br.com.cooperative.assembly.controller.v2.adapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.cache.service.VoteAsyncService;
import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.domain.DecisionVote;
import br.com.cooperative.assembly.dto.VoteDto;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoteAsyncAdapterTest {

    private static final String DOCUMENT = "83714006087";
    private static final Long ONE = 1L;

    private VoteAsyncAdapter voteAsyncAdapter;

    @Mock
    private VoteAsyncService voteAsyncService;


    @Before
    public void setUp() {
        voteAsyncAdapter = new VoteAsyncAdapter(voteAsyncService);
    }

    @Test
    public void shouldAdapterVoteAsyncSucessuful(){

        VoteDto response = VoteDto.builder().document(DOCUMENT).decision(DecisionVote.YES).voteSessionId(ONE).build();

        when(voteAsyncService.executeReceiveRequest(ONE, DOCUMENT)).thenReturn( response );

        voteAsyncAdapter.handleRequest(ONE, DOCUMENT);

        verify(voteAsyncService).executeReceiveRequest(any(),any());
    }


    @Test
    public void shouldAdapterVoteAsyncByIdSucessuful(){
        VoteRequest request = VoteRequest.builder().document(DOCUMENT).decision(DecisionVote.YES).build();

        voteAsyncAdapter.handleRequest(ONE, request);

        verify(voteAsyncService).executeSendRequest(any());

    }


}