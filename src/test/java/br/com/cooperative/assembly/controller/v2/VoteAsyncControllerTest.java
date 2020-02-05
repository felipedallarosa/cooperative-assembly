package br.com.cooperative.assembly.controller.v2;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.controller.v2.adapter.VoteAsyncAdapter;
import br.com.cooperative.assembly.domain.DecisionVote;

@RunWith(SpringRunner.class)
@WebMvcTest(VoteAsyncController.class)
public class VoteAsyncControllerTest {

    private static final String DOCUMENT = "83714006087";
    private static final String INVALID_DOCUMENT = "123";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VoteAsyncAdapter voteAsyncAdapter;

    private JacksonTester<VoteRequest> jsonVoteWriter;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void shouldVoteAsync() throws Exception {
        VoteRequest voteRequest = VoteRequest.builder().decision(DecisionVote.YES).document(DOCUMENT).build();

        String voteRequestBody = jsonVoteWriter.write(voteRequest).getJson();
        MockHttpServletResponse response = mvc.perform(post("/v2/vote/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(voteRequestBody))
            .andReturn()
            .getResponse();

        assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
    }

    @Test
    public void shouldVerifyVoteAsync() throws Exception {

        MockHttpServletResponse response = mvc.perform(get("/v2/vote/1/1234567890123"))
            .andReturn()
            .getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

}