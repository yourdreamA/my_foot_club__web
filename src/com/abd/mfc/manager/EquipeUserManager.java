package com.abd.mfc.manager;

import java.util.List;

import com.abd.mfc.dao.EquipeUserDao;
import com.abd.mfc.entities.EquipeUserFilter;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.EquipeUserFilterVO;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.mfc.vo.UserVO;

public interface EquipeUserManager extends BaseManager<EquipeUserFilter, EquipeUserFilterVO, Long, EquipeUserDao> {

	ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO> search(EquipeUserVO filter)
			throws ApplicationAbdException;

	void modifierSoldeEquipeUser(long id, int newSolde)
			throws ApplicationAbdException;

	List<UserVO> rechercherStatsUsers() throws ApplicationAbdException;

}
