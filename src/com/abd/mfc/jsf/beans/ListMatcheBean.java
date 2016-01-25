package com.abd.mfc.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.MatcheManager;
import com.abd.mfc.vo.MatchVO;

@Controller
@Scope("view")
public class ListMatcheBean extends BaseBean {

	private List<MatchVO> listMatch;
	
	@Autowired
	private SessionBean sessionBean;
	
//	@Autowired
//  private UserManager userManager;
	
	@Autowired
    private MatcheManager matcheManager;
	
	private int selectedJournee;

	@PostConstruct
	public void init() throws ApplicationAbdException {
		selectedJournee = sessionBean.getSelectedChampionnat().getActualJournee();
		
		doSearch();
	}
	
	public String doSearch() throws ApplicationAbdException {
		
		long idChamp = sessionBean.getSelectedChampionnat().getId();
		listMatch = matcheManager.findMatchsByDay(idChamp, selectedJournee);
		
		return null;
	}

	public List<MatchVO> getListMatch() {
		return listMatch;
	}

	public int getSelectedJournee() {
		return selectedJournee;
	}

	public void setSelectedJournee(int selectedJournee) {
		this.selectedJournee = selectedJournee;
	}
	
}
