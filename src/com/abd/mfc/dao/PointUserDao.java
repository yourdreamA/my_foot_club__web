package com.abd.mfc.dao;

import java.util.List;

import com.abd.mfc.entities.Journee;
import com.abd.mfc.entities.PointUser;
import com.abd.mfc.entities.PointUserJournee;
import com.abd.mfc.entities.User;
import com.abd.mfc.vo.PointUserVO;
import com.abd.mfc.vo.ResultPaginateVO;

public interface PointUserDao extends BaseDao<PointUser, Long> {

	List<User> findUserWithoutPoint(long idChampionnat);

	List<PointUser> calculPointUserSaison(long idChampionnat);

	Journee findJourneeByNumero(long idChampionnat, int num);


	void addPointUserJournee(PointUserJournee p);

	void updatePointUserJournee(long idUser, int nbrPt);

	void updatePointUserSaison(PointUser pointUser);

}
