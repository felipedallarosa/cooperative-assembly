package br.com.cooperative.assembly.converter;

import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.controller.response.VoteResponse;
import br.com.cooperative.assembly.domain.Vote;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VoteDto;

public class VoteConverter {

    protected VoteConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static VoteDto toVoteDto(Long voteSessionId, VoteRequest voteRequest) {
        return VoteDto.builder()
                        .voteSessionId(voteSessionId)
                        .document(voteRequest.getDocument())
                        .decision(voteRequest.isDecision())
                    .build();
    }

    public static VoteDto toVoteDto(Vote vote) {
        return VoteDto.builder()
            .voteSessionId(vote.getVotingSession().getId())
            .document(vote.getDocument())
            .decision(vote.isDecision())
            .build();
    }

    public static VoteResponse toVoteResponse(VoteDto voteDto) {
        return VoteResponse.builder()
                            .voteSessionId(voteDto.getVoteSessionId())
                            .document(voteDto.getDocument())
                            .decision(voteDto.isDecision())
                        .build();
    }

    public static Vote toVote(VotingSession votingSession, VoteDto voteDto) {
        return Vote.builder()
                    .votingSession(votingSession)
                    .document(voteDto.getDocument())
                    .decision(voteDto.isDecision())
                .build();
    }
}
