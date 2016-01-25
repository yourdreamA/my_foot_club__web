package com.abd.mfc.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.entities.Joueur;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.PaginationEnum;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.ResultPaginateVO;

@Controller
@Scope("view")
public class ListJoueurBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4853L;
	private List<JoueurVO> listJoueur;
	private JoueurVO filter;
	
	@Autowired
    private JoueurManager joueurManager;
	
	@Autowired
	private SessionBean sessionBean;
	
	
	@PostConstruct
	public void init() {
		filter = new JoueurVO();
//		filter.setIdSaison(Long.valueOf(1));
//		filter.setIdChampionnat(1);
//		filter.setTypePt(TypePointEnum.TOT_SAISON);
//		filter.setSortBy("nbrPt");
		
		PaginationVO pagination = new PaginationVO(30);
		filter.setPagination(pagination);
		filter.setIdChampionnat(sessionBean.getSelectedChampionnat().getId());
		filter.setSortBy("totalPoint");
		
		doSearch();
	}
	
	public String doSearch(String action) {
		if (PaginationEnum.FIRST.getCode().equals(action)) {
			filter.getPagination().setNumeroPage(0);
		} else if (PaginationEnum.PREVIOUS.getCode().equals(action)) {
			filter.getPagination().setNumeroPage(filter.getPagination().getNumeroPage() - 1);
		} else if (PaginationEnum.NEXT.getCode().equals(action)) {
			filter.getPagination().setNumeroPage(filter.getPagination().getNumeroPage() + 1);
		} else if (PaginationEnum.LAST.getCode().equals(action)) {
			filter.getPagination().setNumeroPage(filter.getPagination().getTotalPage() - 1);
		}
		
		return doSearch();
	}
	
	public String doSearch(int page) {
		filter.getPagination().setNumeroPage(page);
		
		return doSearch();
	}
	
	public String doSearch() {
		
		ResultPaginateVO<Joueur, JoueurVO> resultat = null;
		
		try {
			
			resultat = joueurManager.search(filter);
		} catch (ApplicationAbdException e) {
			e.printStackTrace();
		}
		if (resultat != null && resultat.getListVO() != null && !resultat.getListVO().isEmpty()) {
			listJoueur = new ArrayList<JoueurVO>();
			for (JoueurVO vo : resultat.getListVO()) {
				listJoueur.add(vo);
			}
		} else {
			listJoueur = null;
		}
		
		filter.setPagination(resultat.getP());
		
		return null;
	}

	public JoueurVO getFilter() {
		return filter;
	}

	public void setFilter(JoueurVO filter) {
		this.filter = filter;
	}

	public List<JoueurVO> getListJoueur() {
		return listJoueur;
	}
	
}
