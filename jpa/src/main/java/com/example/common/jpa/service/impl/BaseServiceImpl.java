package com.example.common.jpa.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.example.common.jpa.dao.BaseDao;
import com.example.common.jpa.model.StatusModel;
import com.example.common.jpa.service.BaseService;

public abstract class BaseServiceImpl<DAO extends BaseDao<T, ID>, T, ID> implements BaseService<DAO, T, ID> {

	protected DAO dao;

	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

	protected PageRequest sortBy(Integer pageNo, Integer pageSize, Direction direction, String... properties) {
		Sort sort = Sort.by(direction, properties);
		return PageRequest.of(pageNo - 1, pageSize, sort);
	}

	protected PageRequest createPageRequest(Integer pageNo, Integer pageSize) {
		return PageRequest.of(pageNo - 1, pageSize);
	}

	public T save(T m) {
		if (m instanceof StatusModel) {
			((StatusModel) m).setUpdatedAt(new Date());
		}
		return dao.save(m);
	}

	public <S extends T> List<S> save(Iterable<S> entities) {
		if (entities != null) {
			Date updatedAt = new Date();
			entities.forEach(it -> {
				if (it instanceof StatusModel) {
					((StatusModel) it).setUpdatedAt(updatedAt);
				}
			});
		}
		return dao.saveAll(entities);
	}

	public void deleteById(ID id) {
		dao.deleteById(id);
	}

	@Override
	public void delete(T entity) {
		dao.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		dao.deleteAll(entities);
	}

	@Override
	public T findById(ID id) {
		if (id == null) {
			return null;
		}
		Optional<T> optional = dao.findById(id);
		return optional.orElse(null);
	}

	@Override
	public List<T> findAllById(Iterable<ID> ids) {
		return dao.findAllById(ids);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

}
