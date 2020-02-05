package br.com.cooperative.assembly.cache.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.cache.domain.VoteRequestRedis;
import br.com.cooperative.assembly.cache.repository.VoteRequestRedisRepository;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.exception.InvalidDocumentException;
import br.com.cooperative.assembly.service.VoteService;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoteDtoAsyncServiceTest {

    private static final String DOCUMENT = "83714006087";
    private static final Long ONE = 1L;

    private VoteDtoAsyncService voteDtoAsyncService;

    @Mock
    private VoteRequestRedisRepository voteRequestRedisRepository;

    @Mock
    private VoteService voteService;

    @Before
    public void setUp() {
        voteDtoAsyncService = new VoteDtoAsyncService(voteRequestRedisRepository, voteService);
    }

    @Test
    public void shouldInsertVoteAsyncSucessuful(){

        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();

        VoteRequestRedis voteRedis = getVoteRequestRedis();

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.of(voteRedis) );

        when(voteService.registryVote(any())).thenReturn(request);

        voteDtoAsyncService.executeReceiveRequest(request);

        verify(voteRequestRedisRepository).save(any());
    }

    @Test
    public void tryInsertVoteButInvalidDocument(){

        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();

        VoteRequestRedis voteRedis = getVoteRequestRedis();

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.of(voteRedis) );

        when(voteService.registryVote(any())).thenThrow(InvalidDocumentException.class);

        voteDtoAsyncService.executeReceiveRequest(request);

        verify(voteRequestRedisRepository).save(any());
    }


    private VoteRequestRedis getVoteRequestRedis() {
        VoteRequestRedis voteRedis = new VoteRequestRedis();
        voteRedis.setVoteId(ONE);
        voteRedis.setVoteSessionId(ONE);
        voteRedis.setDecision(true);
        voteRedis.setDocument(DOCUMENT);
        voteRedis.setProcessed(true);
        return voteRedis;
    }

}