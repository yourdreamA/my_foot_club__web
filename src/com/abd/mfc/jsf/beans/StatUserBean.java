package com.abd.mfc.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.jsf.model.PositionJoueurMO;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.StatUserVO;

@Controller
@Scope("view")
public class StatUserBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1896541287999L;

	private StatUserVO statUser;
	
	@Autowired
	private JoueurManager joueurManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private SessionBean sessionBean;
	
	@Autowired
	private LoginBean loginBean;
	
	@Autowired
	 private LocaleBean localeBean;
	
	private String showType;
	
	private int selectedJournee;
	
	private long idSelectedUser;
	
	@PostConstruct
	public void init() throws ApplicationAbdException {
		selectedJournee = sessionBean.getSelectedChampionnat().getActualJournee();
		showType = "terrain";
	}
	
	public void initEquipe() throws ApplicationAbdException {
		if (loginBean.getConnectedUser() != null) {
			idSelectedUser = userManager.getIdEquipeUser(loginBean.getConnectedUser().getId(), 
					sessionBean.getSelectedChampionnat().getId());
			doSearch();
		}
		
	}
	
	public String doSearch() throws ApplicationAbdException {
		
		statUser = userManager.findEquipeUserJ(
				idSelectedUser, 
				sessionBean.getSelectedChampionnat().getId(), 
				selectedJournee);
		
		return null;
	}
	
	public String doSearchPublic() throws ApplicationAbdException {
		
		statUser = userManager.findEquipeUserJ(
				idSelectedUser, 
				sessionBean.getSelectedChampionnat().getId(), 
				selectedJournee);
		
		return null;
	}

	
	
	
	
	/****************************************************************************************
	 * **************************************************************************************
	 **************************************     Popup Joueur   ******************************/        

	
	@Autowired
	private ApplicationBean appBean;
	
	private PositionJoueurMO position;
	private int indicePosition;
	private List<JoueurVO> listJoueur;
	private List<JoueurVO>[] listJoueurPage;
	
	private PaginationVO pagination;
	
	private int joueurPos;
	private String joueurRenderId;
	
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

	public int getJoueurPos() {
		return joueurPos;
	}

	public void setJoueurPos(int joueurPos) {
		this.joueurPos = joueurPos;
	}

	public String getJoueurRenderId() {
		return joueurRenderId;
	}

	public void setJoueurRenderId(String joueurRenderId) {
		this.joueurRenderId = joueurRenderId;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		System.out.println("new value : " + showType);
		this.showType = showType;
	}

	public StatUserVO getStatUser() {
		return statUser;
	}

	public void setStatUser(StatUserVO statUser) {
		this.statUser = statUser;
	}

	public int getSelectedJournee() {
		return selectedJournee;
	}

	public void setSelectedJournee(int selectedJournee) {
		this.selectedJournee = selectedJournee;
	}

	public long getIdSelectedUser() {
		return idSelectedUser;
	}

	public void setIdSelectedUser(long idSelectedUser) {
		this.idSelectedUser = idSelectedUser;
	}


	

	
}

