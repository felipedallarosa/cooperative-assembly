package br.com.cooperative.assembly.controller.v1.adapter;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.controller.request.AgendaRequest;
import br.com.cooperative.assembly.controller.v1.response.AgendaResponse;
import br.com.cooperative.assembly.dto.AgendaDto;
import br.com.cooperative.assembly.service.AgendaService;

@RunWith(SpringJUnit4ClassRunner.class)
public class AgendaAdapterTest {

    private static final String DESCRIPTION = "TEST";
    private static final Long ONE = 1L;

    private AgendaAdapter agendaAdapter;

    @Mock
    private AgendaService agendaService;


    @Before
    public void setUp() {
        agendaAdapter = new AgendaAdapter(agendaService);
    }

    @Test
    public void shouldAdapterAgendaSucessuful(){

        AgendaRequest request = AgendaRequest.builder().description(DESCRIPTION).build();
        AgendaDto response = AgendaDto.builder().id(ONE).description(DESCRIPTION).build();

        when(agendaService.insertAgenda(any())).thenReturn( response );

        AgendaResponse out =  agendaAdapter.handleRequest(request);

        assertEquals( ONE , out.getId() );
        assertEquals( DESCRIPTION , out .getDescription() );

    }
}