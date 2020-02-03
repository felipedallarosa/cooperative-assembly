package br.com.cooperative.assembly.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.cooperative.assembly.controller.adapter.VotingSessionAdapter;
import br.com.cooperative.assembly.controller.response.VotingSessionResponse;
import br.com.cooperative.assembly.controller.response.VotingSessionResultResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(VotingSessionController.class)
public class VotingSessionControllerTest {

    private static final Long ONE = 1L;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VotingSessionAdapter votingSessionAdapter;

    @Test
    public void shouldCreateVotingSession() throws Exception {

        when(votingSessionAdapter.handleRequest(ONE,ONE)).thenReturn(VotingSessionResponse.builder().build());

        MockHttpServletResponse response = mvc.perform(post("/v1/votingSession/1?timeVotingSession=1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn()
            .getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void shouldSearchResultVotingSession() throws Exception {

        when(votingSessionAdapter.handleRequest(ONE)).thenReturn(VotingSessionResultResponse.builder().build());

        MockHttpServletResponse response = mvc.perform(get("/v1/votingSession/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn()
            .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}