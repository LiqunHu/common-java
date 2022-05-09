package com.example.common.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.example.common.redis.operator.RedisHashOperator;
import com.example.common.redis.operator.RedisValueOperator;
import com.example.common.redis.operator.UnConverter;

@Slf4j
@Configuration
public class RedisConfig {
	public RedisConfig() {
		log.info("RedisConfig init.");
	}

	@Bean(name = "JSONObjectRedisTemplate")
	public RedisTemplate<String, JSONObject> jsonObjectRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, JSONObject> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		// 全局开启AutoType，不建议使用
		// ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		// 建议使用这种方式，小范围指定白名单
		// ParserConfig.getGlobalInstance().addAccept("com.shinetechchina.project.common.base.");

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);

		FastJsonRedisSerializer<JSONObject> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(JSONObject.class);
		// value序列化方式采用jackson
		template.setValueSerializer(fastJsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(fastJsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean(name = "JSONArrayRedisTemplate")
	public RedisTemplate<String, JSONArray> jsonArrayRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, JSONArray> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		// 全局开启AutoType，不建议使用
		// ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		// 建议使用这种方式，小范围指定白名单
//		ParserConfig.getGlobalInstance().addAccept("com.shinetechchina.project.common.base.");

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);

		FastJsonRedisSerializer<JSONArray> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(JSONArray.class);
		// value序列化方式采用jackson
		template.setValueSerializer(fastJsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(fastJsonRedisSerializer);
		template.afterPropertiesSet();
		return template;

	}

	@Bean(name = "stringObjectRedisTemplate")
	public RedisTemplate<String, Object> stringObjectRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		// 全局开启AutoType，不建议使用
		// ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		// 建议使用这种方式，小范围指定白名单

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		GenericToStringSerializer<Object> genericToStringSerializer = new GenericToStringSerializer<Object>(
				Object.class);
		template.setValueSerializer(genericToStringSerializer);

		template.setHashKeySerializer(genericToStringSerializer);
		template.setHashValueSerializer(genericToStringSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean(name = "stringStringRedisTemplate")
	public RedisTemplate<String, String> stringStringRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		// 全局开启AutoType，不建议使用
		// ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		// 建议使用这种方式，小范围指定白名单

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(stringRedisSerializer);

		// key采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setHashValueSerializer(stringRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean(name = "ObjectRedisValueOperator")
	public RedisValueOperator<JSONObject, JSONObject> objectRedisValueOperator(
			@Autowired @Qualifier("JSONObjectRedisTemplate") RedisTemplate<String, JSONObject> redisTemplate) {
		RedisValueOperator<JSONObject, JSONObject> op = new RedisValueOperator<>();
		op.setRedisTemplate(redisTemplate, new UnConverter<JSONObject, JSONObject>());
		return op;
	}

	@Bean(name = "stringJSONObjectRedisHashOperator")
	public RedisHashOperator<String, JSONObject, JSONObject> stringJSONObjectRedisHashOperator(
			@Autowired @Qualifier("JSONObjectRedisTemplate") RedisTemplate<String, JSONObject> template) {

		RedisHashOperator<String, JSONObject, JSONObject> op = new RedisHashOperator<>();
		op.setRedisTemplate(template, new UnConverter<JSONObject, JSONObject>());
		return op;
	}
}