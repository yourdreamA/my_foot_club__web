package com.abd.mfc.jsf.beans;

import static ch.lambdaj.Lambda.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.StatJoueurVO;

@Controller
@Scope("request")
public class StatJoueurBean extends BaseBean {
	
	private JoueurVO selectedJoueur;
	private StatJoueurVO total;
	private long idSelectedJoueur;
	
	@Autowired
	private JoueurManager joueurManager;
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private SessionBean sessionBean;
	
	
	public void doSearch() throws ApplicationAbdException {
		selectedJoueur = joueurManager.searchStatJoueur(sessionBean.getSelectedChampionnat().getId(), idSelectedJoueur);
		//clacul totao stats
		List<StatJoueurVO> stats = selectedJoueur.getStats();
		
		total = new StatJoueurVO();
		if (stats != null && !stats.isEmpty()) {
			total.setNbrMinuteJoue(sumFrom(stats).getNbrMinuteJoue());
			total.setNbrBut(sumFrom(stats).getNbrBut());
			total.setNbrAssist(sumFrom(stats).getNbrAssist());
			total.setNbrShotInG(sumFrom(stats).getNbrShotInG());
			total.setNbrPenalty(sumFrom(stats).getNbrPenalty());
			total.setNbrPenaltyRate(sumFrom(stats).getNbrPenaltyRate());
			total.setNbrSave(sumFrom(stats).getNbrSave());
			total.setNbrSavePenalty(sumFrom(stats).getNbrSavePenalty());
			total.setNbrButRecu(sumFrom(stats).getNbrButRecu());
			total.setCleanSheetS(sumFrom(stats).getCleanSheetF());
			total.setCartonJ(sumFrom(stats).getCartonJ());
			total.setCartonR(sumFrom(stats).getCartonR());
			total.setTotalP(sumFrom(stats).getTotalP());
		}
		
	}
	
	public void initIdJoueur(long idJoueur) throws ApplicationAbdException {
		for (int i = 0; i < 650000; i++) {
			
		}
		
for (int i = 0; i < 650000; i++) {
			
		}

for (int i = 0; i < 650000; i++) {
	
}

for (int i = 0; i < 650000; i++) {
	
}
		idSelectedJoueur = idJoueur;
		doSearch();
	}
	

	public JoueurVO getSelectedJoueur() {
		return selectedJoueur;
	}

	public void setSelectedJoueur(JoueurVO selectedJoueur) {
		this.selectedJoueur = selectedJoueur;
	}

	public long getIdSelectedJoueur() {
		return idSelectedJoueur;
	}

	public void setIdSelectedJoueur(long idSelectedJoueur) {
		this.idSelectedJoueur = idSelectedJoueur;
	}

	public StatJoueurVO getTotal() {
		return total;
	}

	public void setTotal(StatJoueurVO total) {
		this.total = total;
	}
	
	
}
