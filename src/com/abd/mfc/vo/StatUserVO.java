package com.abd.mfc.vo;

import java.util.List;

public class StatUserVO {

	private List<JoueurVO> statJoueurs;
	private EquipeUserVO equipeUser;
	private int journee;
	private String totalPoint;
	private int totalPt;
	private int diffCout;
	
	public List<JoueurVO> getStatJoueurs() {
		return statJoueurs;
	}
	public void setStatJoueurs(List<JoueurVO> statJoueurs) {
		this.statJoueurs = statJoueurs;
	}
	public EquipeUserVO getEquipeUser() {
		return equipeUser;
	}
	public void setEquipeUser(EquipeUserVO equipeUser) {
		this.equipeUser = equipeUser;
	}
	public int getJournee() {
		return journee;
	}
	public void setJournee(int journee) {
		this.journee = journee;
	}
	public String getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}
	public int getTotalPt() {
		return totalPt;
	}
	public void setTotalPt(int totalPt) {
		this.totalPt = totalPt;
	}
	public int getDiffCout() {
		return diffCout;
	}
	public void setDiffCout(int diffCout) {
		this.diffCout = diffCout;
	}
	
	
}
