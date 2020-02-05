package br.com.cooperative.assembly.config.message;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VotingSessionResultRabbitConfiguration {

    public static final String ROUTING_KEY_NAME = "cooperative-assembly.voting-session-result.message";

    public static final String QUEUE_NAME = "cooperative-assembly.voting-session-result.queue";

    public static final String DEAD_LETTER_QUEUE_NAME = "cooperative-assembly.voting-session-result.dead-letter.queue";

    @Bean(name = QUEUE_NAME)
    Queue queue() {
        return QueueBuilder.durable(QUEUE_NAME)
            .withArgument("x-dead-letter-exchange", "")
            .withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_NAME)
            .build();
    }

    @Bean(name = QUEUE_NAME + ".binding")
    Binding binding(TopicExchange votingSessionResultExchange) {
        return BindingBuilder
            .bind(queue())
            .to(votingSessionResultExchange)
            .with(ROUTING_KEY_NAME);
    }

    @Bean(name = DEAD_LETTER_QUEUE_NAME)
    Queue deadLetterQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE_NAME).build();
    }
}
