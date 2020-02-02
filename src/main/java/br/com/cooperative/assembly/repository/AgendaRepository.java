package br.com.cooperative.assembly.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cooperative.assembly.domain.Agenda;

@Repository
public interface AgendaRepository extends CrudRepository<Agenda, Long> {

    boolean existsByDescription(String description);

    Optional<Agenda> findById(Long id);
}
