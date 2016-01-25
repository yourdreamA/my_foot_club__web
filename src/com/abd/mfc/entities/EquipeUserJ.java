package com.abd.mfc.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
	name = "findEquipeUserJ",
	query = "from EquipeUserJ p where p.id_user = :id_user and p.id_championnat = :id_championnat and p.journee = :journee"
	),
	@NamedQuery(
			name = "findAllEquipeUserJ",
			query = "from EquipeUserJ p where p.id_championnat = :id_championnat and p.journee = :journee"
			),
	@NamedQuery(
			name = "calculPointUserSaison",
			query = "select eq.id_user, sum(eq.pointUser.nbrPt) as tot from EquipeUserJ eq where eq.id_championnat = :idChampionnat group by eq.id_user order by sum(eq.pointUser.nbrPt) desc"
			),
})

@Entity
@Table(name="mfc_equipe_user_j")
public class EquipeUserJ {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nom;
	private String logo;
	
	private long id_user;
	private long id_championnat;
	private int journee;
	private int soldeUser;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private PointUserJournee pointUser;
	
	private int formation;
	
	@ManyToOne
	private Joueur gardien;
	@ManyToOne
	private Joueur defenseur1;
	@ManyToOne
	private Joueur defenseur2;
	@ManyToOne
	private Joueur defenseur3;
	@ManyToOne
	private Joueur defenseur4;
	@ManyToOne
	private Joueur milieu1;
	@ManyToOne
	private Joueur milieu2;
	@ManyToOne
	private Joueur milieu3;
	@ManyToOne
	private Joueur milieu4;
	@ManyToOne
	private Joueur att1;
	@ManyToOne
	private Joueur att2;
	@ManyToOne
	private Joueur att3;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getFormation() {
		return formation;
	}
	public void setFormation(int formation) {
		this.formation = formation;
	}
	public Joueur getGardien() {
		return gardien;
	}
	public void setGardien(Joueur gardien) {
		this.gardien = gardien;
	}
	public Joueur getDefenseur1() {
		return defenseur1;
	}
	public void setDefenseur1(Joueur defenseur1) {
		this.defenseur1 = defenseur1;
	}
	public Joueur getDefenseur2() {
		return defenseur2;
	}
	public void setDefenseur2(Joueur defenseur2) {
		this.defenseur2 = defenseur2;
	}
	public Joueur getDefenseur3() {
		return defenseur3;
	}
	public void setDefenseur3(Joueur defenseur3) {
		this.defenseur3 = defenseur3;
	}
	public Joueur getDefenseur4() {
		return defenseur4;
	}
	public void setDefenseur4(Joueur defenseur4) {
		this.defenseur4 = defenseur4;
	}
	public Joueur getMilieu1() {
		return milieu1;
	}
	public void setMilieu1(Joueur milieu1) {
		this.milieu1 = milieu1;
	}
	public Joueur getMilieu2() {
		return milieu2;
	}
	public void setMilieu2(Joueur milieu2) {
		this.milieu2 = milieu2;
	}
	public Joueur getMilieu3() {
		return milieu3;
	}
	public void setMilieu3(Joueur milieu3) {
		this.milieu3 = milieu3;
	}
	public Joueur getMilieu4() {
		return milieu4;
	}
	public void setMilieu4(Joueur milieu4) {
		this.milieu4 = milieu4;
	}
	public Joueur getAtt1() {
		return att1;
	}
	public void setAtt1(Joueur att1) {
		this.att1 = att1;
	}
	public Joueur getAtt2() {
		return att2;
	}
	public void setAtt2(Joueur att2) {
		this.att2 = att2;
	}
	public Joueur getAtt3() {
		return att3;
	}
	public void setAtt3(Joueur att3) {
		this.att3 = att3;
	}
	public long getId_user() {
		return id_user;
	}
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	public int getSoldeUser() {
		return soldeUser;
	}
	public void setSoldeUser(int soldeUser) {
		this.soldeUser = soldeUser;
	}
	public long getId_championnat() {
		return id_championnat;
	}
	public void setId_championnat(long id_championnat) {
		this.id_championnat = id_championnat;
	}
	public int getJournee() {
		return journee;
	}
	public void setJournee(int journee) {
		this.journee = journee;
	}
	public PointUserJournee getPointUser() {
		return pointUser;
	}
	public void setPointUser(PointUserJournee pointUser) {
		this.pointUser = pointUser;
	}
	
	
}
