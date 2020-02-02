package br.com.cooperative.assembly.converter;

import br.com.cooperative.assembly.controller.request.AgendaRequest;
import br.com.cooperative.assembly.controller.response.AgendaResponse;
import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.dto.AgendaDto;

public class AgendaConverter {

    protected AgendaConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static AgendaDto toAgendaDto(AgendaRequest agendaRequest) {
        return AgendaDto.builder()
                        .description(agendaRequest.getDescription())
                        .build();
    }

    public static AgendaDto toAgendaDto(Agenda agenda) {
        return AgendaDto.builder()
            .id(agenda.getId())
            .description(agenda.getDescription())
            .build();
    }

    public static AgendaResponse toAgendaResponse(AgendaDto agendaDto) {
        return AgendaResponse.builder()
                             .id(agendaDto.getId())
                             .description(agendaDto.getDescription())
                             .build();
    }

    public static Agenda toAgenda(AgendaDto agendaDto) {
        return Agenda.builder()
            .description(agendaDto.getDescription())
            .build();
    }
}
