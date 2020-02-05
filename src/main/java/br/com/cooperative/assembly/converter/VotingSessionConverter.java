package br.com.cooperative.assembly.converter;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.controller.v1.response.VotingSessionResponse;
import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VotingSessionDto;

@Component
public class VotingSessionConverter {

    @Value("${voting-session.minimun-time}")
    private Long minimunTime = 1L;

    public VotingSessionDto toVotingSessionDto(Long agendaId, Long timeVotingSession) {
        return VotingSessionDto.builder()
                            .agendaId(agendaId)
                            .startVotingSession(LocalDateTime.now())
                            .finishVotingSession(buildFinishVotingSession(timeVotingSession))
                            .opened(true)
                        .build();
    }

    public static VotingSession toVotingSession(final VotingSessionDto votingSessionDto, final Agenda agenda) {
         return VotingSession.builder()
                                .agenda(agenda)
                                .opened(votingSessionDto.isOpened())
                                .startVotingSession(votingSessionDto.getStartVotingSession())
                                .finishVotingSession(votingSessionDto.getFinishVotingSession())
                            .build();
    }

    public static VotingSessionDto toVotingSessionDto(VotingSession votingSession) {
        return VotingSessionDto.builder()
                                .id(votingSession.getId())
                                .agendaId(votingSession.getAgenda().getId())
                                .startVotingSession(votingSession.getStartVotingSession())
                                .finishVotingSession(votingSession.getFinishVotingSession())
                                .opened(votingSession.isOpened())
                            .build();
    }

    public static VotingSessionResponse toVotingSessionResponse(final VotingSessionDto votingSessionDto) {
        return VotingSessionResponse.builder()
                                    .id(votingSessionDto.getId())
                                    .agendaId(votingSessionDto.getAgendaId())
                                    .startVotingSession(votingSessionDto.getStartVotingSession())
                                    .finishVotingSession(votingSessionDto.getFinishVotingSession())
                                    .opened(votingSessionDto.isOpened())
                                .build();
    }

    private LocalDateTime buildFinishVotingSession(Long timeVotingSession) {
        LocalDateTime startingVotingSession = LocalDateTime.now();

        if ( Objects.isNull(timeVotingSession) ||
            minimunTime.compareTo(timeVotingSession) > 0) {
            timeVotingSession = minimunTime;
        }

        return startingVotingSession.plusMinutes(timeVotingSession);
    }

}
