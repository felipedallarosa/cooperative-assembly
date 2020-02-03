package br.com.cooperative.assembly.facede.message;

import static br.com.cooperative.assembly.config.CooperativeAssemblyConfiguration.EXCHANGE_NAME;
import static br.com.cooperative.assembly.config.VotingSessionRabbitResultConfiguration.ROUTING_KEY_NAME;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionProducerTest {

    private VotingSessionProducer votingSessionProducer;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() {
        votingSessionProducer = new VotingSessionProducer(rabbitTemplate);
    }

    @Test
    public void testSendMessage() {
        String message = "Test";

        String queueName = "queueName";

        votingSessionProducer.send(message);

        verify(rabbitTemplate).convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NAME, message);
    }
}