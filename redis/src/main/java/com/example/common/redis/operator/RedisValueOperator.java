package com.example.common.redis.operator;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisValueOperator<R, T> extends ARedisDataOperator<R, T> {

	protected ValueOperations<String, R> opsForValue;

	public void setRedisTemplate(RedisTemplate<String, R> redisTemplate, Converter<R, T> converter) {
		this.redisTemplate = redisTemplate;
		this.converter = converter;
		opsForValue = redisTemplate.opsForValue();
	}

	/**
	 ** 普通缓存获取**
	 * 
	 * @param key 键
	 * 
	 * @return 值
	 * 
	 */
	public <C extends T> C get(String key, Class<C> clazz) {
		R r = opsForValue.get(key);
//		redisTemplate.keys("*")
		return converter.toBean(clazz, r);
	}

	/**
	 * 
	 * 普通缓存放入
	 * 
	 * @param key   键
	 * 
	 * @param value 值
	 * 
	 * @return true成功 false失败
	 * 
	 */
	public void set(String key, T value) {
		if (value == null) {
			return;
		}
		opsForValue.set(key, converter.serialize(value));
	}

	/**
	 * 
	 * 普通缓存放入并设置时间
	 * 
	 * @param key   键
	 * 
	 * @param value 值
	 * 
	 * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * 
	 * @return true成功 false 失败
	 * 
	 */
	public void set(String key, T value, long time) {
		if (value == null) {
			return;
		}
		if (time > 0) {
			opsForValue.set(key, converter.serialize(value), time, TimeUnit.SECONDS);
		} else {
			set(key, value);
		}
	}

	/**
	 * 
	 * 递增
	 * 
	 * @param key   键
	 * 
	 * @param delta 要增加几(大于0)
	 * 
	 * @return
	 * 
	 */
	public long incr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}
		return opsForValue.increment(key, delta);
	}

	/**
	 * 
	 * 递减
	 * 
	 * @param key   键
	 * 
	 * @param delta 要减少几(小于0)
	 * 
	 * @return
	 * 
	 */
	public long decr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递减因子必须大于0");
		}
		return opsForValue.increment(key, -delta);
	}

}
