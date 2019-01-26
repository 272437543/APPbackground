package com.drake.APPbackground.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport {
	Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private String port;

	@Value("${spring.redis.timeout}")
	private String timeout;

	@Value("${spring.redis.pool.max-idle}")
	private String maxIdle;

	@Value("${spring.redis.pool.max-wait}")
	private String maxWaitMillis;

	@Bean
	public JedisPool redisPoolFactory() {
		
		logger.info("JedisPool注入成功！！");
		logger.info("redis地址：" + host + ":" + port);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(Integer.valueOf(maxIdle));
		jedisPoolConfig.setMaxWaitMillis(Long.valueOf(maxWaitMillis));

		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, Integer.valueOf(port));

		return jedisPool;
	}

	@Override
	public String toString() {
		return "RedisCacheConfiguration [logger=" + logger + ", host=" + host
				+ ", port=" + port + ", timeout=" + timeout + ", maxIdle="
				+ maxIdle + ", maxWaitMillis=" + maxWaitMillis + "]";
	}
}
