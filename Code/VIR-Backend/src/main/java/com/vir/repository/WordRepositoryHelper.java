package com.vir.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class WordRepositoryHelper {

	@PersistenceContext(unitName = "default")
	private EntityManager entityManager;

	@Transactional
	public int truncateWordTable() {
		final String sql = "truncate table word";
		entityManager.createNativeQuery(sql).executeUpdate();
		return 1;
	}
}
