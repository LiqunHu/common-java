package com.example.common.auth.filter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.alibaba.fastjson.JSONObject;
import com.example.common.auth.model.AuthApi;
import com.example.common.auth.model.AuthUser;
import com.example.common.auth.model.User;
import com.example.common.redis.operator.RedisHashOperator;
import com.example.common.redis.operator.RedisValueOperator;
import com.example.common.util.consts.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckUserFilter extends OncePerRequestFilter {

	private static final String APITIMES = "APITIMES";

	private static final String AUTHAPI = "AUTHAPI";

	private static final List<String> VIP_GROUPS = Arrays.asList("VIP1", "VIP2", "VIP3");

	@Autowired
	@Qualifier("ObjectRedisValueOperator")
	protected RedisValueOperator<JSONObject, JSONObject> redisValueOperator;

	@Autowired
	@Qualifier("stringObjectRedisTemplate")
	private RedisTemplate<String, Object> redisTemplate;

//	@Autowired
//	@Qualifier("stringStringRedisTemplate")
//	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	@Qualifier("stringJSONObjectRedisHashOperator")
	private RedisHashOperator<String, JSONObject, JSONObject> stringJSONObjectRedisHashOperator;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		request = requestWrapper;

		String pathInfo = request.getRequestURI();
		log.info("pathInfo:{}, queryString:{}, body: {}", pathInfo, request.getQueryString(),
				IOUtils.toString(requestWrapper.getContentAsByteArray(), "utf-8"));

		String[] split = pathInfo.split("/");
		if (split.length < 2) {
			request.getRequestDispatcher(Constants.ERROR_PATH + "/unauthorized").forward(request, response);
			return;
		}
		String func = split[split.length - 2].toUpperCase();
		log.info("func=" + func);

		JSONObject authPaths = redisValueOperator.get(AUTHAPI, JSONObject.class);

		if (!authPaths.containsKey(func)) {
			log.info("there is no {} in tbl_common_api", func);
			if (StringUtils.equals(func, "AUTH")) {
				filterChain.doFilter(request, response);
				return;
			} else {
				request.getRequestDispatcher(Constants.ERROR_PATH + "/unauthorized").forward(request, response);
				return;
			}

//			filterChain.doFilter(request, response);
//			return;
		}

		Object attribute = request.getAttribute(Constants.AUTH_USER);
		String method = split[split.length - 1].toUpperCase();
		String apifunc = func + method;
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		String times = (String) opsForHash.get(APITIMES, apifunc);
		if (times != null) {
			if (attribute == null) {
				log.info("there is no auth user");
				request.getRequestDispatcher(Constants.ERROR_PATH + "/unauthorized").forward(request, response);
				return;
			}
		}

		if (StringUtils.equals(authPaths.getString(func), "1")) {
			if (attribute == null) {
				log.info("there is no auth user");
				request.getRequestDispatcher(Constants.ERROR_PATH + "/unauthorized").forward(request, response);
				return;
			}
		} else {
			if (times == null) {
				log.info("{} is no need to auth", func);
				filterChain.doFilter(request, response);
				return;
			}
		}

		AuthUser authUser = (AuthUser) attribute;
		if (authUser.getSessionToken().startsWith("SYSTEM")) {
			filterChain.doFilter(request, response);
			return;
		}

		User user = authUser.getUser();
		List<String> groups = user.getGroups();
		List<AuthApi> authApis = authUser.getAuthApis();
		for (AuthApi authApi : authApis) {
			String apiPath = authApi.getApiFunction();
			if (StringUtils.equals(apiPath, func)) {
				if (CollectionUtils.isEmpty(groups) || !CollectionUtils.containsAny(groups, VIP_GROUPS)) {
					if (times != null) {
						int limit = Integer.parseInt(times);
						log.info("\"{}\" can be accessed {} times", apifunc, times);
						if (limit == 0) {
							request.getRequestDispatcher(Constants.ERROR_PATH + "/overtimes").forward(request,
									response);
							return;
						}

						String key = APITIMES + "_" + user.getUserId();
						String creatAt = (String) opsForHash.get(key, "create_at");
						String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						if (!StringUtils.equals(creatAt, now)) {
							log.info("\"{}\" accesses first time today", key);
							redisTemplate.delete(key);
							JSONObject setData = new JSONObject();
							setData.put("create_at", now);
							setData.put(apifunc, 1);
							opsForHash.putAll(key, setData);
							redisTemplate.expire(key, 1, TimeUnit.DAYS);
						} else {
							String visitcount = (String) opsForHash.get(key, apifunc);
							if (visitcount == null) {
								log.info("\"{}\" accesses \"{}\" first time today", key, apifunc);
								opsForHash.put(key, apifunc, 1);
							} else {
								log.info("\"{}\" accesses \"{}\" {} times today", key, apifunc, visitcount);
								if (Integer.parseInt(visitcount) >= limit) {
									request.getRequestDispatcher(Constants.ERROR_PATH + "/overtimes").forward(request,
											response);
									return;
								} else {
									opsForHash.increment(key, apifunc, 1);
								}
							}
						}
					}
				}

				filterChain.doFilter(request, response);
				return;
			}
		}

		log.info("there is no {} in user auth apis", func);
		request.getRequestDispatcher(Constants.ERROR_PATH + "/unauthorized").forward(request, response);
		return;
	}
}