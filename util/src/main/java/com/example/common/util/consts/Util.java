package com.example.common.util.consts;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Util {

	public static <T> T to(Map<? extends String, ? extends Object> map, Class<T> clazz) {
		if (MapUtils.isEmpty(map)) {
			return null;
		}
		return new JSONObject().fluentPutAll(map).toJavaObject(clazz);
	}

	public static <T> List<T> to(JSONArray arr, Class<T> clazz) {
		if (arr == null || arr.isEmpty()) {
			return null;
		}
		return arr.toJavaList(clazz);
	}

	public static <T> JSONObject to(T entity) {
		if (entity == null) {
			return null;
		}
		return (JSONObject) JSON.toJSON(entity);
	}

	public static <T> JSONArray to(List<T> list) {
		if (list == null) {
			return null;
		}
		return (JSONArray) JSON.toJSON(list);
	}
}
