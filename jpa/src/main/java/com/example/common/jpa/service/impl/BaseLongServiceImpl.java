package com.example.common.jpa.service.impl;

import com.example.common.jpa.dao.BaseLongDao;
import com.example.common.jpa.service.BaseLongService;

public abstract class BaseLongServiceImpl<DAO extends BaseLongDao<T>, T> extends BaseServiceImpl<DAO, T, Long>
		implements BaseLongService<DAO, T> {

}
