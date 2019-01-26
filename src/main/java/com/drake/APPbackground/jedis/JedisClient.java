package com.drake.APPbackground.jedis;

import java.util.List;
import java.util.Set;

public interface JedisClient {
	String set(String key, String value);
	String get(String key);
	Set<String> allKeys();
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
	Boolean hexists(String key, String field);
	List<String> hvals(String key);
	Long del(String key);
}
