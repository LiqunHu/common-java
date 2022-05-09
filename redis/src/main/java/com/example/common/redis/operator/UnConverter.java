package com.example.common.redis.operator;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class UnConverter<R, T> implements Converter<R, T> {

	public <C extends T> List<R> toList(List<C> values) {
		if (CollectionUtils.isEmpty(values)) {
			return null;
		}
		return (List<R>) values;
	}

	public <C extends T> Set<C> toSet(Class<C> clazz, Set<R> values) {
		if (CollectionUtils.isEmpty(values)) {
			return null;
		}
		return (Set<C>) values;
	}

	public <C extends T> List<C> toList(Class<C> clazz, List<R> values) {
		if (CollectionUtils.isEmpty(values)) {
			return null;
		}
		return (List<C>) values;
	}

	public <C extends T> C toBean(Class<C> clazz, R r) {
		if (r == null) {
			return null;
		}
		return (C) r;
	}

	public R serialize(Object value) {
		if (value == null) {
			return null;
		}

		return (R) value;
	}
}
