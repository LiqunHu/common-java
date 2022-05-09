package com.example.common.redis.operator;

import java.util.List;
import java.util.Set;

public interface Converter<R, T> {

	<C extends T> List<R> toList(List<C> values);

	<C extends T> Set<C> toSet(Class<C> clazz, Set<R> values);

	<C extends T> List<C> toList(Class<C> clazz, List<R> values);

	<C extends T> C toBean(Class<C> clazz, R r);

	R serialize(T value);
}