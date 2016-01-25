package com.abd.mfc.vo;

import com.abd.mfc.entities.Championnat;

public class ChampionnatVO extends VO<Championnat> {

	private long id;
	
	private String code;
	private String libelle;
	private String logo;
	private long idSaison;
	private String libelleSaison;
	private int actualJournee;
	
	public ChampionnatVO() {}



	public ChampionnatVO(Championnat e) {
		super();
		this.id = e.getId();
		this.code = e.getCode();
		this.libelle = e.getLibelle();
		this.logo = e.getLogo();
		this.idSaison = e.getSaison().getId();
		libelleSaison = e.getSaison().getLibelle();
		actualJournee = e.getActualJournee();
	}



	@Override
	public Championnat convertToEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public long getIdSaison() {
		return idSaison;
	}

	public void setIdSaison(long idSaison) {
		this.idSaison = idSaison;
	}



	public String getLogo() {
		return logo;
	}



	public void setLogo(String logo) {
		this.logo = logo;
	}



	public String getLibelleSaison() {
		return libelleSaison;
	}



	public void setLibelleSaison(String libelleSaison) {
		this.libelleSaison = libelleSaison;
	}



	public int getActualJournee() {
		return actualJournee;
	}



	public void setActualJournee(int actualJournee) {
		this.actualJournee = actualJournee;
	}
}
