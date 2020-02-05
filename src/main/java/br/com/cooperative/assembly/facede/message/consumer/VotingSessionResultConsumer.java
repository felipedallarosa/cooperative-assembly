package br.com.cooperative.assembly.facede.message.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.config.message.VotingSessionResultRabbitConfiguration;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VotingSessionResultConsumer {

    @RabbitListener(queues = VotingSessionResultRabbitConfiguration.QUEUE_NAME)
    public void receive(VotingSessionResultResponse message) {

        log.warn("Init Message: {}", message.toString());

        log.warn(message.toString());

        log.warn("Finish Message");
    }

}