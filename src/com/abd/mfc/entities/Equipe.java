package com.abd.mfc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(
	name = "findEquipeByChampionnat",
	query = "from Equipe p where p.idChampionnat = :idChampionnat"
	)
})

@Entity
@Table(name="mfc_equipe")
public class Equipe extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String libelle;
	private String libelle_ar;
	private String logo;
	private long idChampionnat;
	
	
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
