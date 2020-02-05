package br.com.cooperative.assembly.controller.v1.adapter;

import static br.com.cooperative.assembly.converter.AgendaConverter.toAgendaDto;
import static br.com.cooperative.assembly.converter.AgendaConverter.toAgendaResponse;

import org.springframework.stereotype.Component;

import br.com.cooperative.assembly.controller.request.AgendaRequest;
import br.com.cooperative.assembly.controller.v1.response.AgendaResponse;
import br.com.cooperative.assembly.dto.AgendaDto;
import br.com.cooperative.assembly.service.AgendaService;

@Component
public class AgendaAdapter {

    private AgendaService agendaService;

    public AgendaAdapter(final AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    public AgendaResponse handleRequest(final AgendaRequest agendaRequest) {

        AgendaDto agendaDto = toAgendaDto(agendaRequest);

        return toAgendaResponse(agendaService.insertAgenda(agendaDto));
    }
}
