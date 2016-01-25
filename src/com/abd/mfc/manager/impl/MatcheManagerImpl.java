package com.abd.mfc.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abd.mfc.dao.MatcheDao;
import com.abd.mfc.entities.Championnat;
import com.abd.mfc.entities.Journee;
import com.abd.mfc.entities.Match;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.MatcheManager;
import com.abd.mfc.vo.JourneeVO;
import com.abd.mfc.vo.MatchVO;

@Service("matcheManager")
public class MatcheManagerImpl extends BaseManagerImpl<Match, MatchVO, Long, MatcheDao>
		implements MatcheManager {
	
	@Autowired
	private MatcheDao dao;
	
	public List<MatchVO> findMatchsByDay(long idChampionnat, int numJ) {
		List<MatchVO> result = null;
		List<Match> list = dao.findMatchsByDay(idChampionnat, numJ);
		
		if (list != null && !list.isEmpty()) {
			result = new ArrayList<MatchVO>();
			for (Match match : list) {
				result.add(new MatchVO(match));
			}
		}
		
		return result;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public JourneeVO updateCurrentJournee(long idChampionnat, int actualJournee) {
		Journee jj = dao.findJourneeByNumero(idChampionnat, actualJournee);
		if (jj == null) {
			jj = new Journee();
			jj.setIdChampionnat(idChampionnat);
			jj.setNumero(actualJournee);
			dao.addObject(jj);
		}
		
		Championnat ch = (Championnat) dao.getObject(Championnat.class, idChampionnat);
		ch.setActualJournee(actualJournee);
		dao.updateObject(ch);
		
		return new JourneeVO(jj);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public MatchVO updateResultat(MatchVO m) {
		Match entity = dao.get(Match.class, m.getId());
		entity.setNbrButEq1(m.getNbrButEq1());
		entity.setNbrButEq2(m.getNbrButEq2());
		entity.setJoue(m.getJoue());
		dao.update(entity);
		
		return new MatchVO(entity);
	}
	

	@Override
	public Class<Match> getClazz() {
		return Match.class;
	}

	@Override
	public MatchVO convertEntityToVO(Match e) throws ApplicationAbdException {
		return new MatchVO(e);
	}

	@Override
	public MatcheDao getDao() {
		return dao;
	}

	



}
