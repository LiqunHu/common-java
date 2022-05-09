package com.example.common.redis.operator;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

public abstract class ARedisDataOperator<R, T> {
	
	protected RedisTemplate<String, R> redisTemplate;

	protected Converter<R, T> converter;

	public abstract void setRedisTemplate(RedisTemplate<String, R> redisTemplate, Converter<R, T> converter);

	public RedisTemplate<String, R> getRedisTemplate() {
		return redisTemplate;
	}

	public Converter<R, T> getConverter() {
		return converter;
	}

	/**
	 * 指定缓存失效时间
	 * 
	 * @param key  键
	 * @param time 时间(秒)
	 * @return
	 */
	public void expire(String key, long time) {
		if (time > 0) {
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	/**
	 * 
	 * 根据key 获取过期时间
	 * 
	 * @param key 键 不能为null
	 * 
	 * @return 时间(秒) 返回0代表为永久有效
	 * 
	 */
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * 判断key是否存在
	 * 
	 * @param key 键
	 * 
	 * @return true 存在 false不存在
	 * 
	 */
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 
	 * 删除缓存
	 * 
	 * @param key 可以传一个值 或多个
	 * 
	 */
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(Arrays.asList(key));
			}
		}
	}

}
