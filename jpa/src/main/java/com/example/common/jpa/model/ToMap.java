package com.example.common.jpa.model;

import java.util.Map;

public interface ToMap {
	Map<String, Object> toMap();

	Map<String, Object> toDeepMap();
}
