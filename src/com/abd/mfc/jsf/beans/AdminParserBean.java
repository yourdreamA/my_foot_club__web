package com.abd.mfc.jsf.beans;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.ChampionnatVO;
import com.abd.stat.CalculStatJoueur;
import com.abd.stat.StatJoueurParse;

@Controller
@Scope("view")
public class AdminParserBean extends BaseBean {

	@Autowired
	private JoueurManager joueurManager;
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ApplicationBean applicationBean;
	
	private int actualJournee;
	private long idSelectedChampionnat;
	
	private String journee;
	private String idUrl;
	
	private long idEq1;
	private String nbrButEq1;
	
	private long idEq2;
	private String nbrButEq2;
	
	public void init() throws ApplicationAbdException {
		ChampionnatVO champ = null;
		for (ChampionnatVO ch : applicationBean.getListChampionnat()) {
			if (ch.getId() == idSelectedChampionnat) {
				champ = ch;
				break;
			}
		}
		
		journee = String.valueOf(champ.getActualJournee());
		actualJournee = champ.getActualJournee();
	}
	
	private List<String> listErreurs;
	private List<String> listErreurs2;
	public void parseMatch() throws ApplicationAbdException {
		validate();
		
		String url = "http://www.espnfc.com/gamecast/statistics/id/" + idUrl + "/statistics.html";
		
		CalculStatJoueur calculStatJoueur = new CalculStatJoueur(
				url, (int)idSelectedChampionnat, Integer.valueOf(journee), 
				(int)idEq1, (int)idEq2, 
				Integer.valueOf(nbrButEq1), Integer.valueOf(nbrButEq2))
				;
		
		
		Map<Integer, List<StatJoueurParse>> res = calculStatJoueur.extractList();
		
		List<StatJoueurParse> listJ1 = res.get(1);
		
		listErreurs = joueurManager.insertStatJ(idSelectedChampionnat, Integer.valueOf(journee), idEq1, listJ1);
		
		List<StatJoueurParse> listJ2 = res.get(2);
		listErreurs2 = joueurManager.insertStatJ(idSelectedChampionnat, Integer.valueOf(journee), idEq2, listJ2);
		
		
	}
	
	private void validate() throws ApplicationAbdException {
		if (idUrl == null || idUrl.isEmpty()) {
			throw new ApplicationAbdException("idUrl null");
		}
		
		
		
		if (idEq1 == 0 || idEq2 == 0) {
			throw new ApplicationAbdException("equipe null");
		}
		
		if (nbrButEq1 == null || nbrButEq1.isEmpty()) {
			throw new ApplicationAbdException("nbrButEq1 null");
		}
		
		if (nbrButEq2 == null || nbrButEq2.isEmpty()) {
			throw new ApplicationAbdException("nbrButEq2 null");
		}
	}

	
	/**
	 * 
	 * GETTERS AND SETTERS
	 * 
	 */
	public String getJournee() {
		return journee;
	}

	public void setJournee(String journee) {
		this.journee = journee;
	}

	public String getIdUrl() {
		return idUrl;
	}

	public void setIdUrl(String idUrl) {
		this.idUrl = idUrl;
	}

	public JoueurManager getJoueurManager() {
		return joueurManager;
	}

	public void setJoueurManager(JoueurManager joueurManager) {
		this.joueurManager = joueurManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public ApplicationBean getApplicationBean() {
		return applicationBean;
	}

	public void setApplicationBean(ApplicationBean applicationBean) {
		this.applicationBean = applicationBean;
	}

	public int getActualJournee() {
		return actualJournee;
	}

	public void setActualJournee(int actualJournee) {
		this.actualJournee = actualJournee;
	}

	public long getIdSelectedChampionnat() {
		return idSelectedChampionnat;
	}

	public void setIdSelectedChampionnat(long idSelectedChampionnat) {
		this.idSelectedChampionnat = idSelectedChampionnat;
	}

	public long getIdEq1() {
		return idEq1;
	}

	public void setIdEq1(long idEq1) {
		this.idEq1 = idEq1;
	}

	public String getNbrButEq1() {
		return nbrButEq1;
	}

	public void setNbrButEq1(String nbrButEq1) {
		this.nbrButEq1 = nbrButEq1;
	}

	public long getIdEq2() {
		return idEq2;
	}

	public void setIdEq2(long idEq2) {
		this.idEq2 = idEq2;
	}

	public String getNbrButEq2() {
		return nbrButEq2;
	}

	public void setNbrButEq2(String nbrButEq2) {
		this.nbrButEq2 = nbrButEq2;
	}

	public List<String> getListErreurs() {
		return listErreurs;
	}

	public void setListErreurs(List<String> listErreurs) {
		this.listErreurs = listErreurs;
	}

	public List<String> getListErreurs2() {
		return listErreurs2;
	}

	public void setListErreurs2(List<String> listErreurs2) {
		this.listErreurs2 = listErreurs2;
	}

}
