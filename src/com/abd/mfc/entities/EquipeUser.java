package com.abd.mfc.entities;

import java.util.Date;

import javax.persistence.CascadeType;
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
	name = "findEquipeUserByUser",
	query = "from EquipeUser p where p.id_user = :id_user and p.id_championnat = :id_championnat"
	),
	@NamedQuery(
			name = "findAllEquipeUser",
			query = "from EquipeUser p where p.id_championnat = :id_championnat"
			),
			@NamedQuery(
					name = "findIdEquipeUser",
					query = "select p.id from EquipeUser p where p.id_user = :id_user and p.id_championnat = :id_championnat"
					),
//	@NamedQuery(
//			name = "findUserWithoutPoint",
//			query = "select u.id from EquipeUser u where u.idChampionnat = :idChampionnat and u.pointUserS not exists (select 1 from PointUser p where p.idUser = u.id)"
//			),
})

@Entity
@Table(name="mfc_equipe_user")
public class EquipeUser extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nom;
	private String logo;
	
	private long id_user;
	private long id_championnat;
	private int soldeUser;
	
	@OneToOne(cascade={CascadeType.MERGE})
	private PointUser pointUserS;
	
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
	
	private Date lastUpdate;
	
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
	public PointUser getPointUserS() {
		return pointUserS;
	}
	public void setPointUserS(PointUser pointUserS) {
		this.pointUserS = pointUserS;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
