package com.example.common.jpa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.common.jpa.model.ToMap;

public class ToMapUtil {

	public static List<Map<String, Object>> toMap(List<? extends ToMap> list) {
		if (list == null) {
			return new ArrayList<>();
		}
		List<Map<String, Object>> l = list.stream().map(i -> i.toMap()).collect(Collectors.toList());
		return l;
	}

	public static List<Map<String, Object>> toDeepMap(List<? extends ToMap> list) {
		if (list == null) {
			return new ArrayList<>();
		}
		List<Map<String, Object>> l = list.stream().map(i -> i.toDeepMap()).collect(Collectors.toList());
		return l;
	}

	public static Set<Map<String, Object>> toMap(Set<? extends ToMap> set) {
		if (set == null) {
			return new HashSet<>();
		}

		Set<Map<String, Object>> l = set.stream().map(i -> i.toMap()).collect(Collectors.toSet());
		return l;
	}

	public static Set<Map<String, Object>> toDeepMap(Set<? extends ToMap> set) {
		if (set == null) {
			return new HashSet<>();
		}

		Set<Map<String, Object>> l = set.stream().map(i -> i.toDeepMap()).collect(Collectors.toSet());
		return l;
	}

	public static <T extends ToMap> Map<String, Object> toMap(T model) {
		if (model == null) {
			return new HashMap<>();
		}
		return model.toMap();
	}

	public static <T extends ToMap> Map<String, Object> toDeepMap(T model) {
		if (model == null) {
			return new HashMap<>();
		}
		return model.toDeepMap();
	}

}
