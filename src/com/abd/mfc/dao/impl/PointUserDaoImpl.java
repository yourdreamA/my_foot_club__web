package com.abd.mfc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.abd.mfc.dao.PointUserDao;
import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserJ;
import com.abd.mfc.entities.Journee;
import com.abd.mfc.entities.PointUser;
import com.abd.mfc.entities.PointUserJournee;
import com.abd.mfc.entities.StatJoueur;
import com.abd.mfc.entities.User;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.PointUserVO;
import com.abd.mfc.vo.ResultPaginateVO;

@Repository
public class PointUserDaoImpl extends BaseDaoImpl<PointUser, Long> implements
		PointUserDao {


	
	@Override
	public void updatePointUserSaison(PointUser pointUser) {
		
		EquipeUser eq = entityManager.find(EquipeUser.class, pointUser.getIdUser());
		eq.getPointUserS().setNbrPt(pointUser.getNbrPt());
		eq.getPointUserS().setRang(pointUser.getRang());
		entityManager.merge(eq);
	}
	
	@Override
	public List<User> findUserWithoutPoint(long idChampionnat) {
		
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findUserWithoutPoint");
		q.setLong("idChampionnat", idChampionnat);
    	
        return q.list();
	}
	
	@Override
	public List<PointUser> calculPointUserSaison(long idChampionnat) {
		List<PointUser> res = null;
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("calculPointUserSaison");
		q.setLong("idChampionnat", idChampionnat);
    	List<Object[]> list = q.list();
    	
    	if (list != null && !list.isEmpty()) {
    		res = new ArrayList<PointUser>();
    		for (Object[] tab : list) {
				long idUser = (Long) tab[0];
				long nbrPt = (Long) tab[1];
				PointUser p = new PointUser();
				p.setIdUser(idUser);
				p.setNbrPt((int) nbrPt);
				res.add(p);
			}
    	}
    	
        return res;
	}
	
	@Override
	public Journee findJourneeByNumero(long idChampionnat, int num) {
		
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findJourneeByNumero");
		q.setLong("idChampionnat", idChampionnat);
		q.setInteger("numero", num);
    	
        return (Journee) q.list().get(0);
	}
	
	@Override
	public void updatePointUserJournee(long idUser, int nbrPt) {
		EquipeUserJ eq = entityManager.find(EquipeUserJ.class, idUser);
		eq.getPointUser().setNbrPt(nbrPt);
		entityManager.merge(eq);
	}

	@Override
	public void addPointUserJournee(PointUserJournee p) {
		entityManager.persist(p);
		
	}
	
	


}
