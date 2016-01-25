package com.abd.mfc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;

import com.abd.mfc.dao.BaseDao;
import com.abd.mfc.entities.BaseEntity;
import com.abd.mfc.entities.Journee;

public class BaseDaoImpl<TYPE extends BaseEntity, ID> implements BaseDao<TYPE, ID> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public void add(TYPE entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(TYPE entity) {
		entityManager.merge(entity);

	}

	@Override
	public TYPE get(Class<TYPE> clazz, ID id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public List<TYPE> getAll(Class<TYPE> clazz) {
		StringBuilder queryString = new StringBuilder("from ").append(clazz.getName());
    	
        Query query = entityManager.createQuery(queryString.toString());
        List<TYPE> resultList = query.getResultList();
        
        return resultList;
	}
	
	@Override
	public Journee findJourneeByNumero(long idChampionnat, int num) {
		Journee j = null;
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query q = session.getNamedQuery("findJourneeByNumero");
		q.setLong("idChampionnat", idChampionnat);
		q.setInteger("numero", num);
		
		List l = q.list();
    	if (l != null && !l.isEmpty()) {
    		j = (Journee) q.list().get(0);
    	}
        return j;
	}

	@Override
	public BaseEntity getObject(Class clazz, ID id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public void updateObject(BaseEntity entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public BaseEntity addObject(BaseEntity entity) {
		entityManager.persist(entity);
		return entity;
	}

}
