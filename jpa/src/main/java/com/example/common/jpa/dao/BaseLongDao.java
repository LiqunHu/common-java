package com.example.common.jpa.dao;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseLongDao<T> extends BaseDao<T, Long> {

}
