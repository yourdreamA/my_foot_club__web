package com.abd.mfc.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="mfc_equipe_user")
public class EquipeUserFilter extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nom;
	private String logo;
	
	private long id_user;
	private long id_championnat;
	
	@Formula("(select CONCAT(u.lastname,' ',u.firstname) from mfc_user u where u.id=id_user)")
	private String nomUser;
	
	@OneToOne(cascade={CascadeType.MERGE})
	private PointUser pointUserS;
	
	@OneToOne
	private PointUserJournee pointUserJ;

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

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public long getId_championnat() {
		return id_championnat;
	}

	public void setId_championnat(long id_championnat) {
		this.id_championnat = id_championnat;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	public PointUser getPointUserS() {
		return pointUserS;
	}

	public void setPointUserS(PointUser pointUserS) {
		this.pointUserS = pointUserS;
	}

	public PointUserJournee getPointUserJ() {
		return pointUserJ;
	}

	public void setPointUserJ(PointUserJournee pointUserJ) {
		this.pointUserJ = pointUserJ;
	}
	
	
}
