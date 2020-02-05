package br.com.cooperative.assembly.facede.message.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.cache.service.VoteDtoAsyncService;
import br.com.cooperative.assembly.config.message.VoteRabbitConfiguration;
import br.com.cooperative.assembly.dto.VoteDto;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VoteConsumer {

    private VoteDtoAsyncService voteDtoAsyncService;

    public VoteConsumer(final VoteDtoAsyncService voteDtoAsyncService) {
        this.voteDtoAsyncService = voteDtoAsyncService;
    }

    @RabbitListener(queues = VoteRabbitConfiguration.QUEUE_NAME)
    public void receive(VoteDto voteDto) {

        log.warn("Init Receive Message VoteRequest: {}", voteDto.toString());

        voteDtoAsyncService.executeReceiveRequest(voteDto);

        log.warn("Finish Message VoteRequest");
    }

}