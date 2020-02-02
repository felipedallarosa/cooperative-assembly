package br.com.cooperative.assembly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cooperative.assembly.domain.Agenda;
import br.com.cooperative.assembly.domain.VotingSession;


@Repository
public interface VotingSessionRepository extends CrudRepository<VotingSession, Long> {

    boolean existsByOpenedAndAgenda(boolean opened, Agenda agenda);

    Optional<VotingSession> findByOpenedAndId(boolean opened, Long id);

    List<VotingSession> findByOpened(boolean opened);
}
