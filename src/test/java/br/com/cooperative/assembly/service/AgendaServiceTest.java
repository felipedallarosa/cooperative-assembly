package br.com.cooperative.assembly.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.dto.AgendaDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.repository.AgendaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class AgendaServiceTest {

    private static final String DESCRIPTION = "TEST";
    private static final Long ONE = 1L;

    private AgendaService agendaService;

    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private MessageService messageService;

    @Before
    public void setUp() {
        agendaService = new AgendaService(agendaRepository, messageService);
    }

    @Test
    public void shouldInsertAgendaSucessuful(){

        AgendaDto request = AgendaDto.builder().description(DESCRIPTION).build();
        Agenda agendaDB = Agenda.builder().description(DESCRIPTION).id(ONE).build();

        when(agendaRepository.existsByDescription(DESCRIPTION))
            .thenReturn( Boolean.FALSE);

        when(agendaRepository.save(any(Agenda.class))).thenReturn(agendaDB);

        AgendaDto response =  agendaService.insertAgenda(request);

        assertEquals( ONE , response.getId() );
        assertEquals( DESCRIPTION , response.getDescription() );
    }

    @Test(expected = BusinessException.class)
    public void tryInsertAgendaButAlreadyExistsRegistry(){

        AgendaDto request = AgendaDto.builder().description(DESCRIPTION).build();

        when(agendaRepository.existsByDescription(DESCRIPTION))
            .thenReturn( Boolean.TRUE);

        AgendaDto response =  agendaService.insertAgenda(request);
    }

    @Test
    public void shouldFindAgendaSucessuful(){

        Agenda agendaDB = Agenda.builder().description(DESCRIPTION).id(ONE).build();

        when(agendaRepository.findById(ONE)).thenReturn( Optional.of(agendaDB) );

        Agenda response =  agendaService.findAgenda(ONE);

        assertEquals( ONE , response.getId() );
        assertEquals( DESCRIPTION , response.getDescription() );
    }

    @Test(expected = BusinessException.class)
    public void tryFindAgendaButNotExistsRegistry(){
        //given
        Agenda agendaDB = Agenda.builder().description(DESCRIPTION).id(ONE).build();

        when(agendaRepository.findById(ONE)).thenThrow(BusinessException.class);

        Agenda response =  agendaService.findAgenda(ONE);
    }
}