package br.com.cooperative.assembly.cache.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RedisHash("VOTE_REQUEST_REDIS")
public class VoteRequestRedis implements Serializable {

    @Id
    private String id;

    private Long voteId;

    private Long voteSessionId;

    private String document;

    private boolean decision;

    private Boolean processed;

    private String errException;

    private String txtException;

    public VoteRequestRedis(final Long voteSessionId, final String document, final boolean decision) {
        this.id=generateId(voteSessionId,document);
        this.voteSessionId = voteSessionId;
        this.document = document;
        this.decision = decision;
        this.processed = Boolean.FALSE;
        this.voteId = 0L;
    }

    public static String generateId(final Long voteSessionId, final String document) {
        return voteSessionId + "-" + document;
    }

}
