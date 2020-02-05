package br.com.cooperative.assembly.cache.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cooperative.assembly.cache.domain.VoteRequestRedis;

public interface VoteRequestRedisRepository extends CrudRepository<VoteRequestRedis, String> {

}
