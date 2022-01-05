package com.example.common.jpa.dao;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseIntegerDao<T> extends BaseDao<T, Integer> {

}
