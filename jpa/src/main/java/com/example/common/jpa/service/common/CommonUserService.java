package com.example.common.jpa.service.common;

import java.util.List;

import com.example.common.jpa.service.BaseIntegerService;
import com.example.common.jpa.dao.common.CommonUserDao;
import com.example.common.jpa.model.common.CommonUser;

public interface CommonUserService extends BaseIntegerService<CommonUserDao, CommonUser> {

	List<CommonUser> searchUser(String searchText, Integer pageNo, Integer pageSize);

	List<CommonUser> searchUser(String searchText);

	CommonUser findByUserId(String userId);

	List<CommonUser> findByUserIdIn(String[] userIds);

	List<CommonUser> findByUserPhoneIn(List<String> phones);

	CommonUser findByUserPhone(String userPhone);
}
