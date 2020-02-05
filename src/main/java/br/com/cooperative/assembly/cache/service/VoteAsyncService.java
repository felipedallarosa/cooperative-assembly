package br.com.cooperative.assembly.cache.service;

import static br.com.cooperative.assembly.domain.EnumMessage.ASSOCIATE_ALREADY_VOTED;
import static br.com.cooperative.assembly.domain.EnumMessage.VOTE_REGISTRY_NOT_FOUNT;
import static org.apache.commons.lang.StringUtils.isEmpty;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cooperative.assembly.cache.domain.VoteRequestRedis;
import br.com.cooperative.assembly.cache.repository.VoteRequestRedisRepository;
import br.com.cooperative.assembly.converter.VoteRequestRedisConverter;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.exception.FeignExceptionException;
import br.com.cooperative.assembly.exception.InvalidDocumentException;
import br.com.cooperative.assembly.exception.ProcessingException;
import br.com.cooperative.assembly.facede.message.producer.VoteProducer;
import br.com.cooperative.assembly.service.MessageService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VoteAsyncService {

    private VoteProducer voteProducer;

    private VoteRequestRedisRepository voteRequestRedisRepository;

    private VoteRequestRedisConverter voteRequestRedisConverter;

    private MessageService messageService;

    public VoteAsyncService(final VoteProducer voteProducer,
        final VoteRequestRedisRepository voteRequestRedisRepository,
        final VoteRequestRedisConverter voteRequestRedisConverter,
        final MessageService messageService) {
        this.voteProducer = voteProducer;
        this.voteRequestRedisRepository = voteRequestRedisRepository;
        this.voteRequestRedisConverter = voteRequestRedisConverter;
        this.messageService = messageService;
    }

    public void executeSendRequest(final VoteDto voteDto) {

        validateDuplicateRequest(voteDto);

        voteRequestRedisRepository.save(voteRequestRedisConverter.toVoteRequestRedis(voteDto));

        voteProducer.send(voteDto);
    }

    private void validateDuplicateRequest(final VoteDto voteDto) {
        String id = voteRequestRedisConverter.generateId(voteDto);

        if (voteRequestRedisRepository.findById(id).isPresent()) {
            throw new BusinessException(messageService.get(ASSOCIATE_ALREADY_VOTED));
        }
    }

    @Transactional
    public void executeReceiveRequest(final Long votingSessionId, final String document) {
        String id = voteRequestRedisConverter.generateId(votingSessionId, document);

        VoteRequestRedis vote = voteRequestRedisRepository.findById(id)
            .orElseThrow(() -> new InvalidDocumentException(messageService.get(VOTE_REGISTRY_NOT_FOUNT)));

        if (!vote.getProcessed()) { throw new ProcessingException(""); }

        verifyError(vote);

    }

    private void verifyError(final VoteRequestRedis vote) {
        if (!isEmpty(vote.getErrException())) {
            String nameError = vote.getErrException();
            String txtError = vote.getTxtException();

            voteRequestRedisRepository.deleteById(vote.getId());

            if (BusinessException.class.getName().equals(nameError)) {
                throw new BusinessException(txtError);
            } else if (FeignExceptionException.class.getName().equals(nameError)) {
                throw new FeignExceptionException(txtError);
            } else if (InvalidDocumentException.class.getName().equals(nameError)) {
                throw new InvalidDocumentException(txtError);
            }
        }
    }
}
