package com.abd.mfc.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.abd.mfc.dao.JoueurDao;
import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.Joueur;
import com.abd.mfc.entities.StatJoueur;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.ResultPaginateVO;

@Repository
public class JoueurDaoImpl extends BaseDaoImpl<Joueur, Long> implements JoueurDao {

	
	@Override
	public List<StatJoueur> findStatJoueur(long idChampionnat, long idJoueur) {
		
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findStatJoueur");
		q.setLong("idChampionnat", idChampionnat);
		q.setLong("idJoueur", idJoueur);
    	
        return q.list();
	}
	
	@Override
	public List<StatJoueur> findStatEquipe(long idChampionnat, List<Long> idsJoueur, int journee) {
		List<StatJoueur> res = null;
		if (idsJoueur != null && !idsJoueur.isEmpty()) {
			Session session = (Session) entityManager.getDelegate();
			Query q = session.getNamedQuery("findStatEquipe");
			q.setLong("idChampionnat", idChampionnat);
			q.setInteger("journee", journee);
			q.setParameterList("idsJoueur", idsJoueur);
	    	
			res = q.list();
		}
		
		
        return res;
	}
	
	@Override
	public List<StatJoueur> findStatJournee(long idChampionnat, int journee) {
		
		Session session = (Session) entityManager.getDelegate();
		Query q = session.getNamedQuery("findStatJournee");
		q.setLong("idChampionnat", idChampionnat);
		q.setInteger("journee", journee);
    	
        return q.list();
	}
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	public ResultPaginateVO<Joueur, JoueurVO> search(JoueurVO filter) {

		List<Joueur> result = null;

		Criteria criteria = createCriteria(filter);
		
		
		if (filter.getPagination() != null && filter.getPagination().getNombreLigne() > 0) {
			filter.getPagination().setTotalResultat(calculerTotal(filter));
			
		}
		
		setPaginateQuery(criteria, filter.getPagination());
		
		result = criteria.list();
		
		ResultPaginateVO<Joueur, JoueurVO> res = new ResultPaginateVO<Joueur, JoueurVO>(filter.getPagination());
		res.setList(result);
		
		return res;
	}
	
	private Criteria createCriteria(JoueurVO filter) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Joueur.class);
		
//		if (filter.getUsername() != null && !filter.getUsername().isEmpty()) {
//			criteria.add(Restrictions.like("username", filter.getUsername() + "%"));
//		}
		Criteria cEq = criteria.createCriteria("equipe");
		
		if (filter.getIdChampionnat() > 0) {
			cEq.add(Restrictions.eq("idChampionnat", filter.getIdChampionnat()));
		}
		
		if (filter.getIdEquipe() > 0) {
			cEq.add(Restrictions.eq("id", filter.getIdEquipe()));
		}
		
		if (filter.getPosition() > 0) {
			criteria.add(Restrictions.eq("position", filter.getPosition()));
		}
		
		if (filter.getSortBy() != null && !filter.getSortBy().isEmpty()) {
			criteria.addOrder(Order.desc(filter.getSortBy()));
			if (!"totalPoint".equals(filter.getSortBy())) {
				criteria.addOrder(Order.desc("totalPoint"));
			}
		} else {
			criteria.addOrder(Order.desc("totalPoint"));
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
	private Integer calculerTotal(JoueurVO filter) {
		Integer total = 0;
		
		Criteria criteria = createCriteria(filter);
		
		
		total = (Integer)criteria.setProjection(Projections.rowCount()).uniqueResult();
		
		return total;
	}

	@Override
	public EquipeUser saveFormationJoueur(EquipeUser equipeUser) {
		entityManager.merge(equipeUser);
		
		return equipeUser;
	}
	
	@Override
	public void updateStat(StatJoueur entity) {
		entityManager.merge(entity);

	}
	
	@Override
	public void updateTotalPointJoueur() {
		Session session = (Session) entityManager.getDelegate();
		
		entityManager.createQuery(session.getNamedQuery("updateTotalPointJoueur").getQueryString()).executeUpdate();
//		entityManager.createQuery(session.getNamedQuery("updateTotalPointJoueurMySQL").getQueryString()).executeUpdate();
	}
	
	@Override
	public void updatePointJourneeJoueur(long idChampionnat, int journee) {
		Session session = (Session) entityManager.getDelegate();
		
		Query q = session.getNamedQuery("updatePointJourneeJoueur");
		q.setLong("idChampionnat", idChampionnat);
		q.setInteger("journee", journee);
		
		q.executeUpdate();
		
//		entityManager.createQuery(session.getNamedQuery("updateTotalPointJoueurMySQL").getQueryString()).executeUpdate();
	}
	
	@Override
	public List<Joueur> findJoueurChampionnat(long idChampionnat) {
		Session session = (Session) entityManager.getDelegate();
		
		Query q = session.getNamedQuery("findJoueurChampionnat");
		q.setLong("idChampionnat", idChampionnat);
		
		return q.list();
		
	}
	
	@Override
	public Long findIdJoueurByNumero(long idEquipe, int numero) {
		
		Long res = null;
		Session session = (Session) entityManager.getDelegate();
		
		Query q = session.getNamedQuery("findIdJoueurByNumero");
		q.setLong("idEquipe", idEquipe);
		q.setInteger("numero", numero);
		
		List list = q.list();
		if (list != null && list.size() == 1) {
			res = (Long) list.get(0);
		}
		
		return res;
		
	}
	
	@Override
	public void insertStatJ(StatJoueur entity) {
		entityManager.persist(entity);
	}
}
