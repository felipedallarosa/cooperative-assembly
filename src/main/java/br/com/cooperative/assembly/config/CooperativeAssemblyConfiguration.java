package br.com.cooperative.assembly.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CooperativeAssemblyConfiguration {

    public static final String EXCHANGE_NAME = "cooperative-assembly.exchange";

    @Bean("cooperativeAssemblyExchange")
    TopicExchange cooperativeAssemblyExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
}