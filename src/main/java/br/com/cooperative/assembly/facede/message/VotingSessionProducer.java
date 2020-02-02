package br.com.cooperative.assembly.facede.message;

import static br.com.cooperative.assembly.config.CooperativeAssemblyConfiguration.EXCHANGE_NAME;
import static br.com.cooperative.assembly.config.VotingSessionRabbitResultConfiguration.ROUTING_KEY_NAME;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.controller.response.VotingSessionResultResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VotingSessionProducer {

    private RabbitTemplate rabbitTemplate;

    public VotingSessionProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(final VotingSessionResultResponse message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NAME, message);
    }

}


