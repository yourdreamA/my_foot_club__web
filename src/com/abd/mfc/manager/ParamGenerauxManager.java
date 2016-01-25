package com.abd.mfc.manager;

import java.util.List;

import com.abd.mfc.dao.BaseDao;
import com.abd.mfc.entities.Championnat;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.ChampionnatVO;
import com.abd.mfc.vo.EquipeVO;

public interface ParamGenerauxManager extends
		BaseManager<Championnat, ChampionnatVO, Long, BaseDao<Championnat, Long>>  {

	List<EquipeVO> findEquipeByChampionnat(long idChampionnat) throws ApplicationAbdException;
	
	void addComment(String param) throws ApplicationAbdException;
}
