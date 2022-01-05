package com.example.common.jpa.service.impl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.common.jpa.service.impl.BaseIntegerServiceImpl;
import com.example.common.jpa.dao.common.CommonUserDao;
import com.example.common.jpa.model.common.CommonUser;
import com.example.common.jpa.service.common.CommonUserService;

@Service
@Transactional
public class CommonUserServiceImpl extends BaseIntegerServiceImpl<CommonUserDao, CommonUser> implements CommonUserService {

	@Override
	@Autowired
	public void setDao(CommonUserDao dao) {
		this.dao = dao;
	}

	@Override
	public List<CommonUser> searchUser(String searchText, Integer pageNo, Integer pageSize) {
		return dao.searchUser(searchText, createPageRequest(pageNo, pageSize));
	}

	@Override
	public List<CommonUser> searchUser(String searchText) {
		return dao.searchUser(searchText);
	}

	@Override
	public CommonUser findByUserId(String userId) {
		return dao.findByUserId(userId);
	}

	@Override
	public List<CommonUser> findByUserIdIn(String[] userIds) {
		return dao.findByUserIdIn(userIds);
	}

	@Override
	public List<CommonUser> findByUserPhoneIn(List<String> phones) {
		return dao.findByUserPhoneIn(phones);
	}

	@Override
	public CommonUser findByUserPhone(String userPhone) {
		return dao.findByUserPhone(userPhone);
	}
}
