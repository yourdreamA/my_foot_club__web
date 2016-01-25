package com.abd.mfc.manager;

import com.abd.mfc.dao.PointUserDao;
import com.abd.mfc.entities.PointUser;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.PointUserVO;
import com.abd.mfc.vo.ResultPaginateVO;

public interface PointUserManager extends BaseManager<PointUser, PointUserVO, Long, PointUserDao> {
//	ResultPaginateVO<PointUser, PointUserVO> search(PointUserVO filter) throws ApplicationAbdException;

	void updatePointUserSaison(long idChampionnat) throws ApplicationAbdException;

	void updatePointUserJournee(long idChampionnat, int journee)
			throws ApplicationAbdException;
}
