package com.example.common.auth.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class User {

	private String userId;

	private String userUsername;

	private String userType;

	private String userEmail;

	private String userCountryCode;

	private String userPhone;

	private String userPassword;

	private String userName;

	private String userGender;

	private String userAvatar;

	private String userProvince;

	private String userCity;

	private String userDistrict;

	private String userAddress;

	private String userZipcode;

	private String userRemark;

	private String state;

	private Integer version;

	private String createdAt;

	private String updatedAt;

	private Integer default_organization;

	private String default_organization_code;

	private List<String> groups = new ArrayList<String>();

}