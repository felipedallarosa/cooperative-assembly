package br.com.cooperative.assembly.converter;

import static br.com.cooperative.assembly.domain.VotingSessionResultStatus.generateResultStatus;

import br.com.cooperative.assembly.controller.v1.response.VotingSessionResultResponse;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VotingSessionResultDto;

public class VotingSessionResultConverter {

    protected VotingSessionResultConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static VotingSessionResultDto toVotingSessionResultDto(VotingSession votingSession, Long voteYes, Long voteNo) {
        return VotingSessionResultDto.builder()
                                        .votingSession(votingSession)
                                        .voteYes(voteYes)
                                        .voteNo(voteNo)
                                        .status(generateResultStatus(voteYes,voteNo))
                                    .build();
    }

    public static VotingSessionResultResponse toVotingSessionResultResponse(VotingSessionResultDto votingSessionResultDto) {
        return VotingSessionResultResponse.builder()
                                        .votingSession(votingSessionResultDto.getVotingSession())
                                        .voteYes(votingSessionResultDto.getVoteYes())
                                        .voteNo(votingSessionResultDto.getVoteNo())
                                        .status(votingSessionResultDto.getStatus())
                                    .build();
    }
}
