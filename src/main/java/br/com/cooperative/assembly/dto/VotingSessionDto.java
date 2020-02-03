package br.com.cooperative.assembly.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class VotingSessionDto {

    private Long id;

    private Long agendaId;

    private LocalDateTime finishVotingSession;

    private boolean opened;
}
