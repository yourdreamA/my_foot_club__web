package com.abd.mfc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.abd.mfc.dao.ParamGenerauxDao;
import com.abd.mfc.entities.Championnat;
import com.abd.mfc.entities.Equipe;

@Repository
public class ParamGenerauxDaoImpl extends BaseDaoImpl<Championnat, Long> implements
		ParamGenerauxDao {
	
	public List<Equipe> findEquipeByChampionnat(long idChampionnat) {
		
		Session session = (Session) entityManager.getDelegate();
		Query query = session.getNamedQuery("findEquipeByChampionnat")
				.setLong("idChampionnat", idChampionnat);
		
		return query.list();
	}
	
	public void addComment(String param) {
		Session session = (Session) entityManager.getDelegate();
		Query query = session.createSQLQuery(param);
		
		int updated = query.executeUpdate();
	}
}
