package br.com.cooperative.assembly.cache.service;

import static br.com.cooperative.assembly.converter.VoteRequestRedisConverter.generateId;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cooperative.assembly.cache.domain.VoteRequestRedis;
import br.com.cooperative.assembly.cache.repository.VoteRequestRedisRepository;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.service.VoteService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VoteDtoAsyncService {

    private VoteRequestRedisRepository voteRequestRedisRepository;

    private VoteService voteService;

    public VoteDtoAsyncService(
        final VoteRequestRedisRepository voteRequestRedisRepository,
        final VoteService voteService) {
        this.voteRequestRedisRepository = voteRequestRedisRepository;
        this.voteService = voteService;
    }

    @Transactional
    public void executeReceiveRequest(final VoteDto voteDto) {

        String id = generateId(voteDto);

        voteRequestRedisRepository.findById(id)
            .ifPresent(voteRequestRedis -> {
                voteRequestRedis.setProcessed(Boolean.TRUE);
                tryRegistryVote(voteDto, voteRequestRedis);
                voteRequestRedisRepository.save(voteRequestRedis);
            });
    }

    private void tryRegistryVote(final VoteDto voteDto, final VoteRequestRedis voteRequestRedis) {
        try {
            VoteDto voteDtoResult = voteService.registryVote(voteDto);
            voteRequestRedis.setVoteId(voteDtoResult.getId());
        } catch (Exception e) {
            voteRequestRedis.setErrException(e.getClass().getName());
            voteRequestRedis.setTxtException(e.getMessage());
        }
    }

}
