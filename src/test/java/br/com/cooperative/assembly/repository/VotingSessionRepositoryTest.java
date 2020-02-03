package br.com.cooperative.assembly.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.domain.VotingSession;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VotingSessionRepositoryTest {

    private static final Long ONE = 1L;

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Test
    public void existsByOpenedAndAgenda() {
        Agenda agenda = Agenda.builder().id(ONE).description("test agenda").build();
        boolean result = votingSessionRepository.existsByOpenedAndAgenda(false, agenda);

        Assert.assertTrue( result );
    }

    @Test
    public void findByOpenedAndId() {
        Optional<VotingSession> result = votingSessionRepository.findByOpenedAndId(false, ONE);

        Assert.assertEquals( ONE, result.get().getId() );
    }

    @Test
    public void findByOpened() {
        List<VotingSession> result = votingSessionRepository.findByOpened(false);

        Assert.assertFalse( result.isEmpty() );
    }
}