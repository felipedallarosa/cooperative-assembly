package br.com.cooperative.assembly.service;

import static br.com.cooperative.assembly.converter.VotingSessionResultConverter.toVotingSessionResultDto;
import static br.com.cooperative.assembly.domain.EnumMessage.INVALID_VOTING_SESSION_OPENED;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cooperative.assembly.domain.Vote;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VotingSessionResultDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.repository.VoteRepository;
import br.com.cooperative.assembly.repository.VotingSessionRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class VotingSessionResultService {

    private MessageService messageService;

    private VotingSessionRepository votingSessionRepository;

    private VoteRepository voteRepository;

    public VotingSessionResultService(final MessageService messageService,
        final VotingSessionRepository votingSessionRepository,
        final VoteRepository voteRepository) {
        this.messageService = messageService;
        this.votingSessionRepository = votingSessionRepository;
        this.voteRepository = voteRepository;
    }

    public VotingSessionResultDto generateResult(Long votingSessionId) {

        VotingSession votingSession = findSessionById(votingSessionId);

        List<Vote> listVote = voteRepository.findByVotingSession(votingSession);

        Long voteYes = listVote.stream().filter(Vote::isVoteYes).count();

        Long voteNo = listVote.stream().filter(Vote::isVoteNo).count();

        return toVotingSessionResultDto(votingSession, voteYes, voteNo);
    }

    private VotingSession findSessionById(final Long voteSessionId) {
        return votingSessionRepository.findByOpenedAndId(true, voteSessionId)
            .orElseThrow(() -> new BusinessException(messageService.get(INVALID_VOTING_SESSION_OPENED)));
    }

}
