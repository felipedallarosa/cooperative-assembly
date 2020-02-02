package br.com.cooperative.assembly.service;

import static br.com.cooperative.assembly.converter.VotingSessionConverter.toVotingSession;
import static br.com.cooperative.assembly.converter.VotingSessionConverter.toVotingSessionDto;
import static br.com.cooperative.assembly.domain.EnumMessage.INVALID_VOTING_SESSION_OPENED;
import static br.com.cooperative.assembly.domain.EnumMessage.VOTING_SESSION_ALREADY_OPENED;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VotingSessionDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.repository.VotingSessionRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class VotingSessionService {

    private MessageService messageService;

    private AgendaService agendaService;

    private VotingSessionRepository votingSessionRepository;

    public VotingSessionService(final MessageService messageService,
        final AgendaService agendaService, final VotingSessionRepository votingSessionRepository) {
        this.messageService = messageService;
        this.agendaService = agendaService;
        this.votingSessionRepository = votingSessionRepository;
    }

    @Transactional
    public VotingSessionDto openVotingSession(VotingSessionDto votingSessionDto) {

        Agenda agenda = agendaService.findAgenda(votingSessionDto.getAgendaId());

        validateAnotherOpenVotingSession(agenda);

        VotingSession votingSession = votingSessionRepository.save(toVotingSession(votingSessionDto, agenda));

        return toVotingSessionDto(votingSession);
    }

    private void validateAnotherOpenVotingSession(final Agenda agenda) {
        if (votingSessionRepository.existsByOpenedAndAgenda(true, agenda)) {
            log.error(messageService.get(VOTING_SESSION_ALREADY_OPENED));
            throw new BusinessException(messageService.get(VOTING_SESSION_ALREADY_OPENED));
        }
    }

    public VotingSession findOpenedSessionById(final Long voteSessionId) {

        Optional<VotingSession> optVotingSession = votingSessionRepository.findByOpenedAndId(true, voteSessionId);

        if (optVotingSession.isPresent() &&
            LocalDateTime.now().isBefore(optVotingSession.get().getFinishVotingSession())) {
            return optVotingSession.get();
        } else {
            throw new  BusinessException(messageService.get(INVALID_VOTING_SESSION_OPENED));
        }
    }

    public List<Long> findAllInvalidOpenedSessionAndClose() {

        List<VotingSession> lstVotingSession = votingSessionRepository.findByOpened(true);

        return lstVotingSession.stream()
                                .filter(VotingSession::shouldClose)
                                .map(this::closeVotingSessionAndReturnAgendaId)
                            .collect(Collectors.toList());
    }

    private Long closeVotingSessionAndReturnAgendaId(VotingSession votingSession) {
        votingSession.setOpened(false);
        return  votingSessionRepository.save(votingSession).getAgenda().getId();
    }
}
