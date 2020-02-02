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
        log.warn("Inserting new Voting Session {}.", votingSessionDto.toString());

        Agenda agenda = agendaService.findAgenda(votingSessionDto.getAgendaId());

        validateAnotherOpenVotingSession(agenda);

        VotingSession votingSession = votingSessionRepository.save(toVotingSession(votingSessionDto, agenda));

        log.warn("New Voting Session Created {}.", votingSession.toString());

        return toVotingSessionDto(votingSession);
    }

    private void validateAnotherOpenVotingSession(final Agenda agenda) {
        if (votingSessionRepository.existsByOpenedAndAgenda(true, agenda)) {
            log.error("There is already an open voting session. Agenda: {}.", agenda.toString());
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

    public List<VotingSession> findAllInvalidOpenedSessionAndClose() {

        List<VotingSession> lstVotingSession = votingSessionRepository.findByOpened(true);

        return lstVotingSession.stream()
                                .filter(VotingSession::shouldClose)
                                .map(this::closeVotingSession)
                            .collect(Collectors.toList());
    }

    private VotingSession closeVotingSession(VotingSession votingSession) {
        votingSession.setOpened(false);
        return  votingSessionRepository.save(votingSession);
    }
}
