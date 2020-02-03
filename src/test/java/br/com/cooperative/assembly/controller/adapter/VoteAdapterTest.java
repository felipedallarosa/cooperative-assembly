package br.com.cooperative.assembly.controller.adapter;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.controller.response.VoteResponse;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.service.VoteService;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoteAdapterTest {

    private static final String DOCUMENT = "83714006087";
    private static final Long ONE = 1L;

    private VoteAdapter voteAdapter;

    @Mock
    private VoteService voteService;


    @Before
    public void setUp() {
        voteAdapter = new VoteAdapter(voteService);
    }

    @Test
    public void shouldAdapterVoteSucessuful(){
        // given
        VoteRequest request = VoteRequest.builder().document(DOCUMENT).decision(true).build();
        VoteDto response = VoteDto.builder().document(DOCUMENT).decision(true).voteSessionId(ONE).build();

        when(voteService.registryVote(any())).thenReturn( response );

        // when
        VoteResponse out =  voteAdapter.handleRequest(ONE, request);

        //then
        assertEquals( ONE , out.getVoteSessionId() );
        assertEquals( DOCUMENT , out.getDocument() );

    }

}