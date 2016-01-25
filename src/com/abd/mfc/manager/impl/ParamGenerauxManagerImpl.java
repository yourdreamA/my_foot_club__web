package com.abd.mfc.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abd.mfc.dao.BaseDao;
import com.abd.mfc.dao.ParamGenerauxDao;
import com.abd.mfc.entities.Championnat;
import com.abd.mfc.entities.Equipe;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.ParamGenerauxManager;
import com.abd.mfc.vo.ChampionnatVO;
import com.abd.mfc.vo.EquipeVO;

@Service("paramGenerauxManager")
public class ParamGenerauxManagerImpl extends BaseManagerImpl<Championnat, ChampionnatVO, Long, BaseDao<Championnat, Long>> 
implements ParamGenerauxManager {

	@Autowired
	private ParamGenerauxDao dao;
	
	@Override
	public List<EquipeVO> findEquipeByChampionnat(long idChampionnat) throws ApplicationAbdException {
		List<EquipeVO> res = null;
		
		List<Equipe> list = dao.findEquipeByChampionnat(idChampionnat);
		if(list != null && !list.isEmpty()) {
			res = new ArrayList<EquipeVO>();
			for (Equipe entity : list) {
				res.add(new EquipeVO(entity));
			}
		}
		
		return res;
	}
	
	@Override
	public void addComment(String param) throws ApplicationAbdException {
		dao.addComment(param);
		
	}
	
	@Override
	public Class<Championnat> getClazz() {
		return Championnat.class;
	}

	@Override
	public ChampionnatVO convertEntityToVO(Championnat e)
			throws ApplicationAbdException {
		return new ChampionnatVO(e);
	}

	@Override
	public BaseDao<Championnat, Long> getDao() {
		return dao;
	}

	
}
