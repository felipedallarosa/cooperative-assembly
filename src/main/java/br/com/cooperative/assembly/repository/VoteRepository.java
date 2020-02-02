package br.com.cooperative.assembly.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cooperative.assembly.domain.Vote;
import br.com.cooperative.assembly.domain.VotingSession;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

    boolean existsByVotingSessionAndDocument(VotingSession votingSession, String document);

    List<Vote> findByVotingSession(VotingSession votingSession);

}
