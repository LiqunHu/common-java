package com.example.common.jpa.service.common;

import java.util.List;

import com.example.common.jpa.service.BaseIntegerService;
import com.example.common.jpa.dao.common.CommonUserDao;
import com.example.common.jpa.model.common.CommonUser;

public interface CommonUserService extends BaseIntegerService<CommonUserDao, CommonUser> {

	List<User> searchUser(String searchText, Integer pageNo, Integer pageSize);

	List<User> searchUser(String searchText);

	User findByUserId(String userId);

	List<User> findByUserIdIn(String[] userIds);

	List<User> findByUserPhoneIn(List<String> phones);

	User findByUserPhone(String userPhone);
}
