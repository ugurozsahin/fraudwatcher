package com.inobil.fraudwatcher.dal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class DbRepositoryExtensionsImpl implements DbRepositoryExtensions {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Boolean Check(String query) {
		Object objResult = entityManager.createNativeQuery(query).getSingleResult(); //"Select Count(*) > 0 From Transaction"
		String strResult = objResult.toString();
		Boolean result = Boolean.parseBoolean(strResult);
		return result;
	}

}
