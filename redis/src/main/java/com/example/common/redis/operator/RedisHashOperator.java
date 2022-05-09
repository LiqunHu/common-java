package com.example.common.redis.operator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisHashOperator<K, R, T> extends ARedisDataOperator<R, T> {

	protected HashOperations<String, K, R> opsForHash;

	public void setRedisTemplate(RedisTemplate<String, R> redisTemplate, Converter<R, T> converter) {
		this.redisTemplate = redisTemplate;
		this.converter = converter;
		opsForHash = redisTemplate.opsForHash();
	}

	/**
	 * 根据key获取hash缓存value
	 * 
	 * @param <C>
	 * @param key
	 * @param valueKey
	 * @param clazz
	 * @return
	 */
	public <C extends T> C get(String key, K valueKey, Class<C> clazz) {
		R value = opsForHash.get(key, valueKey);
		return converter.toBean(clazz, value);
	}

	public <C extends T> List<C> multiGet(String key, Collection<K> valueKeys, Class<C> clazz) {
		List<R> values = opsForHash.multiGet(key, valueKeys);
		return converter.toList(clazz, values);
	}

	public Map<K, R> entries(String key) {
		return opsForHash.entries(key);
	}

	public void putAll(String key, Map<K, R> map) {
		opsForHash.putAll(key, map);
	}

	public void putAll(String key, K hashKey, R value) {
		opsForHash.put(key, hashKey, value);
	}

	public Set<K> hkeys(String key) {
		return opsForHash.keys(key);
	}

}