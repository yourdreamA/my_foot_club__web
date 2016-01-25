package com.abd.mfc.dao;

import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserFilter;
import com.abd.mfc.vo.EquipeUserFilterVO;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.ResultPaginateVO;

public interface EquipeUserDao extends BaseDao<EquipeUserFilter, Long> {

	ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO> search(EquipeUserVO filter);
	void modifierSoldeEquipeUser(long id, int newSolde);
	EquipeUser findEquipeUserByUser(long idChampionnat, long idUser);
}
