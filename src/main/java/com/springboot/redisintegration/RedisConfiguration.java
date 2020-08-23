package com.springboot.redisintegration;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Lazy
@Configuration
public class RedisConfiguration {
	@Inject
    private RedisProperties redisProperties; 
	
	@Lazy
	@Bean
	protected RedisConnectionFactory jedisConnectionFactory() {
		RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
				.master(redisProperties.getSentinel().getMaster());
		redisProperties.getSentinel().getNodes().forEach(s -> {
			sentinelConfig.sentinel(s, redisProperties.getPort());
		}); 
		// sentinelConfig.setPassword(RedisPassword.of(redisProperties.getPassword()));
		RedisConnectionFactory factory = new JedisConnectionFactory(sentinelConfig);
		return factory;
	}
	 
	
	@Lazy
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
}
