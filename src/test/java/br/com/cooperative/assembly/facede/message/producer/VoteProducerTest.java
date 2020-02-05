package br.com.cooperative.assembly.facede.message.producer;
import static br.com.cooperative.assembly.config.CooperativeAssemblyConfiguration.EXCHANGE_NAME;
import static br.com.cooperative.assembly.config.message.VoteRabbitConfiguration.ROUTING_KEY_NAME;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.facede.message.producer.VotingSessionResultProducer;

@RunWith(MockitoJUnitRunner.class)
public class VoteProducerTest {

    private VoteProducer voteProducer;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() {
        voteProducer = new VoteProducer(rabbitTemplate);
    }

    @Test
    public void testSendMessage() {

        String queueName = "queueName";

        VoteDto message = VoteDto.builder().build();

        voteProducer.send(message);

        verify(rabbitTemplate).convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NAME, message);
    }

}