package br.com.cooperative.assembly.controller.v1.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VotingSessionResponse {

    private Long id;

    private Long agendaId;

    private LocalDateTime finishVotingSession;

    private boolean opened;

}
