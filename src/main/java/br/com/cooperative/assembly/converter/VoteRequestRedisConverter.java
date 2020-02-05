package br.com.cooperative.assembly.converter;

import br.com.cooperative.assembly.cache.domain.VoteRequestRedis;
import br.com.cooperative.assembly.dto.VoteDto;

public class VoteRequestRedisConverter {

    protected VoteRequestRedisConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static VoteRequestRedis toVoteRequestRedis(VoteDto voteDto) {
        return new VoteRequestRedis(voteDto.getVoteSessionId(),
            voteDto.getDocument(),
            voteDto.getDecision());
    }


    public static String generateId(final VoteDto voteDto) {
        return VoteRequestRedis.generateId(voteDto.getVoteSessionId(),
                                            voteDto.getDocument());
    }

    public static String generateId(final Long votingSessionId, final String document) {
        return VoteRequestRedis.generateId(votingSessionId,document);
    }
}
