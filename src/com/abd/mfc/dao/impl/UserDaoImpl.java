package com.abd.mfc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.abd.mfc.dao.UserDao;
import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserFilter;
import com.abd.mfc.entities.EquipeUserJ;
import com.abd.mfc.entities.Journee;
import com.abd.mfc.entities.PointUser;
import com.abd.mfc.entities.PointUserJournee;
import com.abd.mfc.entities.User;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.PointUserVO;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.mfc.vo.UserVO;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

	public List<User> getUsers(User filter) {

		Session session = (Session) entityManager.getDelegate();
		Example userExample = Example.create(filter);
		Criteria criteria = session.createCriteria(User.class).add(userExample);

		List<User> resultList = criteria.list();

		return resultList;
	}
	
	



	@Override
	public EquipeUser getActualEquipeUser(long idUser, long idChampionnat) {

		EquipeUser res = null;
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findEquipeUserByUser");
		q.setLong("id_user", idUser);
		q.setLong("id_championnat", idChampionnat);
    	List list = q.list();
    	if (list != null && !list.isEmpty()) {
    		res = (EquipeUser) list.get(0);
    	}
    	
        return res;
	
	}
	
	@Override
	public Long getIdEquipeUser(long idUser, long idChampionnat) {

		Long res = null;
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findIdEquipeUser");
		q.setLong("id_user", idUser);
		q.setLong("id_championnat", idChampionnat);
    	List list = q.list();
    	if (list != null && !list.isEmpty()) {
    		res = (Long) list.get(0);
    	}
    	
        return res;
	
	}
	
	@Override
	public List<EquipeUser> getAllEquipeUser(long idChampionnat) {

		List<EquipeUser> res = null;
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findAllEquipeUser");
		q.setLong("id_championnat", idChampionnat);
    	res = q.list();
    	
        return res;
	
	}
	
	@Override
	public EquipeUserJ findEquipeUserJ(long idUser, long idChampionnat, int journee) {

		EquipeUserJ res = null;
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findEquipeUserJ");
		q.setLong("id_user", idUser);
		q.setLong("id_championnat", idChampionnat);
		q.setLong("journee", journee);
    	List list = q.list();
    	if (list != null && !list.isEmpty()) {
    		res = (EquipeUserJ) list.get(0);
    	}
    	
        return res;
	
	}
	
	@Override
	public List<EquipeUserJ> findAllEquipeUserJ(long idChampionnat, int journee) {

		EquipeUserJ res = null;
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findAllEquipeUserJ");
		q.setLong("id_championnat", idChampionnat);
		q.setLong("journee", journee);
    	List list = q.list();
    	
        return list;
	
	}
	
	@Override
	public void saveEquipeUserJ(EquipeUserJ e) {
		entityManager.persist(e);
	}
	
	@Override
	public void affectPointUserJ(long idUser, PointUserJournee pt) {
		EquipeUserFilter eq = entityManager.find(EquipeUserFilter.class, idUser);
		eq.setPointUserJ(pt);
		entityManager.merge(eq);
	}
	@Override
	public Journee findJourneeByNumero(long idChampionnat, int num) {
		
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findJourneeByNumero");
		q.setLong("idChampionnat", idChampionnat);
		q.setInteger("numero", num);
    	
        return (Journee) q.list().get(0);
	}

}
