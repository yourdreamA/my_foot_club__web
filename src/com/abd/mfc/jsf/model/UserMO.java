package com.abd.mfc.jsf.model;

import java.util.Date;

import com.abd.mfc.vo.EquipeUserFilterVO;
import com.abd.mfc.vo.UserVO;

public class UserMO extends BaseMO {

	private long id;
	private long id_user;
	private LoginMO login;
	private String nomEquipe;
	private String pwd1;
	private String pwd2;
	private String firstname;
	private String lastname;
	private String name;
	private String mail;
	
	private String ville;
	private String zone;
	private String pays;
	private String adress;
	
	private String profession;
	
	private Date dateNaissance;
	
	private String langue;
	
	private String sexe;
	
	//TODO rang par championnat
	private int rangTotal;
	private int nbrPointTotal;
	
	private int rangPeriodeAct;
	private int nbrPointPeriodeAct;
	private int nbrPointJourneAct;
	
	public UserMO() {}
	

	
	

	public UserMO(EquipeUserFilterVO u) {
		id = u.getId();
		id_user = u.getId_user();
		nomEquipe = u.getNom();
		name = u.getNomUser();
		
		if (u.getPointUserS() != null) {
			nbrPointTotal = u.getPointUserS().getNbrPt();
			rangTotal = u.getPointUserS().getRang();
		}
		
		if (u.getPointUserJ() != null) {
			nbrPointJourneAct = u.getPointUserJ().getNbrPt();
		}
	}

	public UserMO(UserVO u) {
		super();
		id = u.getId();
		this.login = new LoginMO();
		login.setUsername(u.getUsername());
//		login.setPwd(u.getPwd());
		this.firstname = u.getFirstname();
		this.lastname = u.getLastname();
		name = firstname + " " + lastname;
		this.mail = u.getMail();
		this.ville = u.getVille();
		this.zone = u.getZone();
		this.pays = u.getPays();
		this.adress = u.getAdress();
		this.profession = u.getProfession();
		this.dateNaissance = u.getDateNaissance();
		this.langue = u.getLangue();
		this.sexe = u.getSexe();
		if (u.getPointUser() != null) {
			nbrPointTotal = u.getPointUser().getNbrPt();
		}
		if (u.getPointUserJournee() != null) {
			nbrPointJourneAct = u.getPointUserJournee().getNbrPt();
		}
	}
	
//	public UserMO(PointUserVO u) {
//		super();
//		this.login = new LoginMO();
//		login.setUsername(u.getUsername());
//		this.nbrPointTotal = u.getNbrPt();
//		name = u.getName();
//	}





	public UserVO convertToVo() {
		UserVO vo = new UserVO();
		
		if (login != null) {
			vo.setUsername(login.getUsername());
			vo.setPwd(login.getPwd());
		}
		
		vo.setFirstname(firstname);
		vo.setLastname(lastname);
		vo.setMail(mail);
		vo.setVille(ville);
		vo.setZone(zone);
		vo.setPays(pays);
		vo.setAdress(adress);
		vo.setProfession(profession);
		vo.setDateNaissance(dateNaissance);
		vo.setLangue(langue);
		vo.setSexe(sexe);
		
		return vo;
	}

	public String getFullname() {
		StringBuilder fullname = new StringBuilder();
		if (lastname != null && !lastname.isEmpty()) {
			fullname.append(lastname);
		}
		if (firstname != null && !firstname.isEmpty()) {
			fullname.append(" ").append(firstname);
		}
		
		return fullname.toString();
	}
	
	public LoginMO getLogin() {
		return login;
	}

	public void setLogin(LoginMO login) {
		this.login = login;
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
	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}






	public int getNbrPointTotal() {
		return nbrPointTotal;
	}






	public void setNbrPointTotal(int nbrPointTotal) {
		this.nbrPointTotal = nbrPointTotal;
	}






	public int getNbrPointPeriodeAct() {
		return nbrPointPeriodeAct;
	}






	public void setNbrPointPeriodeAct(int nbrPointPeriodeAct) {
		this.nbrPointPeriodeAct = nbrPointPeriodeAct;
	}






	public int getRangTotal() {
		return rangTotal;
	}






	public void setRangTotal(int rangTotal) {
		this.rangTotal = rangTotal;
	}






	public int getRangPeriodeAct() {
		return rangPeriodeAct;
	}






	public void setRangPeriodeAct(int rangPeriodeAct) {
		this.rangPeriodeAct = rangPeriodeAct;
	}






	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
	}






	public long getId() {
		return id;
	}






	public void setId(long id) {
		this.id = id;
	}






	public int getNbrPointJourneAct() {
		return nbrPointJourneAct;
	}






	public void setNbrPointJourneAct(int nbrPointJourneAct) {
		this.nbrPointJourneAct = nbrPointJourneAct;
	}





	public String getNomEquipe() {
		return nomEquipe;
	}





	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}





	public long getId_user() {
		return id_user;
	}





	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	
}
