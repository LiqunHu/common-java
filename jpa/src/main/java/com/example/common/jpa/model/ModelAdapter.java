package com.example.common.jpa.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.MappedSuperclass;

import com.example.common.jpa.util.ToMapUtil;
import com.example.common.util.consts.DateUtil;

@MappedSuperclass
public abstract class ModelAdapter implements ToMap {

	protected Map<String, Object> _toMap() {
		HashMap<String, Object> map = new HashMap<>();
		return map;
	}

	protected String formatDate(Date date) {
		return DateUtil.formatDate(date);
	}

	protected String formatDateTime(Date date) {
		return DateUtil.formatDateTime(date);
	}

	protected Map<String, Object> toMap(ToMap toMap) {
		return ToMapUtil.toMap(toMap);
	}

	protected void toMap(Map<String, Object> map, String key, ToMap value) {
		if (value != null) {
			map.put(key, value.toMap());
		}
	}

	protected void toMap(Map<String, Object> map, String key, Set<? extends ToMap> value) {
		if (value != null) {
			map.put(key, toMap(value));
		}
	}

	protected List<Map<String, Object>> toMap(List<? extends ToMap> list) {
		return ToMapUtil.toMap(list);
	}

	protected Set<Map<String, Object>> toMap(Set<? extends ToMap> set) {
		return ToMapUtil.toMap(set);
	}

	protected void put(Map<String, Object> map, String key, Object value) {
		if (value != null) {
			map.put(key, value);
		}
	}

}
