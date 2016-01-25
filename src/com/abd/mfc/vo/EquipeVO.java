package com.abd.mfc.vo;

import com.abd.mfc.entities.Equipe;

public class EquipeVO extends VO<Equipe> {

	private long id;
	private String libelle;
	private String libelle_ar;
	private String logo;
	private long idChampionnat;
	
	public EquipeVO() {}
	
	
	
	
	public EquipeVO(Equipe e) {
		super();
		this.id = e.getId();
		this.libelle = e.getLibelle();
		this.libelle_ar = e.getLibelle_ar();
		this.logo = e.getLogo();
		this.idChampionnat = e.getIdChampionnat();
	}




	@Override
	public Equipe convertToEntity() {
		Equipe e = new Equipe();
		e.setId(id);
		return e;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public long getIdChampionnat() {
		return idChampionnat;
	}


	public void setIdChampionnat(long idChampionnat) {
		this.idChampionnat = idChampionnat;
	}




	public String getLibelle_ar() {
		return libelle_ar;
	}




	public void setLibelle_ar(String libelle_ar) {
		this.libelle_ar = libelle_ar;
	}

}
