package br.com.cooperative.assembly.repository;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.domain.Vote;
import br.com.cooperative.assembly.domain.VotingSession;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VoteRepositoryTest {

    private static final String DOCUMENT = "83714006087";
    private static final Long ONE = 1L;

    @Autowired
    private VoteRepository voteRepository;

    @Test
    public void existsByVotingSessionAndDocument() {
        Agenda agenda = Agenda.builder().id(ONE).description("test agenda").build();
        VotingSession votingSessionDb = VotingSession.builder().id(ONE).agenda(agenda).finishVotingSession(
            LocalDateTime.of(2030,01,17,0,0)).build();
        boolean result = voteRepository.existsByVotingSessionAndDocument(votingSessionDb, DOCUMENT);
        Assert.assertTrue( result );
    }

    @Test
    public void findByVotingSession() {
        Agenda agenda = Agenda.builder().id(ONE).description("test agenda").build();
        VotingSession votingSessionDb = VotingSession.builder().id(ONE).agenda(agenda).finishVotingSession(
            LocalDateTime.of(2030,01,17,0,0)).build();
        List<Vote> result = voteRepository.findByVotingSession(votingSessionDb);
        Assert.assertFalse( result.isEmpty() );
    }
}