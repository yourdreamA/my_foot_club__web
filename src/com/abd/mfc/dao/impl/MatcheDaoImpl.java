package com.abd.mfc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.abd.mfc.dao.MatcheDao;
import com.abd.mfc.entities.Match;

@Repository
public class MatcheDaoImpl extends BaseDaoImpl<Match, Long> implements MatcheDao {
	
	@Override
	public List<Match> findMatchsByDay(long idChampionnat, int numJ) {
		
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findMatchsByDay");
		q.setLong("idChampionnat", idChampionnat);
		q.setInteger("numJ", numJ);
    	
		List<Match> resultList = q.list();
        
        return resultList;
	}
}
