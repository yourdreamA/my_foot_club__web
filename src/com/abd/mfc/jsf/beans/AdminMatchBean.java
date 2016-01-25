package com.abd.mfc.jsf.beans;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.MatcheManager;
import com.abd.mfc.vo.ChampionnatVO;
import com.abd.mfc.vo.EquipeVO;
import com.abd.mfc.vo.JourneeVO;
import com.abd.mfc.vo.MatchVO;

@Controller
@Scope("view")
public class AdminMatchBean extends BaseBean {

	@Autowired
	private ApplicationBean applicationBean;
	
	@Autowired
	private MatcheManager matcheManager;
	
	private int actualJournee;
	private long idSelectedChampionnat;
	
	private JourneeVO currentJj;
	private String currentJournee;
	
	/*************************************************************************/
	private long idEq1;
	private String nbrButEq1;
	
	private long idEq2;
	private String nbrButEq2;
	private Date dateMatch;
	private Date heureMatch;
	/*************************************************************************/
	
	private List<MatchVO> listMatch;

	public void init() throws ApplicationAbdException {
		ChampionnatVO champ = null;
		for (ChampionnatVO ch : applicationBean.getListChampionnat()) {
			if (ch.getId() == idSelectedChampionnat) {
				champ = ch;
				break;
			}
		}
		
		currentJournee = String.valueOf(champ.getActualJournee());
		actualJournee = champ.getActualJournee();
		
		listMatch = matcheManager.findMatchsByDay(idSelectedChampionnat, champ.getActualJournee());
	}
	
	public void updateCurrentJournee() throws ApplicationAbdException {
		int j = Integer.valueOf(currentJournee);
		currentJj = matcheManager.updateCurrentJournee(idSelectedChampionnat, j);
		applicationBean.updateJournee();
	}
	
	public void addMatch() throws ApplicationAbdException {
		
		if (currentJj == null) {
			FacesMessage msg = new FacesMessage("journee null", "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return;
		}
		MatchVO m = new MatchVO();
		
		m.setJournee(currentJj);
		
		EquipeVO eq1 = new EquipeVO();
		eq1.setId(idEq1);
		m.setEq1(eq1);
		
		EquipeVO eq2 = new EquipeVO();
		eq2.setId(idEq2);
		m.setEq2(eq2);
		
		dateMatch.setHours(heureMatch.getHours());
		dateMatch.setMinutes(heureMatch.getMinutes());
		m.setDateM(dateMatch);
		
		matcheManager.add(m);
	}
	
	public void modifierMatch(MatchVO m) throws ApplicationAbdException {
		//System.out.println(m.getId());
		m.setJoue(1);
		matcheManager.updateResultat(m);
	}
	
	/**
	 * 
	 * GETTERS AND SETTERS
	 * 
	 */
	
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

	public String getCurrentJournee() {
		return currentJournee;
	}

	public void setCurrentJournee(String currentJournee) {
		this.currentJournee = currentJournee;
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

	public Date getDateMatch() {
		return dateMatch;
	}

	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}

	public JourneeVO getCurrentJj() {
		return currentJj;
	}

	public void setCurrentJj(JourneeVO currentJj) {
		this.currentJj = currentJj;
	}

	public Date getHeureMatch() {
		return heureMatch;
	}

	public void setHeureMatch(Date heureMatch) {
		this.heureMatch = heureMatch;
	}

	public List<MatchVO> getListMatch() {
		return listMatch;
	}

	public void setListMatch(List<MatchVO> listMatch) {
		this.listMatch = listMatch;
	}
	
	
	
}
