package br.com.cooperative.assembly.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.domain.Vote;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.exception.InvalidDocumentException;
import br.com.cooperative.assembly.repository.VoteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class VoteServiceTest {

    private static final String DOCUMENT = "83714006087";
    private static final Long ONE = 1L;

    private VoteService voteService;

    @Mock
    private UserInfoService userInfoService;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VotingSessionService votingSessionService;

    @Mock
    private MessageService messageService;

    @Before
    public void setUp() {
        voteService = new VoteService(userInfoService, voteRepository, votingSessionService, messageService);
    }

    @Test
    public void shouldInsertVoteSucessuful(){
        // given
        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();
        VotingSession votingSession = VotingSession.builder().id(ONE).build();
        Vote voteDb = Vote.builder().id(ONE).decision(true).document(DOCUMENT).votingSession(votingSession).build();

        when(userInfoService.isValidDocument(DOCUMENT)).thenReturn( Boolean.TRUE);

        when(votingSessionService.findOpenedSessionById(any())).thenReturn(votingSession);

        when(voteRepository.existsByVotingSessionAndDocument(any(),any())).thenReturn(Boolean.FALSE);

        when(voteRepository.save(any(Vote.class))).thenReturn(voteDb);

        // when
        VoteDto response =  voteService.registryVote(request);

        //then
        assertEquals( DOCUMENT , response.getDocument() );
        assertEquals( ONE , response.getVoteSessionId() );
    }

    @Test(expected = InvalidDocumentException.class)
    public void tryInsertVoteButInvalidDocument(){
        // given
        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();

        when(userInfoService.isValidDocument(DOCUMENT)).thenReturn( Boolean.FALSE);

        // when
        VoteDto response =  voteService.registryVote(request);
    }

    @Test(expected = BusinessException.class)
    public void tryInsertVoteButVotingSessionInvalid(){
        // given
        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();

        when(userInfoService.isValidDocument(DOCUMENT)).thenReturn( Boolean.TRUE);

        when(votingSessionService.findOpenedSessionById(any())).thenThrow(BusinessException.class);

        // when
        VoteDto response =  voteService.registryVote(request);
    }

    @Test(expected = BusinessException.class)
    public void tryInsertVoteButAssociatedAlreadyVoted(){
        // given
        VoteDto request = VoteDto.builder().decision(true).document(DOCUMENT).voteSessionId(ONE).build();
        VotingSession votingSession = VotingSession.builder().id(ONE).build();

        when(userInfoService.isValidDocument(DOCUMENT)).thenReturn( Boolean.TRUE);

        when(votingSessionService.findOpenedSessionById(any())).thenReturn(votingSession);

        when(voteRepository.existsByVotingSessionAndDocument(any(),any())).thenReturn(Boolean.TRUE);

        // when
        VoteDto response =  voteService.registryVote(request);
    }
}