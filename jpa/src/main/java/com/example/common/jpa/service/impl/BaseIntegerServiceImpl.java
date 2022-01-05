package com.example.common.jpa.service.impl;

import com.example.common.jpa.dao.BaseIntegerDao;
import com.example.common.jpa.service.BaseIntegerService;

public abstract class BaseIntegerServiceImpl<DAO extends BaseIntegerDao<T>, T> extends BaseServiceImpl<DAO, T, Integer>
		implements BaseIntegerService<DAO, T> {

}
