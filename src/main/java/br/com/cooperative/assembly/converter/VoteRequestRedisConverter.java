package br.com.cooperative.assembly.converter;

import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.cache.domain.VoteRequestRedis;
import br.com.cooperative.assembly.dto.VoteDto;

@Component
public class VoteRequestRedisConverter {


    public VoteRequestRedis toVoteRequestRedis(VoteDto voteDto) {
        return new VoteRequestRedis(voteDto.getVoteSessionId(),
            voteDto.getDocument(),
            voteDto.isDecision());
    }


    public String generateId(final VoteDto voteDto) {
        return VoteRequestRedis.generateId(voteDto.getVoteSessionId(),
                                            voteDto.getDocument());
    }

    public String generateId(final Long votingSessionId, final String document) {
        return VoteRequestRedis.generateId(votingSessionId,document);
    }
}
