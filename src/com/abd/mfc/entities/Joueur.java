package com.abd.mfc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(
			name = "findJoueurChampionnat",
			query = "from Joueur j where j.equipe.idChampionnat = :idChampionnat"
			),
	@NamedQuery(
			name = "findIdJoueurByNumero",
			query = "select j.id from Joueur j where j.equipe.id = :idEquipe and j.numero = :numero"
			)
})

@Entity
@Table(name="mfc_joueur")
public class Joueur extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nomJ;
	private String nomJ_ar;
	private String nomC;
	private int numero;
	private Date dateNaissance;
	private int position;
	private int cout;
	
	private Integer totalPoint;
	private Integer pointPeriodeAct;
	private Integer pointJournee;
	
	private Integer diffCout;
	
	@ManyToOne
	private Equipe equipe;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomC() {
		return nomC;
	}
	public void setNomC(String nomC) {
		this.nomC = nomC;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	public Integer getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}
	public Integer getPointPeriodeAct() {
		return pointPeriodeAct;
	}
	public void setPointPeriodeAct(Integer pointPeriodeAct) {
		this.pointPeriodeAct = pointPeriodeAct;
	}
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	public String getNomJ() {
		return nomJ;
	}
	public void setNomJ(String nomJ) {
		this.nomJ = nomJ;
	}
	public String getNomJ_ar() {
		return nomJ_ar;
	}
	public void setNomJ_ar(String nomJ_ar) {
		this.nomJ_ar = nomJ_ar;
	}
	public Integer getPointJournee() {
		return pointJournee;
	}
	public void setPointJournee(Integer pointJournee) {
		this.pointJournee = pointJournee;
	}
	public Integer getDiffCout() {
		return diffCout;
	}
	public void setDiffCout(Integer diffCout) {
		this.diffCout = diffCout;
	}
	
	
}
