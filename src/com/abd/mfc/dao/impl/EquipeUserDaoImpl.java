package com.abd.mfc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.abd.mfc.dao.EquipeUserDao;
import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserFilter;
import com.abd.mfc.entities.StatJoueur;
import com.abd.mfc.vo.EquipeUserFilterVO;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.ResultPaginateVO;

@Repository
public class EquipeUserDaoImpl extends BaseDaoImpl<EquipeUserFilter, Long> implements
		EquipeUserDao {

	@Override
	public ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO> search(EquipeUserVO filter) {

		List<EquipeUserFilter> result = null;

		Criteria criteria = createCriteria(filter, true);
		
		
		if (filter.getPagination() != null && filter.getPagination().getNombreLigne() > 0) {
			filter.getPagination().setTotalResultat(calculerTotal(filter));
			
		}
		
		setPaginateQuery(criteria, filter.getPagination());
		
		result = criteria.list();
		
		ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO> res = new ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO>(filter.getPagination());
		res.setList(result);
		
		return res;
	}
	
	private Criteria createCriteria(EquipeUserVO filter, boolean enabledSort) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(EquipeUserFilter.class);
		
//		if (filter.getIdUser() > 0) {
//			criteria.add(Restrictions.eq("idUser", filter.getIdUser()));
//		}
		
		if (filter.getId_championnat() > 0) {
			criteria.add(Restrictions.eq("id_championnat", filter.getId_championnat()));
		}
		
//		if (filter.getUsername() != null && !filter.getUsername().isEmpty()) {
//			criteria.add(Restrictions.like("username", filter.getUsername() + "%"));
//		}
		
		if (enabledSort) {
			if (filter.getSortBy() != null && !filter.getSortBy().isEmpty()) {
				if ("pointUserS".equals(filter.getSortBy())) {
					Criteria critPointUserS = criteria.createCriteria("pointUserS");
					critPointUserS.addOrder(Order.desc("nbrPt"));
					critPointUserS.addOrder(Order.asc("rang"));
				} else if ("pointUserP".equals(filter.getSortBy())) {
					//TODO periode
					Criteria critPointUserS = criteria.createCriteria("pointUserS");
					critPointUserS.addOrder(Order.desc("nbrPt"));
					critPointUserS.addOrder(Order.asc("rang"));
				} else if ("pointUserJ".equals(filter.getSortBy())) {
					Criteria critPointUserJ = criteria.createCriteria("pointUserJ");
					critPointUserJ.addOrder(Order.desc("nbrPt"));
					Criteria critPointUserS = criteria.createCriteria("pointUserS");
					critPointUserS.addOrder(Order.asc("rang"));
				}
				
			}
		}
		
		
		
		return criteria;
	}

	/**
	 * 
	 * @param queryStr
	 * @param pagination
	 * @return
	 */
	private void setPaginateQuery(Criteria query, PaginationVO pagination) {
		
		if (pagination != null && pagination.getNombreLigne() > 0
				&& pagination.getTotalResultat() > 0) {
			int nombreLigne = (int) pagination.getNombreLigne();
			
			int fromIndex = (int) (pagination.getNumeroPage() * nombreLigne);
			
			query.setFirstResult(fromIndex); // starting row index
	        query.setMaxResults(nombreLigne); //  end row index
		}
		
	}
	
	/**
	 * calcul total (exec requete count).
	 * @param critere critere
	 * @return total
	 */
	private Integer calculerTotal(EquipeUserVO filter) {
		Integer total = 0;
		
		Criteria criteria = createCriteria(filter, false);
		
		
		total = (Integer)criteria.setProjection(Projections.rowCount()).uniqueResult();
		
		return total;
	}

	@Override
	public void modifierSoldeEquipeUser(long id, int newSolde) {
		EquipeUser eq = entityManager.find(EquipeUser.class, id);
		if (eq != null) {
			eq.setSoldeUser(newSolde);
			entityManager.merge(eq);
		}
		
	}
	
	@Override
	public EquipeUser findEquipeUserByUser(long idChampionnat, long idUser) {
		EquipeUser res = null;
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findEquipeUserByUser");
		q.setLong("id_championnat", idChampionnat);
		q.setLong("id_user", idUser);
		
		List l = q.list();
		
		if (l != null && !l.isEmpty()) {
			res = (EquipeUser) l.get(0);
		}
    	
        return res;
	}
}
