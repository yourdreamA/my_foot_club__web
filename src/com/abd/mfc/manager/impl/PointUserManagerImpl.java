package com.abd.mfc.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abd.mfc.dao.PointUserDao;
import com.abd.mfc.entities.Journee;
import com.abd.mfc.entities.PointUser;
import com.abd.mfc.entities.PointUserJournee;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.PointUserManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.PointUserVO;
import com.abd.mfc.vo.StatUserVO;

@Service("pointUserManager")
public class PointUserManagerImpl extends
		BaseManagerImpl<PointUser, PointUserVO, Long, PointUserDao> implements PointUserManager {


	@Autowired
	private PointUserDao dao;
	
	@Autowired
	private UserManager userManager;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updatePointUserSaison(long idChampionnat)  throws ApplicationAbdException {
		/*List<User> users = dao.findUserWithoutPoint(idChampionnat);
		//1-
		if (users != null && !users.isEmpty()) {
			for (User user : users) {
				PointUser p = new PointUser();
				p.setIdChampionnat(idChampionnat);
				p.setIdUser(user.getId());
				p.setNbrPt(0);
				dao.add(p);
			}
		}*/
		//2-
		List<PointUser> listP = dao.calculPointUserSaison(idChampionnat);
		if (listP != null && !listP.isEmpty()) {
			int rang = 1;
			for (PointUser pointUser : listP) {
				pointUser.setRang(rang);
				dao.updatePointUserSaison(pointUser);
				rang++;
			}
		}
		
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updatePointUserJournee(long idChampionnat, int journee)  throws ApplicationAbdException {
		Journee jj = dao.findJourneeByNumero(idChampionnat, journee);
		
		//1- remove all
		//dao.deletePointUserJournee(idChampionnat, jj.getId());
		
		List<StatUserVO> listStat = userManager.findAllEquipeUserJ(idChampionnat, journee);
		//2-
		if (listStat != null && !listStat.isEmpty()) {
			for (StatUserVO user : listStat) {
				
				PointUserJournee p = new PointUserJournee();
				//p.setIdChampionnat(idChampionnat);
				p.setIdJournee(jj.getId());
				//p.setIdUser(user.getEquipeUser().getId());//id de EquipeUserJ
				p.setNbrPt(user.getTotalPt());
				dao.updatePointUserJournee(user.getEquipeUser().getId(), user.getTotalPt());
				//dao.addPointUserJournee(p);
			}
		}
	}
	

	
	@Override
	public Class<PointUser> getClazz() {
		return PointUser.class;
	}

	@Override
	public PointUserVO convertEntityToVO(PointUser e)
			throws ApplicationAbdException {
		return new PointUserVO(e);
	}

	@Override
	public PointUserDao getDao() {
		return dao;
	}

	

}
