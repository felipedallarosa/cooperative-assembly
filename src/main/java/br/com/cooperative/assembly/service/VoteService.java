package br.com.cooperative.assembly.service;

import static br.com.cooperative.assembly.converter.VoteConverter.toVote;
import static br.com.cooperative.assembly.converter.VoteConverter.toVoteDto;
import static br.com.cooperative.assembly.domain.EnumMessage.ASSOCIATE_ALREADY_VOTED;
import static br.com.cooperative.assembly.domain.EnumMessage.INVALID_DOCUMENT;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.cooperative.assembly.domain.Vote;
import br.com.cooperative.assembly.domain.VotingSession;
import br.com.cooperative.assembly.dto.VoteDto;
import br.com.cooperative.assembly.exception.BusinessException;
import br.com.cooperative.assembly.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class VoteService {

    private UserInfoService userInfoService;

    private VoteRepository voteRepository;

    private VotingSessionService votingSessionService;

    private MessageService messageService;

    public VoteService(final UserInfoService userInfoService,
        final VoteRepository voteRepository, final VotingSessionService votingSessionService,
        final MessageService messageService) {
        this.userInfoService = userInfoService;
        this.voteRepository = voteRepository;
        this.votingSessionService = votingSessionService;
        this.messageService = messageService;
    }

    @Transactional
    public VoteDto registryVote(VoteDto voteDto) {
        log.warn("Inserting new Vote {}.", voteDto.toString());

        validateDocumentNumber(voteDto);

        VotingSession votingSession = votingSessionService.findOpenedSessionById(voteDto.getVoteSessionId());

        validateVoteBefore(votingSession, voteDto);

        Vote vote = voteRepository.save(toVote(votingSession, voteDto));

        log.warn("New Vote Registred {}.", vote.toString());

        return toVoteDto(vote);
    }

    private void validateDocumentNumber(final VoteDto voteDto) {
        if (!userInfoService.isValidDocument(voteDto.getDocument())) {
            log.error("Invalid document. Vote: {}", voteDto.toString());
            throw new BusinessException(messageService.get(INVALID_DOCUMENT));
        }
    }

    private void validateVoteBefore(final VotingSession votingSession, final VoteDto voteDto) {
        if (voteRepository.existsByVotingSessionAndDocument(votingSession,voteDto.getDocument())) {
            log.error("Associate has already voted for this agenda. Voting Session: {} Vote: {}",
                                votingSession.toString(),
                                voteDto.toString());
            throw new BusinessException(messageService.get(ASSOCIATE_ALREADY_VOTED));
        }
    }
}
