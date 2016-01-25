package com.abd.mfc.vo;

import java.util.Date;

import com.abd.mfc.entities.Match;

public class MatchVO extends VO<Match> {

	private long id;
	
	private Date dateM;
	private String lieu;
	
	private JourneeVO journee;
	
	private EquipeVO eq1;
	private EquipeVO eq2;
	
	private int nbrButEq1;
	private int nbrButEq2;
	
	private int joue;
	
	public MatchVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MatchVO(Match e) {
		this.id = e.getId();
		this.dateM = e.getDateM();
		this.lieu = e.getLieu();
		//TODO a verif
		//this.journee = journee;
		this.eq1 = new EquipeVO(e.getEq1());
		this.eq2 = new EquipeVO(e.getEq2());
		this.nbrButEq1 = e.getNbrButEq1();
		this.nbrButEq2 = e.getNbrButEq2();
		joue = e.getJoue();
	}



	@Override
	public Match convertToEntity() {
		Match e = new Match();
		e.setId(id);
		e.setDateM(dateM);
		e.setEq1(eq1.convertToEntity());
		e.setEq2(eq2.convertToEntity());
		e.setLieu(lieu);
		e.setNbrButEq1(nbrButEq1);
		e.setNbrButEq2(nbrButEq2);
		e.setJoue(joue);
		e.setJournee(journee.convertToEntity());
		
		return e;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateM() {
		return dateM;
	}

	public void setDateM(Date dateM) {
		this.dateM = dateM;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	

	public int getNbrButEq1() {
		return nbrButEq1;
	}

	public void setNbrButEq1(int nbrButEq1) {
		this.nbrButEq1 = nbrButEq1;
	}

	public int getNbrButEq2() {
		return nbrButEq2;
	}

	public void setNbrButEq2(int nbrButEq2) {
		this.nbrButEq2 = nbrButEq2;
	}

	public JourneeVO getJournee() {
		return journee;
	}

	public void setJournee(JourneeVO journee) {
		this.journee = journee;
	}

	public EquipeVO getEq1() {
		return eq1;
	}

	public void setEq1(EquipeVO eq1) {
		this.eq1 = eq1;
	}

	public EquipeVO getEq2() {
		return eq2;
	}

	public void setEq2(EquipeVO eq2) {
		this.eq2 = eq2;
	}



	public int getJoue() {
		return joue;
	}



	public void setJoue(int joue) {
		this.joue = joue;
	}

}
