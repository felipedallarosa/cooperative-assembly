package br.com.cooperative.assembly.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VotingSessionDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.repository.VotingSessionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingSessionServiceTest {

    private static final Long ONE = 1L;

    private VotingSessionService votingSessionService;

    @Mock
    private MessageService messageService;

    @Mock
    private AgendaService agendaService;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Captor
    private ArgumentCaptor<VotingSession> votingSessionCaptor;

    @Before
    public void setUp() {
        votingSessionService = new VotingSessionService(messageService, agendaService, votingSessionRepository) ;
    }

    @Test
    public void shouldOpenVotingSessionSucessuful(){

        VotingSessionDto request = VotingSessionDto.builder().opened(true).agendaId(ONE)
            .startVotingSession(LocalDateTime.now()).finishVotingSession(LocalDateTime.now()).build();
        Agenda agenda = Agenda.builder().id(ONE).build();
        VotingSession votingSessionDb = VotingSession.builder().id(ONE).agenda(agenda)
            .startVotingSession(LocalDateTime.now()).finishVotingSession(LocalDateTime.now()).build();

        when(agendaService.findAgenda(ONE)).thenReturn(agenda);

        when(votingSessionRepository.existsByOpenedAndAgenda(true, agenda)).thenReturn(Boolean.FALSE);

        when(votingSessionRepository.save(any(VotingSession.class))).thenReturn(votingSessionDb);

        VotingSessionDto response =  votingSessionService.openVotingSession(request);

        assertEquals( ONE , response.getAgendaId() );
        assertEquals( ONE , response.getId() );
    }

    @Test(expected = BusinessException.class)
    public void tryOpenVotingSessionButExistsAnotherValidVotingSession(){

        VotingSessionDto request = VotingSessionDto.builder().opened(true).agendaId(ONE)
            .startVotingSession(LocalDateTime.now()).finishVotingSession(LocalDateTime.now()).build();
        Agenda agenda = Agenda.builder().id(ONE).build();

        when(agendaService.findAgenda(ONE)).thenReturn(agenda);

        when(votingSessionRepository.existsByOpenedAndAgenda(true, agenda)).thenReturn(Boolean.TRUE);

        VotingSessionDto response =  votingSessionService.openVotingSession(request);
    }

    @Test(expected = BusinessException.class)
    public void tryOpenVotingSessionButInvalidAgenda(){

        VotingSessionDto request = VotingSessionDto.builder().opened(true).agendaId(ONE)
            .startVotingSession(LocalDateTime.now()).finishVotingSession(LocalDateTime.now()).build();

        when(agendaService.findAgenda(ONE)).thenThrow(BusinessException.class);

        VotingSessionDto response =  votingSessionService.openVotingSession(request);
    }

    @Test
    public void shouldFindOpenedSessionByIdSucessuful(){

        VotingSession votingSession = VotingSession.builder().opened(true).id(ONE)
            .startVotingSession(LocalDateTime.now()).finishVotingSession(LocalDateTime.now().plusHours(1)).build();

        when(votingSessionRepository.findByOpenedAndId(true, ONE)).thenReturn(Optional.of(votingSession));

        VotingSession response =  votingSessionService.findOpenedSessionById(ONE);

        assertEquals( ONE , response.getId() );
    }

    @Test(expected = BusinessException.class)
    public void tryFindOpenedSessionByIdButNotFoundRegistry(){

        VotingSession votingSession = VotingSession.builder().opened(true).id(ONE)
            .startVotingSession(LocalDateTime.now()).finishVotingSession(LocalDateTime.now()).build();

        when(votingSessionRepository.findByOpenedAndId(true, ONE)).thenReturn(Optional.empty());

        VotingSession response =  votingSessionService.findOpenedSessionById(ONE);
    }

    @Test(expected = BusinessException.class)
    public void tryFindOpenedSessionByIdButNotValidRegistry(){

        VotingSession votingSession = VotingSession.builder().opened(true).id(ONE)
            .startVotingSession(LocalDateTime.now()).finishVotingSession(LocalDateTime.now().minusMinutes(10)).build();

        when(votingSessionRepository.findByOpenedAndId(true, ONE)).thenReturn(Optional.of(votingSession));

        VotingSession response =  votingSessionService.findOpenedSessionById(ONE);
    }

    @Test
    public void shouldFindAllInvalidOpenedSessionAndCloseSucessuful(){

        Agenda agenda = Agenda.builder().id(ONE).build();
        VotingSession votingSessionDb = VotingSession.builder().opened(false).id(ONE).agenda(agenda)
            .startVotingSession(LocalDateTime.now())
            .finishVotingSession(LocalDateTime.now().minusHours(1)).build();

        when(votingSessionRepository.findByOpened(true)).thenReturn(Arrays.asList(votingSessionDb));

        List<VotingSession> response =  votingSessionService.findAllInvalidOpenedSessionAndClose();

        verify(votingSessionRepository).save(votingSessionCaptor.capture());

        assertFalse( response.isEmpty() );
        assertFalse( votingSessionCaptor.getValue().isOpened() );

    }

}