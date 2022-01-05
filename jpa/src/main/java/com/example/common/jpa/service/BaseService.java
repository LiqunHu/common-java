package com.example.common.jpa.service;

import java.util.List;

import com.example.common.jpa.dao.BaseDao;

public interface BaseService<DAO extends BaseDao<T, ID>, T, ID> {

	void setDao(DAO dao);

	T save(T m);

	<S extends T> List<S> save(Iterable<S> entities);

	void deleteById(ID id);

	void delete(T entity);

	void delete(Iterable<? extends T> entities);

	T findById(ID id);

	List<T> findAllById(Iterable<ID> ids);

	List<T> findAll();

}
