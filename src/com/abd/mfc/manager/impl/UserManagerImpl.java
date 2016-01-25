package com.abd.mfc.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abd.mfc.dao.PointUserDao;
import com.abd.mfc.dao.UserDao;
import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserJ;
import com.abd.mfc.entities.Journee;
import com.abd.mfc.entities.PointUserJournee;
import com.abd.mfc.entities.User;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.EquipeUserManager;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.MessageVO;
import com.abd.mfc.vo.StatUserVO;
import com.abd.mfc.vo.TypeMessage;
import com.abd.mfc.vo.UserVO;

@Service("userManager")
//@Transactional(readOnly = true)
public class UserManagerImpl extends BaseManagerImpl<User, UserVO, Long, UserDao> 
							implements UserManager {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private PointUserDao pointUserDao;
	
	@Autowired
	private JoueurManager joueurManager;
	
	@Autowired
	private EquipeUserManager equipeUserManager;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void add(UserVO entity) throws ApplicationAbdException {
		
		List<MessageVO> list = validateEntity(entity);
		
		if (list.isEmpty()) {
			dao.add(entity.convertToEntity());
		} else {
			ApplicationAbdException e = new ApplicationAbdException();
			e.setMessages(list);
			throw e ;
		}
			
	}
	

	@Override
	public Class<User> getClazz() {
		return User.class;
		
	}

	@Override
	public List<UserVO> getUsers(UserVO filter) {
		//return dao.getUsers(filter);
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public UserVO login(UserVO user) {
		UserVO uLogin = null;
		List<User> res = dao.getUsers(user.convertToEntity());
		if (res != null && !res.isEmpty()) {
			uLogin = new UserVO(res.get(0));
			User uu = dao.get(User.class, uLogin.getId());
			uu.setLastLogin(new Date());
			dao.update(uu);
		}
		
		return uLogin;
	}

	@Override
	public boolean existUser(UserVO filter) {
		
		List<User> list = dao.getUsers(filter.convertToEntity());
		
		return list != null && !list.isEmpty();
	}


	public List<MessageVO> validateEntity(UserVO entity) {
		List<MessageVO> messages = new ArrayList<MessageVO>();
		if (entity != null) {
			//unique username
			UserVO u = new UserVO();
			u.setUsername(entity.getUsername());
			if (existUser(u)) {
				messages.add(new MessageVO(TypeMessage.ERROR, "username existe d�j�."));
			}
			//unique email
			u = new UserVO();
			u.setMail(entity.getMail());
			if (existUser(u)) {
				messages.add(new MessageVO(TypeMessage.ERROR, "adresse email existe d�j�."));
			}
		}
		
		return messages;
	}


	@Override
	public UserVO convertEntityToVO(User e) {
		return new UserVO(e);
	}

	@Override
	public EquipeUserVO getActualEquipeUser(long idUser, long idChampionnat) throws ApplicationAbdException {
		EquipeUserVO res = null;
		EquipeUser eq = dao.getActualEquipeUser(idUser, idChampionnat);
		
		if (eq != null) {
			res = new EquipeUserVO(eq);
			int soldeR = calculerSoldeRestant(res);
			res.setSoldeUserRestant(soldeR);
		}
		
		return res;
	}
	
	@Override
	public void copyEquipeUser(long idChampionnat, int journee)
			throws ApplicationAbdException {
		

		List<EquipeUser> eq = dao.getAllEquipeUser(idChampionnat);
		Journee jj = pointUserDao.findJourneeByNumero(idChampionnat, journee);
		
		if (eq != null && !eq.isEmpty()) {
			for (EquipeUser equipeUser : eq) {
				EquipeUserVO equipeUserJVO = new EquipeUserVO(equipeUser);
				equipeUserJVO.setId_user(equipeUser.getId());
				EquipeUserJ eqj = equipeUserJVO.convertToEntityJ(journee);
				PointUserJournee pointUserJournee = new PointUserJournee();
				pointUserJournee.setIdJournee(jj.getId());
				eqj.setPointUser(pointUserJournee);
				
				dao.saveEquipeUserJ(eqj);
				
				//affect point user journee to user
				dao.affectPointUserJ(eqj.getId_user(), eqj.getPointUser());
			}
		}
		
	
	}
	
	public Long getIdEquipeUser(long idUser, long idChampionnat) throws ApplicationAbdException {
		return dao.getIdEquipeUser(idUser, idChampionnat);
	}
	
	public StatUserVO findEquipeUserJ(long idUser, long idChampionnat, int journee) throws ApplicationAbdException {
		StatUserVO statUser = new  StatUserVO();
		EquipeUserJ eq = dao.findEquipeUserJ(idUser, idChampionnat, journee);
		String totP = "-";
		if (eq != null) {
			EquipeUserVO eqU = new EquipeUserVO(eq);
			statUser.setEquipeUser(eqU);
			statUser.setJournee(eq.getJournee());
			
			List<JoueurVO> stats = joueurManager.findStatEquipe(idChampionnat, eqU , journee);
			statUser.setStatJoueurs(stats);
			/*int totPt = calculerTotalPtJ(stats);
			if (totPt > 0) {
				totP = String.valueOf(totPt);
			}*/
		}
		
		statUser.setTotalPoint(totP);
		
		
		return statUser;
	}
	
	@Override
	public List<StatUserVO> findAllEquipeUserJ(long idChampionnat, int journee)
			throws ApplicationAbdException {
		
		List<StatUserVO> res = null;
		
		List<EquipeUserJ> list = dao.findAllEquipeUserJ(idChampionnat, journee);
		if (list != null && !list.isEmpty()) {
			res = new ArrayList<StatUserVO>();
			
			for (EquipeUserJ eq : list) {
				StatUserVO statUser = new  StatUserVO();
				EquipeUserVO eqU = new EquipeUserVO(eq);
				statUser.setEquipeUser(eqU);
				statUser.setJournee(eq.getJournee());
				
				List<JoueurVO> stats = joueurManager.findStatEquipe(idChampionnat, eqU , journee);
				statUser.setStatJoueurs(stats);
				int totPt = calculerTotalPtJ(stats);
				int diffCout = calculerTotalDiffJ(stats);
				statUser.setTotalPt(totPt);
				statUser.setDiffCout(diffCout);
				res.add(statUser);
				
			}
		}
		
		
		return res;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifierSoldeEquipeUser(long idChampionnat, int journee)
			throws ApplicationAbdException {
		
		//1- rechercher les equipe de la journe (dreniere)
		List<StatUserVO> listStat = findAllEquipeUserJ(idChampionnat, journee);
		
		if (listStat != null && !listStat.isEmpty()) {
			for (StatUserVO user : listStat) {
				if (user.getEquipeUser() != null) {
					int newSoldeUser = user.getEquipeUser().getSoldeUser() + user.getDiffCout();
					equipeUserManager.modifierSoldeEquipeUser(user.getEquipeUser().getId_user(), newSoldeUser);
				}
			}
		}
	}
	
	private int calculerTotalPtJ(List<JoueurVO> stats) {
		int res = 0;
		if (stats != null && !stats.isEmpty()) {
			for (JoueurVO joueurVO : stats) {
				if (joueurVO.getStatJ() != null) {
					res += joueurVO.getStatJ().getTotalP();
				}
			}
		}
		
		return res;
	}
	
	private int calculerTotalDiffJ(List<JoueurVO> stats) {
		int res = 0;
		if (stats != null && !stats.isEmpty()) {
			for (JoueurVO joueurVO : stats) {
				res += joueurVO.getDiffCout();
			}
		}
		
		return res;
	}
	
	private int calculerSoldeRestant(EquipeUserVO equipeUser) {
		int somme = 0;
		
		somme += getCoutJoueur(equipeUser.getGardien());
		
		somme += getCoutJoueur(equipeUser.getDefenseur1());
		somme += getCoutJoueur(equipeUser.getDefenseur2());
		somme += getCoutJoueur(equipeUser.getDefenseur3());
		somme += getCoutJoueur(equipeUser.getDefenseur4());
		
		somme += getCoutJoueur(equipeUser.getMilieu1());
		somme += getCoutJoueur(equipeUser.getMilieu2());
		somme += getCoutJoueur(equipeUser.getMilieu3());
		somme += getCoutJoueur(equipeUser.getMilieu4());
		
		somme += getCoutJoueur(equipeUser.getAtt1());
		somme += getCoutJoueur(equipeUser.getAtt2());
		somme += getCoutJoueur(equipeUser.getAtt3());
		
		return equipeUser.getSoldeUser() - somme;
	}
	
	private int getCoutJoueur(JoueurVO j) {
		int cout = 0;
		if (j != null) {
			cout = j.getCout();
		}
		
		return cout;
	}
	
	
	


	@Override
	public UserDao getDao() {
		return dao;
	}


	

}
