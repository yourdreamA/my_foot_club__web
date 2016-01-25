package com.abd.mfc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="mfc_user",
		uniqueConstraints={@UniqueConstraint(columnNames={"username"}),
							@UniqueConstraint(columnNames={"mail"})}
)
//@SequenceGenerator(sequenceName="MFC_USER_SEQ",name="MFC_USER_SEQ_GEN")
public class User extends BaseEntity {

	@Id
//	@GeneratedValue(generator="MFC_USER_SEQ_GEN",strategy=GenerationType.SEQUENCE)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String username;
	private String pwd;
	private String firstname;
	private String lastname;
	private String mail;
	
	private String ville;
	private String zone;
	private String pays;
	private String adress;
	
	private String profession;
	
	private Date dateNaissance;
	
	private String langue;
	
	private String sexe;
	
	private Date lastLogin;
	
//	@Formula("(select pt.nbrPt from mfc_point_user pt where pt.idUser=id and pt.typePt='s')")
//	private int totalPoint;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
	
}
