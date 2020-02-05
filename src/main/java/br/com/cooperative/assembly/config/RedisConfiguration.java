package br.com.cooperative.assembly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories({
    "br.com.cooperative.assembly.cache.repository"
})
public class RedisConfiguration {

}