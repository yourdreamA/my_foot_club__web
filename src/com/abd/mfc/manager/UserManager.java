package com.abd.mfc.manager;

import java.util.List;

import com.abd.mfc.dao.UserDao;
import com.abd.mfc.entities.User;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.StatUserVO;
import com.abd.mfc.vo.UserVO;

public interface UserManager extends BaseManager<User, UserVO, Long, UserDao> {
	List<UserVO> getUsers(UserVO filter);
	UserVO login(UserVO user);
	boolean existUser(UserVO filter);
	EquipeUserVO getActualEquipeUser(long idUser, long idChampionnat)
			throws ApplicationAbdException;
	
	StatUserVO findEquipeUserJ(long idUser, long idChampionnat, int journee)throws ApplicationAbdException;
	void copyEquipeUser(long idChampionnat, int journee)
			throws ApplicationAbdException;
	
	List<StatUserVO> findAllEquipeUserJ(long idChampionnat, int journee)throws ApplicationAbdException;
	
	Long getIdEquipeUser(long idUser, long idChampionnat) throws ApplicationAbdException;
	void modifierSoldeEquipeUser(long idChampionnat, int journee)
			throws ApplicationAbdException;
}
