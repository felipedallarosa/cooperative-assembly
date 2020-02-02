package br.com.cooperative.assembly.converter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.controller.request.VotingSessionRequest;
import br.com.cooperative.assembly.controller.response.VotingSessionResponse;
import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VotingSessionDto;

@Component
public class VotingSessionConverter {

    @Value("${voting-session.minimun-time}")
    private Long minimunTime = 1L;

    public VotingSessionDto toVotingSessionDto(Long agendaId, VotingSessionRequest votingSessionRequest) {
        return VotingSessionDto.builder()
                            .agendaId(agendaId)
                            .finishVotingSession(buildFinishVotingSession(votingSessionRequest.getTimeVotingSession()))
                            .opened(false)
                        .build();
    }

    public static VotingSession toVotingSession(final VotingSessionDto votingSessionDto, final Agenda agenda) {
         return VotingSession.builder()
                                .agenda(agenda)
                                .opened(votingSessionDto.isOpened())
                                .finishVotingSession(votingSessionDto.getFinishVotingSession())
                            .build();
    }

    public static VotingSessionDto toVotingSessionDto(VotingSession votingSession) {
        return VotingSessionDto.builder()
                                .agendaId(votingSession.getAgenda().getId())
                                .finishVotingSession(votingSession.getFinishVotingSession())
                                .opened(votingSession.isOpened())
                            .build();
    }

    public static VotingSessionResponse toVotingSessionResponse(final VotingSessionDto votingSessionDto) {
        return VotingSessionResponse.builder()
                                    .agendaId(votingSessionDto.getAgendaId())
                                    .finishVotingSession(votingSessionDto.getFinishVotingSession())
                                    .opened(votingSessionDto.isOpened())
                                .build();
    }

    private LocalDateTime buildFinishVotingSession(Long timeVotingSession) {
        LocalDateTime startingVotingSession = LocalDateTime.now();

        if (minimunTime.compareTo(timeVotingSession) < 0) {
            timeVotingSession = minimunTime;
        }

        return startingVotingSession.plusMinutes(timeVotingSession);
    }

}
