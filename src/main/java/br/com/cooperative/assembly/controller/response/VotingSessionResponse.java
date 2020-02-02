package br.com.cooperative.assembly.controller.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class VotingSessionResponse {

    private Long id;

    private Long agendaId;

    private LocalDateTime finishVotingSession;

    private boolean opened;

}
