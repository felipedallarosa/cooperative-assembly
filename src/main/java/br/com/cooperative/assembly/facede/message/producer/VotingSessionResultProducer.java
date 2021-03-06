package br.com.cooperative.assembly.facede.message.producer;

import static br.com.cooperative.assembly.config.CooperativeAssemblyConfiguration.EXCHANGE_NAME;
import static br.com.cooperative.assembly.config.message.VotingSessionResultRabbitConfiguration.ROUTING_KEY_NAME;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VotingSessionResultProducer {

    private RabbitTemplate rabbitTemplate;

    public VotingSessionResultProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(final VotingSessionResultResponse message) {

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NAME, message);
    }

}


