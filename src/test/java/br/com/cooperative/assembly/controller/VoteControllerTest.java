package br.com.cooperative.assembly.controller;

import static org.junit.Assert.*;
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

import br.com.cooperative.assembly.controller.adapter.VoteAdapter;
import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.controller.response.VoteResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(VoteController.class)
public class VoteControllerTest {

    private static final String DOCUMENT = "83714006087";
    private static final String INVALID_DOCUMENT = "123";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VoteAdapter voteAdapter;

    private JacksonTester<VoteRequest> jsonVoteWriter;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void shouldVote() throws Exception {
        VoteRequest voteRequest = VoteRequest.builder().decision(true).document(DOCUMENT).build();

        when(voteAdapter.handleRequest(any(),any())).thenReturn(new VoteResponse());

        String voteRequestBody = jsonVoteWriter.write(voteRequest).getJson();
        MockHttpServletResponse response = mvc.perform(post("/v1/vote/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(voteRequestBody))
            .andReturn()
            .getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void tryVoteButInvalidDocument() throws Exception {
        VoteRequest voteRequest = VoteRequest.builder().decision(true).document(INVALID_DOCUMENT).build();

        when(voteAdapter.handleRequest(any(),any())).thenReturn(new VoteResponse());

        String voteRequestBody = jsonVoteWriter.write(voteRequest).getJson();
        MockHttpServletResponse response = mvc.perform(post("/v1/vote/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(voteRequestBody))
            .andReturn()
            .getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

}