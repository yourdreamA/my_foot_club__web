package com.abd.mfc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
	name = "findMatchsByDay",
	query = "from Match m where m.journee.idChampionnat = :idChampionnat and m.journee.numero = :numJ"
	)
})

@Entity
@Table(name="mfc_match")
public class Match extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Date dateM;
	private String lieu;
	
	@ManyToOne
	private Journee journee;
	
	@OneToOne
	private Equipe eq1;
	@OneToOne
	private Equipe eq2;
	
	private int nbrButEq1;
	private int nbrButEq2;
	
	private int joue;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDateM() {
		return dateM;
	}
	public void setDateM(Date date) {
		this.dateM = date;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public Equipe getEq1() {
		return eq1;
	}
	public void setEq1(Equipe eq1) {
		this.eq1 = eq1;
	}
	public Equipe getEq2() {
		return eq2;
	}
	public void setEq2(Equipe eq2) {
		this.eq2 = eq2;
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
	public Journee getJournee() {
		return journee;
	}
	public void setJournee(Journee journee) {
		this.journee = journee;
	}
	public int getJoue() {
		return joue;
	}
	public void setJoue(int joue) {
		this.joue = joue;
	}
	
	
	
}
