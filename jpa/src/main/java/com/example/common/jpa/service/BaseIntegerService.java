package com.example.common.jpa.service;

import com.example.common.jpa.service.BaseService;
import com.example.common.jpa.dao.BaseIntegerDao;

public interface BaseIntegerService<DAO extends BaseIntegerDao<T>, T> extends BaseService<DAO, T, Integer> {

}
