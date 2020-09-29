package com.springboot.redisintegration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Lazy
@Configuration
public class RedisConfiguration {
	@Inject
    private RedisProperties redisProperties; 
	
	@Bean
	public JedisConnectionFactory getConFactory() {
		String clusterNodes = "localhost:6370,localhost:6371,localhost:6372,localhost:6373,localhost:6374,localhost:6375";
        List<RedisNode> redisNodes = Arrays.stream(clusterNodes
                .split(","))
                .map(s -> {
                    String[] singleNode = s.split(":");
                    return new RedisNode(singleNode[0], Integer.parseInt(singleNode[1]));
                }).collect(Collectors.toList());

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setPassword("pass");
        redisClusterConfiguration.setClusterNodes(redisNodes);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
	}
	 
	
	@Lazy
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template= null;
	    try {
	    	template = new RedisTemplate<>();
		    template.setConnectionFactory(getConFactory());
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return template;
	}
}
