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
    public void receive(VotingSessionResultResponse message) {

        log.info("Init Message: {}", message);

        log.info(message.toString());

        log.info("Finish Message");
    }

}