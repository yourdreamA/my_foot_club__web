package com.abd.mfc.dao;

import java.util.List;

import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserJ;
import com.abd.mfc.entities.Journee;
import com.abd.mfc.entities.PointUserJournee;
import com.abd.mfc.entities.User;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.mfc.vo.UserVO;

public interface UserDao extends BaseDao<User, Long> {
	List<User> getUsers(User example);
	EquipeUser getActualEquipeUser(long idUser, long idChampionnat);
	EquipeUserJ findEquipeUserJ(long idUser, long idChampionnat, int journee);
	List<EquipeUser> getAllEquipeUser(long idChampionnat);
	void saveEquipeUserJ(EquipeUserJ e);
	List<EquipeUserJ> findAllEquipeUserJ(long idChampionnat, int journee);
	void affectPointUserJ(long idUser, PointUserJournee pt);
	Long getIdEquipeUser(long idUser, long idChampionnat);
	Journee findJourneeByNumero(long idChampionnat, int num);
}
