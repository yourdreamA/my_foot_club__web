package com.abd.mfc.manager;

import java.util.List;

import com.abd.mfc.dao.MatcheDao;
import com.abd.mfc.entities.Match;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.JourneeVO;
import com.abd.mfc.vo.MatchVO;

public interface MatcheManager extends BaseManager<Match, MatchVO, Long, MatcheDao> {
	List<MatchVO> findMatchsByDay(long idChampionnat, int numJ) throws ApplicationAbdException;

	JourneeVO updateCurrentJournee(long idChampionnat, int actualJournee);
	
	MatchVO updateResultat(MatchVO m);
}
