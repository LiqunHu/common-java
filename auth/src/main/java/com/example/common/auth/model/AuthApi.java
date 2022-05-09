package com.example.common.auth.model;

import lombok.Data;

@Data
public class AuthApi {

	private String apiName;

	private String apiPath;

	private String apiFunction;

	private Integer authFlag;

	private Integer showFlag;
}