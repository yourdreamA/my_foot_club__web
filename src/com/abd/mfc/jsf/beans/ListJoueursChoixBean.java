package com.abd.mfc.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.entities.Joueur;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.jsf.model.PositionJoueurMO;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.ResultPaginateVO;

@Controller("lstJrChxBean")
@Scope("view")
public class ListJoueursChoixBean extends BaseBean {
	
	@Autowired
	private JoueurManager joueurManager;
	
	@Autowired
	private SessionBean sessionBean;
	
	@Autowired
	private ApplicationBean appBean;
	
	private PositionJoueurMO position;
	private int indicePosition;
	private List<JoueurVO> listJoueur;
	private List<JoueurVO>[] listJoueurPage;
	
	private PaginationVO pagination;
	
	private int joueurPos;
	
	private int equipeIdFilter;
	
	public void filterList() {
		
	}
	
	public void doSearch(PositionJoueurMO pos) throws ApplicationAbdException {
		position = pos;
		//position = appBean.getListPositionJoueur()[indicePosition];
		ResultPaginateVO<Joueur, JoueurVO> resultat = null;
		JoueurVO filter = new JoueurVO();
		filter.setPosition(position.getValeur());
		filter.setIdChampionnat(sessionBean.getSelectedChampionnat().getId());
		
		try {
			
			resultat = joueurManager.search(filter);
		} catch (ApplicationAbdException e) {
			e.printStackTrace();
		}
		
		listJoueur = resultat.getListVO();
		
//		initPagination();
		
	}
	
	
	public void changePage(int p) {
		System.out.println("pagination = " + p);
		pagination.setNumeroPage(p);
	}
	
	public void initPagination() {
		pagination = new PaginationVO(5);
		pagination.setNumeroPage(1);
		int size = listJoueur.size();
		int nbPage = size / pagination.getNombreLigne();
		if (size % pagination.getNombreLigne() > 0) {
			nbPage++;
		}
		pagination.setTotalPage(nbPage);
		listJoueurPage = new List[nbPage];
		
		
		int indice = 0;
		
		for (int i = 0; i < nbPage; i++) {
			List<JoueurVO> listJoueurParPage = new ArrayList<JoueurVO>(pagination.getNombreLigne());
			for (int j = 0; j < pagination.getNombreLigne() && indice < size; j++) {
				listJoueurParPage.add(listJoueur.get(indice++));
			}
			listJoueurPage[i] = listJoueurParPage;
		}
		
	}

	public PositionJoueurMO getPosition() {
		return position;
	}

	public void setPosition(PositionJoueurMO position) {
		this.position = position;
	}

	public List<JoueurVO> getListJoueur() {
		return listJoueur;
	}

	public void setListJoueur(List<JoueurVO> listJoueur) {
		this.listJoueur = listJoueur;
	}

	

	public List<JoueurVO>[] getListJoueurPage() {
		return listJoueurPage;
	}

	public PaginationVO getPagination() {
		return pagination;
	}

	public void setPagination(PaginationVO pagination) {
		this.pagination = pagination;
	}


	public int getIndicePosition() {
		return indicePosition;
	}


	public void setIndicePosition(int indicePosition) {
		this.indicePosition = indicePosition;
	}


	public int getEquipeIdFilter() {
		return equipeIdFilter;
	}


	public void setEquipeIdFilter(int equipeIdFilter) {
		this.equipeIdFilter = equipeIdFilter;
	}
}
