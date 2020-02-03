package br.com.cooperative.assembly.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cooperative.assembly.controller.adapter.AgendaAdapter;
import br.com.cooperative.assembly.controller.request.AgendaRequest;
import br.com.cooperative.assembly.controller.response.AgendaResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(AgendaController.class)
public class AgendaControllerTest {

    private static final String DESCRIPTION = "TEST OF DESCRIPTION";
    private static final String INVALID_DESCRIPTION = "TEST";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AgendaAdapter agendaAdapter;

    private JacksonTester<AgendaRequest> jsonAgendaWriter;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void shouldCreateAgenda() throws Exception {
        AgendaRequest agendaRequest = AgendaRequest.builder().description(DESCRIPTION).build();

        when(agendaAdapter.handleRequest(any())).thenReturn(AgendaResponse.builder().build());

        String agendaRequestBody = jsonAgendaWriter.write(agendaRequest).getJson();
        MockHttpServletResponse response = mvc.perform(post("/v1/agenda")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(agendaRequestBody))
            .andReturn()
            .getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void tryCreateAgendaButInvalidDescription() throws Exception {
        AgendaRequest agendaRequest = AgendaRequest.builder().description(INVALID_DESCRIPTION).build();

        when(agendaAdapter.handleRequest(any())).thenReturn(AgendaResponse.builder().build());

        String agendaRequestBody = jsonAgendaWriter.write(agendaRequest).getJson();
        MockHttpServletResponse response = mvc.perform(post("/v1/agenda")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(agendaRequestBody))
            .andReturn()
            .getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }


}