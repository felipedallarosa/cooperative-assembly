package br.com.cooperative.assembly.facede.message.consumer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.facede.message.consumer.VotingSessionResultConsumer;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingSessionResultConsumerTest {

    private VotingSessionResultConsumer votingSessionResultConsumer;

    @Before
    public void setUp() {
        votingSessionResultConsumer = new VotingSessionResultConsumer();
    }

    @Test
    public void shouldReceiveVotingSessionResult() {
        try {
            votingSessionResultConsumer.receive(VotingSessionResultResponse.builder().build());
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

}