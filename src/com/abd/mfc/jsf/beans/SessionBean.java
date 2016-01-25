package com.abd.mfc.jsf.beans;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.ChampionnatVO;

@Controller
@Scope("session")
public class SessionBean implements Serializable {

	private ChampionnatVO selectedChampionnat;
	@Autowired
	private ApplicationBean appBean;
	
	@PostConstruct
	public void init() throws ApplicationAbdException {
		//set default league
		selectedChampionnat = appBean.getListChampionnat().get(1);
	}
	
	public String leagueChanged(ChampionnatVO sel) {
		selectedChampionnat = sel;
		
		return "/listMatches.xhtml?faces-redirect=true";
	}

	public ChampionnatVO getSelectedChampionnat() {
		return selectedChampionnat;
	}

	public void setSelectedChampionnat(ChampionnatVO selectedChampionnat) {
		this.selectedChampionnat = selectedChampionnat;
	}
	
	
}
