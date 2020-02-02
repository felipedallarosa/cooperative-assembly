package br.com.cooperative.assembly.service;

import static br.com.cooperative.assembly.converter.AgendaConverter.toAgenda;
import static br.com.cooperative.assembly.converter.AgendaConverter.toAgendaDto;
import static br.com.cooperative.assembly.domain.EnumMessage.AGENDA_REGISTRY_EXISTS;
import static br.com.cooperative.assembly.domain.EnumMessage.AGENDA_REGISTRY_NOT_EXISTS;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.dto.AgendaDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.repository.AgendaRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AgendaService {

    private AgendaRepository agendaRepository;

    private MessageService messageService;

    public AgendaService(final AgendaRepository agendaRepository,
        final MessageService messageService) {
        this.agendaRepository = agendaRepository;
        this.messageService = messageService;
    }

    @Transactional
    public AgendaDto insertAgenda(AgendaDto agendaDto) {
        log.warn("Inserting new Agenda {}.", agendaDto.toString());

        validateRegistryAgenda(agendaDto);

        Agenda agenda = agendaRepository.save(toAgenda(agendaDto));

        log.warn("New Agenda Created {}.", agenda.toString());

        return toAgendaDto(agenda);
    }

    public Agenda findAgenda(Long agendaId) {

        return agendaRepository.findById(agendaId)
                               .orElseThrow(() -> new BusinessException(messageService.get(AGENDA_REGISTRY_NOT_EXISTS)));

    }

    private void validateRegistryAgenda(final AgendaDto agendaDto) {
        if (agendaRepository.existsByDescription(agendaDto.getDescription())) {
            log.error("There is already an open voting session. Agenda: {}.", agendaDto.toString());
            throw new BusinessException(messageService.get(AGENDA_REGISTRY_EXISTS));
        }
    }

}
