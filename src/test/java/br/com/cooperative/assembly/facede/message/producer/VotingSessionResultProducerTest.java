package br.com.cooperative.assembly.facede.message.producer;

import static br.com.cooperative.assembly.config.CooperativeAssemblyConfiguration.EXCHANGE_NAME;
import static br.com.cooperative.assembly.config.message.VotingSessionResultRabbitConfiguration.ROUTING_KEY_NAME;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.facede.message.producer.VotingSessionResultProducer;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionResultProducerTest {

    private VotingSessionResultProducer votingSessionResultProducer;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() {
        votingSessionResultProducer = new VotingSessionResultProducer(rabbitTemplate);
    }

    @Test
    public void testSendMessage() {

        String queueName = "queueName";

        VotingSessionResultResponse message = VotingSessionResultResponse.builder().build();

        votingSessionResultProducer.send(message);

        verify(rabbitTemplate).convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NAME, message);
    }
}