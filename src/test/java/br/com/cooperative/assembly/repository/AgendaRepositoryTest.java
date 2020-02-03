package br.com.cooperative.assembly.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cooperative.assembly.domain.Agenda;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AgendaRepositoryTest {

    private static final Long ONE = 1L;

    @Autowired
    private AgendaRepository agendaRepository;

    @Test
    public void existsByDescription() {
        boolean result = agendaRepository.existsByDescription("test agenda");

        Assert.assertTrue( result );
    }

    @Test
    public void findById() {
        Optional<Agenda> result = agendaRepository.findById(ONE);

        Assert.assertTrue( result.isPresent() );
    }
}