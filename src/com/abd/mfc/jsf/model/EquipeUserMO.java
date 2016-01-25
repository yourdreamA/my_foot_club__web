package com.abd.mfc.jsf.model;

import com.abd.mfc.vo.JoueurVO;

public class EquipeUserMO extends BaseMO {
	
	private FormationEnum formation;
	private JoueurVO gardien;
	private JoueurVO defenseur1;
	private JoueurVO defenseur2;
	private JoueurVO defenseur3;
	private JoueurVO defenseur4;
	private JoueurVO milieu1;
	private JoueurVO milieu2;
	private JoueurVO milieu3;
	private JoueurVO milieu4;
	private JoueurVO att1;
	private JoueurVO att2;
	private JoueurVO att3;

	public FormationEnum getFormation() {
		return formation;
	}

	public void setFormation(FormationEnum formation) {
		this.formation = formation;
	}

	public JoueurVO getGardien() {
		return gardien;
	}

	public void setGardien(JoueurVO gardien) {
		this.gardien = gardien;
	}

	public JoueurVO getDefenseur1() {
		return defenseur1;
	}

	public void setDefenseur1(JoueurVO defenseur1) {
		this.defenseur1 = defenseur1;
	}

	public JoueurVO getDefenseur2() {
		return defenseur2;
	}

	public void setDefenseur2(JoueurVO defenseur2) {
		this.defenseur2 = defenseur2;
	}

	public JoueurVO getDefenseur3() {
		return defenseur3;
	}

	public void setDefenseur3(JoueurVO defenseur3) {
		this.defenseur3 = defenseur3;
	}

	public JoueurVO getDefenseur4() {
		return defenseur4;
	}

	public void setDefenseur4(JoueurVO defenseur4) {
		this.defenseur4 = defenseur4;
	}

	public JoueurVO getMilieu1() {
		return milieu1;
	}

	public void setMilieu1(JoueurVO milieu1) {
		this.milieu1 = milieu1;
	}

	public JoueurVO getMilieu2() {
		return milieu2;
	}

	public void setMilieu2(JoueurVO milieu2) {
		this.milieu2 = milieu2;
	}

	public JoueurVO getMilieu3() {
		return milieu3;
	}

	public void setMilieu3(JoueurVO milieu3) {
		this.milieu3 = milieu3;
	}

	public JoueurVO getMilieu4() {
		return milieu4;
	}

	public void setMilieu4(JoueurVO milieu4) {
		this.milieu4 = milieu4;
	}

	public JoueurVO getAtt1() {
		return att1;
	}

	public void setAtt1(JoueurVO att1) {
		this.att1 = att1;
	}

	public JoueurVO getAtt2() {
		return att2;
	}

	public void setAtt2(JoueurVO att2) {
		this.att2 = att2;
	}

	public JoueurVO getAtt3() {
		return att3;
	}

	public void setAtt3(JoueurVO att3) {
		this.att3 = att3;
	}

}
