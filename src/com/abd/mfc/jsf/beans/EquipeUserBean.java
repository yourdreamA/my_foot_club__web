package com.abd.mfc.jsf.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.abd.mfc.entities.Joueur;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.jsf.model.FormationEnum;
import com.abd.mfc.jsf.model.PositionJoueurMO;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.util.Utils;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.MessageVO;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.PointUserVO;
import com.abd.mfc.vo.ResultPaginateVO;


import static ch.lambdaj.Lambda.*;

@Controller
@Scope("view")
public class EquipeUserBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 178522249L;

	private EquipeUserVO equipeUser;
	
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
	
	@PostConstruct
	public void init() throws ApplicationAbdException {
		initEquipe();
	}
	
	public void initEquipe() throws ApplicationAbdException {
		equipeUser = userManager.getActualEquipeUser(loginBean.getConnectedUser().getId(), sessionBean.getSelectedChampionnat().getId());
		if (equipeUser == null) {
			equipeUser = new EquipeUserVO();
			equipeUser.setFormation(FormationEnum.Q_Q_D);
			equipeUser.setId_championnat(sessionBean.getSelectedChampionnat().getId());
			equipeUser.setId_user(loginBean.getConnectedUser().getId());
			if (loginBean.getConnectedUser().getLogin() != null) {
				equipeUser.setNom(loginBean.getConnectedUser().getLogin().getUsername());
			}
			equipeUser.setSoldeUser(1000);
			equipeUser.setSoldeUserRestant(1000);
			
			PointUserVO p = new PointUserVO();
			p.setNbrPt(0);
			equipeUser.setPointUserS(p);
		}
	}
	
	public void supprimerJoueur(int position) {
		int solde = 0;
		switch (position) {
		case 1:solde = equipeUser.getGardien().getCout();equipeUser.setGardien(null);break;
		
		case 2:solde = equipeUser.getDefenseur1().getCout();equipeUser.setDefenseur1(null);break;
		case 3:solde = equipeUser.getDefenseur2().getCout();equipeUser.setDefenseur2(null);break;
		case 4:solde = equipeUser.getDefenseur3().getCout();equipeUser.setDefenseur3(null);break;
		case 5:solde = equipeUser.getDefenseur4().getCout();equipeUser.setDefenseur4(null);break;
		
		case 6:solde = equipeUser.getMilieu1().getCout();equipeUser.setMilieu1(null);break;
		case 7:solde = equipeUser.getMilieu2().getCout();equipeUser.setMilieu2(null);break;
		case 8:solde = equipeUser.getMilieu3().getCout();equipeUser.setMilieu3(null);break;
		case 9:solde = equipeUser.getMilieu4().getCout();equipeUser.setMilieu4(null);break;
		
		case 10:solde = equipeUser.getAtt1().getCout();equipeUser.setAtt1(null);break;
		case 11:solde = equipeUser.getAtt2().getCout();equipeUser.setAtt2(null);break;
		case 12:solde = equipeUser.getAtt3().getCout();equipeUser.setAtt3(null);break;

		default:
			break;
		}
		
		equipeUser.setSoldeUserRestant(equipeUser.getSoldeUserRestant() + solde);
	}
	
	public void changeFormation() {
		
		equipeUser.setGardien(null);

		equipeUser.setDefenseur1(null);
		equipeUser.setDefenseur2(null);
		equipeUser.setDefenseur3(null);
		equipeUser.setDefenseur4(null);

		equipeUser.setMilieu1(null);
		equipeUser.setMilieu2(null);
		equipeUser.setMilieu3(null);
		equipeUser.setMilieu4(null);

		equipeUser.setAtt1(null);
		equipeUser.setAtt2(null);
		equipeUser.setAtt3(null);
		
		equipeUser.setSoldeUserRestant(equipeUser.getSoldeUser());
	}
	
	public void enregistrerFormation() throws ApplicationAbdException {
		List<MessageVO> listErrors = joueurManager.validateFormationJoueur(equipeUser);
		
		if (!listErrors.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (MessageVO m : listErrors) {
				sb.append(" - ")
					.append(ResourceBundle.getBundle("com.abd.mfc.i18n.messages", localeBean.getLanguage().getLocale()).getString(m.getContent()))
					.append("<br>");
		        
			}
			FacesMessage msg = new FacesMessage(sb.toString(), "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage("formation_form", msg);
	        
			throw new ValidatorException(msg);
		}
		
		EquipeUserVO result = joueurManager.saveFormationJoueur(equipeUser);
		
		initEquipe();
	}

	public EquipeUserVO getEquipeUser() {
		return equipeUser;
	}

	public void setEquipeUser(EquipeUserVO equipeUser) {
		this.equipeUser = equipeUser;
	}

	
	
	
	
	/****************************************************************************************
	 * **************************************************************************************
	 **************************************     Popup Joueur   ******************************/        

	
	@Autowired
	private ApplicationBean appBean;
	
	private PositionJoueurMO position;
	private int indicePosition;
	//private List<JoueurVO> listJoueur;
	private List<JoueurVO> listJoueurFilter;
	private List<JoueurVO>[] listJoueurPage;
	
	private PaginationVO pagination;
	
	private int joueurPos;
	private String joueurRenderId;
	private long equipeIdFilter;
	private String sortJBy;
	
	public void doSearch(PositionJoueurMO pos, int joueurPos, int operation, String joueurRenderId) throws ApplicationAbdException {
		position = pos;
		this.joueurPos = joueurPos;
		this.joueurRenderId = joueurRenderId;
		equipeIdFilter = 0;
		sortJBy = "1";

		doSearch();
	}
	
	private void doSearch() throws ApplicationAbdException {
		JoueurVO filter = new JoueurVO();
		filter.setPosition(position.getValeur());
		filter.setIdChampionnat(sessionBean.getSelectedChampionnat().getId());
		
		if (equipeIdFilter > 0) {
			filter.setIdEquipe(equipeIdFilter);
		}

		listJoueurFilter = Utils.filterJoueur(appBean.getListJoueur(), filter);
		
		disabledExistJoueurs();
		
		sortList();
	}
	
	public void valueFilterChanged() throws ApplicationAbdException {
		doSearch();
    }

	
	public void sortList() {
		Object orderBy = on(JoueurVO.class).getTotalPoint();
		int option = DESCENDING;
		if ("2".equals(sortJBy)) {
			if (localeBean.getLanguage().isArabic()) {
				orderBy = on(JoueurVO.class).getNomJ_ar();
			} else {
				orderBy = on(JoueurVO.class).getNomJ();
			}
			option = IGNORE_CASE;
		} else if ("3".equals(sortJBy)) {
			orderBy = on(JoueurVO.class).getCout();
		} else if ("4".equals(sortJBy)) {
			orderBy = on(JoueurVO.class).getIdEquipe();
		}
		
		
		listJoueurFilter = sort(listJoueurFilter, orderBy, option);
		
		
		
	}
	
	

	
	private void _disabledJoueurs(List<JoueurVO> listJoueur) {
		
		for (JoueurVO j : listJoueur) {
			if (position.getValeur() == 1) {
				if (equipeUser.getGardien() != null && equipeUser.getGardien().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				}
			} else if (position.getValeur() == 2) {
				if (equipeUser.getDefenseur1() != null && equipeUser.getDefenseur1().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				} else if (equipeUser.getDefenseur2() != null && equipeUser.getDefenseur2().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				} else if (equipeUser.getDefenseur3() != null && equipeUser.getDefenseur3().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				} else if (equipeUser.getDefenseur4() != null && equipeUser.getDefenseur4().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				}
			} else if (position.getValeur() == 3) {
				if (equipeUser.getMilieu1() != null && equipeUser.getMilieu1().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				} else if (equipeUser.getMilieu2() != null && equipeUser.getMilieu2().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				} else if (equipeUser.getMilieu3() != null && equipeUser.getMilieu3().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				} else if (equipeUser.getMilieu4() != null && equipeUser.getMilieu4().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				}
			} else if (position.getValeur() == 4) {
				if (equipeUser.getAtt1() != null && equipeUser.getAtt1().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				} else if (equipeUser.getAtt2() != null && equipeUser.getAtt2().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				} else if (equipeUser.getAtt3() != null && equipeUser.getAtt3().getId() == j.getId()) {
					j.setAppartienEquipe(true);
				}
			}
		}
	}
	
	private void disabledExistJoueurs() {
		
		for (Iterator iterator = listJoueurFilter.iterator(); iterator.hasNext();) {
			JoueurVO j = (JoueurVO) iterator.next();

			if (position.getValeur() == 1) {
				if (equipeUser.getGardien() != null && equipeUser.getGardien().getId() == j.getId()) {
					iterator.remove();
				}
			} else if (position.getValeur() == 2) {
				if (equipeUser.getDefenseur1() != null && equipeUser.getDefenseur1().getId() == j.getId()) {
					iterator.remove();
				} else if (equipeUser.getDefenseur2() != null && equipeUser.getDefenseur2().getId() == j.getId()) {
					iterator.remove();
				} else if (equipeUser.getDefenseur3() != null && equipeUser.getDefenseur3().getId() == j.getId()) {
					iterator.remove();
				} else if (equipeUser.getDefenseur4() != null && equipeUser.getDefenseur4().getId() == j.getId()) {
					iterator.remove();
				}
			} else if (position.getValeur() == 3) {
				if (equipeUser.getMilieu1() != null && equipeUser.getMilieu1().getId() == j.getId()) {
					iterator.remove();
				} else if (equipeUser.getMilieu2() != null && equipeUser.getMilieu2().getId() == j.getId()) {
					iterator.remove();
				} else if (equipeUser.getMilieu3() != null && equipeUser.getMilieu3().getId() == j.getId()) {
					iterator.remove();
				} else if (equipeUser.getMilieu4() != null && equipeUser.getMilieu4().getId() == j.getId()) {
					iterator.remove();
				}
			} else if (position.getValeur() == 4) {
				if (equipeUser.getAtt1() != null && equipeUser.getAtt1().getId() == j.getId()) {
					iterator.remove();
				} else if (equipeUser.getAtt2() != null && equipeUser.getAtt2().getId() == j.getId()) {
					iterator.remove();
				} else if (equipeUser.getAtt3() != null && equipeUser.getAtt3().getId() == j.getId()) {
					iterator.remove();
				}
			}
		}
	}
	
	private boolean existInTeam(JoueurVO j) {
		
		boolean exist = false;
		
			if (position.getValeur() == 1) {
				if (equipeUser.getGardien() != null && equipeUser.getGardien().getId() == j.getId()) {
					return true;
				}
			} else if (position.getValeur() == 2) {
				if (equipeUser.getDefenseur1() != null && equipeUser.getDefenseur1().getId() == j.getId()) {
					return true;
				} else if (equipeUser.getDefenseur2() != null && equipeUser.getDefenseur2().getId() == j.getId()) {
					return true;
				} else if (equipeUser.getDefenseur3() != null && equipeUser.getDefenseur3().getId() == j.getId()) {
					return true;
				} else if (equipeUser.getDefenseur4() != null && equipeUser.getDefenseur4().getId() == j.getId()) {
					return true;
				}
			} else if (position.getValeur() == 3) {
				if (equipeUser.getMilieu1() != null && equipeUser.getMilieu1().getId() == j.getId()) {
					return true;
				} else if (equipeUser.getMilieu2() != null && equipeUser.getMilieu2().getId() == j.getId()) {
					return true;
				} else if (equipeUser.getMilieu3() != null && equipeUser.getMilieu3().getId() == j.getId()) {
					return true;
				} else if (equipeUser.getMilieu4() != null && equipeUser.getMilieu4().getId() == j.getId()) {
					return true;
				}
			} else if (position.getValeur() == 4) {
				if (equipeUser.getAtt1() != null && equipeUser.getAtt1().getId() == j.getId()) {
					return true;
				} else if (equipeUser.getAtt2() != null && equipeUser.getAtt2().getId() == j.getId()) {
					return true;
				} else if (equipeUser.getAtt3() != null && equipeUser.getAtt3().getId() == j.getId()) {
					return true;
				}
			}
		
		return exist;
	}
	
	public void updateJoueur(JoueurVO j) {
		/*if (existInTeam(j)) {
			FacesMessage msg = new FacesMessage(
					ResourceBundle.getBundle("com.abd.mfc.i18n.messages", localeBean.getLanguage().getLocale()).getString("error_equipe_joueur_exist"),
					"ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
			throw new ValidatorException(msg);
		}*/
		
		int solde = equipeUser.getSoldeUserRestant() - j.getCout();
		
		switch (joueurPos) {
		case 1:
			if (equipeUser.getGardien() != null) {
				solde += equipeUser.getGardien().getCout();
			}
			break;

		case 2:
			if (equipeUser.getDefenseur1() != null) {
				solde += equipeUser.getDefenseur1().getCout();
			}
			break;
		case 3:
			if (equipeUser.getDefenseur2() != null) {
				solde += equipeUser.getDefenseur2().getCout();
			}
			break;
		case 4:
			if (equipeUser.getDefenseur3() != null) {
				solde += equipeUser.getDefenseur3().getCout();
			}
			break;
		case 5:
			if (equipeUser.getDefenseur4() != null) {
				solde += equipeUser.getDefenseur4().getCout();
			}
			break;

		case 6:
			if (equipeUser.getMilieu1() != null) {
				solde += equipeUser.getMilieu1().getCout();
			}
			break;
		case 7:
			if (equipeUser.getMilieu2() != null) {
				solde += equipeUser.getMilieu2().getCout();
			}
			break;
		case 8:
			if (equipeUser.getMilieu3() != null) {
				solde += equipeUser.getMilieu3().getCout();
			}
			break;
		case 9:
			if (equipeUser.getMilieu4() != null) {
				solde += equipeUser.getMilieu4().getCout();
			}
			break;

		case 10:
			if (equipeUser.getAtt1() != null) {
				solde += equipeUser.getAtt1().getCout();
			}
			break;
		case 11:
			if (equipeUser.getAtt2() != null) {
				solde += equipeUser.getAtt2().getCout();
			}
			break;
		case 12:
			if (equipeUser.getAtt3() != null) {
				solde += equipeUser.getAtt3().getCout();
			}
			break;

		default:
			break;
		}
		
		if (solde >= 0) {
			equipeUser.setSoldeUserRestant(solde);
			switch (joueurPos) {
			case 1:equipeUser.setGardien(j);break;
			
			case 2:equipeUser.setDefenseur1(j);break;
			case 3:equipeUser.setDefenseur2(j);break;
			case 4:equipeUser.setDefenseur3(j);break;
			case 5:equipeUser.setDefenseur4(j);break;
			
			case 6:equipeUser.setMilieu1(j);break;
			case 7:equipeUser.setMilieu2(j);break;
			case 8:equipeUser.setMilieu3(j);break;
			case 9:equipeUser.setMilieu4(j);break;
			
			case 10:equipeUser.setAtt1(j);break;
			case 11:equipeUser.setAtt2(j);break;
			case 12:equipeUser.setAtt3(j);break;

			default:
				break;
			}
		} else {
			FacesMessage msg = new FacesMessage(
					ResourceBundle.getBundle("com.abd.mfc.i18n.messages", localeBean.getLanguage().getLocale()).getString("error_equipe_joueur_solde"),
					"ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
			throw new ValidatorException(msg);
		}
	}
	
	
	/*public void changePage(int p) {
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
		
	}*/

	public PositionJoueurMO getPosition() {
		return position;
	}

	public void setPosition(PositionJoueurMO position) {
		this.position = position;
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

	public long getEquipeIdFilter() {
		return equipeIdFilter;
	}

	public void setEquipeIdFilter(long equipeIdFilter) {
		this.equipeIdFilter = equipeIdFilter;
	}

	public String getSortJBy() {
		return sortJBy;
	}

	public void setSortJBy(String sortJBy) {
		this.sortJBy = sortJBy;
	}

	public List<JoueurVO> getListJoueurFilter() {
		return listJoueurFilter;
	}

}
