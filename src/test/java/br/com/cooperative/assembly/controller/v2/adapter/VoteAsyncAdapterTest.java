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
        voteAsyncAdapter.handleRequest(ONE, DOCUMENT);

        verify(voteAsyncService).executeReceiveRequest(any(),any());
    }


    @Test
    public void shouldAdapterVoteAsyncByIdSucessuful(){
        VoteRequest request = VoteRequest.builder().document(DOCUMENT).decision(true).build();

        voteAsyncAdapter.handleRequest(ONE, request);

        verify(voteAsyncService).executeSendRequest(any());

    }


}