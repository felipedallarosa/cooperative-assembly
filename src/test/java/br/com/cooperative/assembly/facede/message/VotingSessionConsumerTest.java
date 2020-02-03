package br.com.cooperative.assembly.facede.message;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingSessionConsumerTest {

    private VotingSessionConsumer votingSessionConsumer;

    @Before
    public void setUp() {
        votingSessionConsumer = new VotingSessionConsumer();
    }

    @Test
    public void main() {
        try {
            votingSessionConsumer.receive("test");
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

}