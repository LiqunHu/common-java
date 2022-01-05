package com.example.common.jpa.service;

import com.example.common.jpa.service.BaseService;
import com.example.common.jpa.dao.BaseLongDao;

public interface BaseLongService<DAO extends BaseLongDao<T>, T> extends BaseService<DAO, T, Long> {

}
