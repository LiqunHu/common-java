package com.example.common.jpa.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class StatusModel extends BaseModel {

	public final static String NORMAL = "1";
	public final static String DELETE = "0";

	// see Status
	@Column(name = "state", nullable = false)
	protected String state = NORMAL;

	@Column(name = "version", nullable = false)
	protected Integer version = 0;

	@Column(name = "created_at", nullable = false)
	protected Date createdAt = new Date();

	@Column(name = "updated_at", nullable = false)
	protected Date updatedAt = new Date();

	protected Map<String, Object> _toMap() {
		Map<String, Object> map = super._toMap();
		map.put("state", state);
		map.put("version", version);
		map.put("createdAt", formatDateTime(createdAt));
		map.put("updatedAt", formatDateTime(updatedAt));
		return map;
	}

}
