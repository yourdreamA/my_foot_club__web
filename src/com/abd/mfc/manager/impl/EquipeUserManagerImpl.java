package com.abd.mfc.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abd.mfc.dao.EquipeUserDao;
import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserFilter;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.EquipeUserManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.EquipeUserFilterVO;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.mfc.vo.StatUserVO;
import com.abd.mfc.vo.UserVO;

@Service("equipeUserManager")
public class EquipeUserManagerImpl extends
		BaseManagerImpl<EquipeUserFilter, EquipeUserFilterVO, Long, EquipeUserDao> implements EquipeUserManager {

	@Autowired
	private EquipeUserDao dao;
	@Autowired
	private UserManager userManager;
	
	
	@Override
	public ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO> search(EquipeUserVO filter)
			throws ApplicationAbdException {
		
		List<EquipeUserFilterVO> result = null;
		ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO> res = dao.search(filter);
		
		if (res != null && res.getList() != null && !res.getList().isEmpty()) {
			result = new ArrayList<EquipeUserFilterVO>();
			for (EquipeUserFilter user : res.getList()) {
				result.add(new EquipeUserFilterVO(user, true));
			}
			
			res.setList(null);
			res.setListVO(result);
			//MAJ nbr pages
			if (res.getP() != null && res.getP().getNombreLigne() > 0) {
				int nombrePageTotal = (int) (res.getP().getTotalResultat() / res.getP().getNombreLigne());
				
				if (res.getP().getTotalResultat() % res.getP().getNombreLigne() > 0) {
					nombrePageTotal++;
				}
				res.getP().setTotalPage(nombrePageTotal);
			}
			
		}
		
		return res;
		
	}
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifierSoldeEquipeUser(long id, int newSolde)
			throws ApplicationAbdException {
		dao.modifierSoldeEquipeUser(id, newSolde);
	}
	
	@Override
	public List<UserVO> rechercherStatsUsers()
			throws ApplicationAbdException {
		List<UserVO> list = userManager.getAll();
		
		if (list != null && !list.isEmpty()) {
			for (UserVO userVO : list) {
				EquipeUser esp = dao.findEquipeUserByUser(1, userVO.getId());
				if (esp != null) {
					userVO.setEqUserEsp("1");
					userVO.setLastUpdateUserEsp(esp.getLastUpdate());
				} else {
					userVO.setEqUserEsp("0");
				}
				
				EquipeUser eng = dao.findEquipeUserByUser(2, userVO.getId());
				if (eng != null) {
					userVO.setEqUserEng("1");
					userVO.setLastUpdateUserEng(eng.getLastUpdate());
				} else {
					userVO.setEqUserEng("0");
				}
			}
		}
		
		return list;
	}
	
	
	
	@Override
	public Class<EquipeUserFilter> getClazz() {
		return EquipeUserFilter.class;
	}

	@Override
	public EquipeUserFilterVO convertEntityToVO(EquipeUserFilter e)
			throws ApplicationAbdException {
		return new EquipeUserFilterVO(e, true);
	}

	@Override
	public EquipeUserDao getDao() {
		return dao;
	}


}
