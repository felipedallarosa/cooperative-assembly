package br.com.cooperative.assembly.cache.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.cache.domain.VoteRequestRedis;
import br.com.cooperative.assembly.cache.repository.VoteRequestRedisRepository;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.exception.FeignExceptionException;
import br.com.cooperative.assembly.exception.InvalidDocumentException;
import br.com.cooperative.assembly.exception.ProcessingException;
import br.com.cooperative.assembly.facede.message.producer.VoteProducer;
import br.com.cooperative.assembly.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoteAsyncServiceTest {


    private static final String DOCUMENT = "83714006087";
    private static final Long ONE = 1L;

    private VoteAsyncService voteAsyncService;

    @Mock
    private VoteProducer voteProducer;

    @Mock
    private VoteRequestRedisRepository voteRequestRedisRepository;

    @Mock
    private MessageService messageService;

    @Before
    public void setUp() {
        voteAsyncService = new VoteAsyncService(voteProducer, voteRequestRedisRepository, messageService);
    }

    @Test
    public void shouldInsertVoteAsyncOnRedisAndRabbitSucessuful(){

        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.empty() );

        voteAsyncService.executeSendRequest(request);

        verify(voteRequestRedisRepository).save(any());

        verify(voteProducer).send(any());
    }

    @Test( expected = BusinessException.class)
    public void shouldInsertVoteAsyncOnRedisAndRabbitButAlreadyRegistred(){

        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.of(getVoteRequestRedis()) );

        voteAsyncService.executeSendRequest(request);
    }

    @Test
    public void shouldVerifyResultVoteAsyncOnRedisSucessuful(){

        VoteRequestRedis voteRedis = getVoteRequestRedis();

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.of(voteRedis) );

        VoteDto vote = voteAsyncService.executeReceiveRequest(ONE, DOCUMENT);

        Assert.assertEquals(ONE, vote.getVoteSessionId());
        Assert.assertEquals(DOCUMENT, vote.getDocument());
    }

    @Test( expected = ProcessingException.class)
    public void shouldVerifyResultVoteAsyncOnRedisButProcessing(){

        VoteRequestRedis voteRedis = getVoteRequestRedis();
        voteRedis.setProcessed(false);

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.of(voteRedis) );

        voteAsyncService.executeReceiveRequest(ONE, DOCUMENT);
    }

    @Test( expected = BusinessException.class)
    public void shouldVerifyResultVoteAsyncOnRedisButBusinessError(){

        VoteRequestRedis voteRedis = getVoteRequestRedis();
        voteRedis.setErrException(BusinessException.class.getName());

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.of(voteRedis) );

        voteAsyncService.executeReceiveRequest(ONE, DOCUMENT);
    }

    @Test( expected = InvalidDocumentException.class)
    public void shouldVerifyResultVoteAsyncOnRedisButInvalidDocument(){

        VoteRequestRedis voteRedis = getVoteRequestRedis();
        voteRedis.setErrException(InvalidDocumentException.class.getName());

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.of(voteRedis) );

        voteAsyncService.executeReceiveRequest(ONE, DOCUMENT);
    }

    @Test( expected = FeignExceptionException.class)
    public void shouldVerifyResultVoteAsyncOnRedisButFeignExceptionException(){

        VoteRequestRedis voteRedis = getVoteRequestRedis();
        voteRedis.setErrException(FeignExceptionException.class.getName());

        when(voteRequestRedisRepository.findById(any())).thenReturn( Optional.of(voteRedis) );

        voteAsyncService.executeReceiveRequest(ONE, DOCUMENT);
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