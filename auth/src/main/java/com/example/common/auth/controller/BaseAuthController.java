package com.example.common.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSONObject;
import com.example.common.auth.model.AuthUser;
import com.example.common.redis.operator.RedisValueOperator;
import com.example.common.util.consts.Constants;
import com.example.common.util.consts.Util;

public abstract class BaseAuthController {

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	@Qualifier("ObjectRedisValueOperator")
	protected RedisValueOperator<JSONObject, JSONObject> redisValueOperator;

	protected AuthUser getCurrentUser() {
		AuthUser authUser = (AuthUser) request.getAttribute(Constants.AUTH_USER);
		return authUser;
	}

	protected void changeOrganization(int id, String code) {
		AuthUser authUser = getCurrentUser();
		authUser.getUser().setDefault_organization(id);
		authUser.getUser().setDefault_organization_code(code);

		String key = authUser.getRedisKey();
		JSONObject json = Util.to(authUser);
		long expire = redisValueOperator.getExpire(key);
		redisValueOperator.set(key, json, expire);
	}

}
