package com.abd.mfc.jsf.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.manager.ParamGenerauxManager;
import com.abd.mfc.manager.PointUserManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.ChampionnatVO;

@Controller
@Scope("view")
public class AdminBatchBean extends BaseBean {

	@Autowired
	private JoueurManager joueurManager;
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private PointUserManager pointUserManager;
	
	@Autowired
	private ApplicationBean applicationBean;
	
	@Autowired
	private ParamGenerauxManager paramGenerauxManager;
	
	private int actualJournee;
	private long idSelectedChampionnat;
	private String journeePtJr;
	private String journeeCpEqUsr;
	private String journeePtUsr;
	
	public void ddd(int i) throws ApplicationAbdException {
		ChampionnatVO champ = null;
		for (ChampionnatVO ch : applicationBean.getListChampionnat()) {
			if (ch.getId() == idSelectedChampionnat) {
				champ = ch;
				break;
			}
		}
		
		journeePtJr = String.valueOf(champ.getActualJournee());
		journeeCpEqUsr = String.valueOf(champ.getActualJournee());
		journeePtUsr = String.valueOf(champ.getActualJournee());
		actualJournee = champ.getActualJournee();
	}
	
	

	public void traiterTotalPointJoueurParJournee() {
		int j = Integer.valueOf(journeePtJr);
		joueurManager.traiterTotalPointJoueur(idSelectedChampionnat, j);
	}
	
	public void traiterTotalPointJoueurParPeriode() {
		//joueurManager.traiterTotalPointJoueur(1, 29);
	}
	
	public void copyEquipeUser() throws NumberFormatException, ApplicationAbdException {
		userManager.copyEquipeUser(idSelectedChampionnat, Integer.valueOf(journeeCpEqUsr));
	}
	
	public void traiterTotalPointJoueurParSaison() {
		joueurManager.updateTotalPointJoueur();
	}
	
	public void updatePointUserSaison() throws ApplicationAbdException {
		pointUserManager.updatePointUserSaison(idSelectedChampionnat);
	}
	
	public void updatePointUserJournee() throws ApplicationAbdException {
		pointUserManager.updatePointUserJournee(idSelectedChampionnat, Integer.valueOf(journeePtUsr));
	}
	
	public void traiterCoutJoueur() throws ApplicationAbdException {
		joueurManager.traiterCoutJoueur(idSelectedChampionnat);
	}
	
	public void traiterSoldeUsers() throws ApplicationAbdException {
		userManager.modifierSoldeEquipeUser(idSelectedChampionnat, Integer.valueOf(actualJournee));
	}
	
	public void initEquipeUser() throws ApplicationAbdException {
		joueurManager.initEquipeUser();
	}
	
	private String message;
	public void addComment() throws ApplicationAbdException {
		paramGenerauxManager.addComment(message);
	}

	
	
	
	/**
	 * 
	 * GETTERS AND SETTERS
	 */
	
	
	public long getIdSelectedChampionnat() {
		return idSelectedChampionnat;
	}

	public void setIdSelectedChampionnat(long idSelectedChampionnat) {
		this.idSelectedChampionnat = idSelectedChampionnat;
	}

	public String getJourneePtJr() {
		return journeePtJr;
	}

	public void setJourneePtJr(String journeePtJr) {
		this.journeePtJr = journeePtJr;
	}

	public String getJourneeCpEqUsr() {
		return journeeCpEqUsr;
	}

	public void setJourneeCpEqUsr(String journeeCpEqUsr) {
		this.journeeCpEqUsr = journeeCpEqUsr;
	}

	public String getJourneePtUsr() {
		return journeePtUsr;
	}

	public void setJourneePtUsr(String journeePtUsr) {
		this.journeePtUsr = journeePtUsr;
	}



	public int getActualJournee() {
		return actualJournee;
	}



	public void setActualJournee(int actualJournee) {
		this.actualJournee = actualJournee;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	
}
