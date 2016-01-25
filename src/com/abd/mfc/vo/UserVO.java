package com.abd.mfc.vo;

import java.util.Date;

import com.abd.mfc.entities.User;

public class UserVO extends VO<User> {

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
	
	private PaginationVO pagination;
	
	private String sortBy;
	
	private JourneeVO journee;
	
	private PointUserVO pointUser;
	private PointUserVO pointUserJournee;
	
	private Date lastLogin;
	
	private String eqUserEsp;
	private Date lastUpdateUserEsp;
	
	private String eqUserEng;
	private Date lastUpdateUserEng;
	
	public UserVO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public UserVO(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.pwd = u.getPwd();
		this.firstname = u.getFirstname();
		this.lastname = u.getLastname();
		this.mail = u.getMail();
		this.ville = u.getVille();
		this.zone = u.getZone();
		this.pays = u.getPays();
		this.adress = u.getAdress();
		this.profession = u.getProfession();
		this.dateNaissance = u.getDateNaissance();
		this.langue = u.getLangue();
		this.sexe = u.getSexe();
		lastLogin = u.getLastLogin();
	}
	
	
	@Override
	public User convertToEntity() {
		User entity = new User();
		
		entity.setId(id);
		entity.setUsername(username);
		entity.setPwd(pwd);
		entity.setFirstname(firstname);
		entity.setLastname(lastname);
		entity.setMail(mail);
		entity.setVille(ville);
		entity.setZone(zone);
		entity.setPays(pays);
		entity.setAdress(adress);
		entity.setProfession(profession);
		entity.setDateNaissance(dateNaissance);
		entity.setLangue(langue);
		entity.setSexe(sexe);
		entity.setLastLogin(lastLogin);
		
		return entity;
	}



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



	public PaginationVO getPagination() {
		return pagination;
	}



	public void setPagination(PaginationVO pagination) {
		this.pagination = pagination;
	}



	public String getSortBy() {
		return sortBy;
	}



	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}




	public JourneeVO getJournee() {
		return journee;
	}



	public void setJournee(JourneeVO journee) {
		this.journee = journee;
	}



	public PointUserVO getPointUserJournee() {
		return pointUserJournee;
	}



	public void setPointUserJournee(PointUserVO pointUserJournee) {
		this.pointUserJournee = pointUserJournee;
	}



	public PointUserVO getPointUser() {
		return pointUser;
	}



	public void setPointUser(PointUserVO pointUser) {
		this.pointUser = pointUser;
	}



	public Date getLastLogin() {
		return lastLogin;
	}



	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}



	public String getEqUserEsp() {
		return eqUserEsp;
	}



	public void setEqUserEsp(String eqUserEsp) {
		this.eqUserEsp = eqUserEsp;
	}



	public Date getLastUpdateUserEsp() {
		return lastUpdateUserEsp;
	}



	public void setLastUpdateUserEsp(Date lastUpdateUserEsp) {
		this.lastUpdateUserEsp = lastUpdateUserEsp;
	}



	public String getEqUserEng() {
		return eqUserEng;
	}



	public void setEqUserEng(String eqUserEng) {
		this.eqUserEng = eqUserEng;
	}



	public Date getLastUpdateUserEng() {
		return lastUpdateUserEng;
	}



	public void setLastUpdateUserEng(Date lastUpdateUserEng) {
		this.lastUpdateUserEng = lastUpdateUserEng;
	}
}
