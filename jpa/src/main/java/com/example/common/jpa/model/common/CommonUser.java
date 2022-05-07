package com.example.common.jpa.model.common;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.example.common.jpa.model.BaseModel;

/**
 * 用户表
 */
@Entity
@Table(name = "tbl_common_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonUser extends BaseModel {
    public CommonUser() {
    }

    public CommonUser(String userId, String userUsername, String userEmail, String userPhone, String userName) {
        this.userId = userId;
        this.userUsername = userUsername;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userName = userName;
    }

    @Id
    @Column(name = "user_id", columnDefinition = "COMMENT '用户主键id'")
    private String userId;

    @Column(name = "user_username")
    private String userUsername;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_country_code")
    private String userCountryCode;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_password_error")
    private String userPasswordError;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "user_province")
    private String userProvince;

    @Column(name = "user_city")
    private String userCity;

    @Column(name = "user_district")
    private String userDistrict;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_zipcode")
    private String userZipcode;

    @Column(name = "user_remark")
    private String userRemark;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = _toMap();
        map.put("userId", userId);
        map.put("userUsername", userUsername);
        map.put("userType", userType);
        map.put("userEmail", userEmail);
        map.put("userCountryCode", userCountryCode);
        map.put("userPhone", userPhone);
        map.put("userPassword", userPassword);
        map.put("userPasswordError", userPasswordError);
        map.put("userName", userName);
        map.put("userGender", userGender);
        map.put("userAvatar", userAvatar);
        map.put("userProvince", userProvince);
        map.put("userCity", userCity);
        map.put("userDistrict", userDistrict);
        map.put("userAddress", userAddress);
        map.put("userZipcode", userZipcode);
        map.put("userRemark", userRemark);
        return map;
    }

    @Override
    public Map<String, Object> toDeepMap() {
        Map<String, Object> map = toMap();
        return map;
    }
}
