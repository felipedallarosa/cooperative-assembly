package br.com.cooperative.assembly.facede.message.producer;

import static br.com.cooperative.assembly.config.CooperativeAssemblyConfiguration.EXCHANGE_NAME;
import static br.com.cooperative.assembly.config.message.VoteRabbitConfiguration.ROUTING_KEY_NAME;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.dto.VoteDto;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VoteProducer {

    private RabbitTemplate rabbitTemplate;

    public VoteProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(final VoteDto message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NAME, message);
    }

}


