package com.example.common.auth.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AuthUser {

	private String redisKey;

	private String sessionToken;

	private User user;

	private List<AuthApi> authApis = new ArrayList<AuthApi>();

}