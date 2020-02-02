package br.com.cooperative.assembly.facede.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.config.VotingSessionRabbitResultConfiguration;
import br.com.cooperative.assembly.controller.response.VotingSessionResultResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VotingSessionConsumer {

    @RabbitListener(queues = VotingSessionRabbitResultConfiguration.QUEUE_NAME)
    public void receive(String message) {

        log.warn("Init Message: {}", message);

        log.warn(message);

        log.warn("Finish Message");
    }

}