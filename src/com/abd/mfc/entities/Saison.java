package com.abd.mfc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="mfc_saison",
uniqueConstraints={@UniqueConstraint(columnNames={"libelle"})})
public class Saison {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String libelle;
	private String libelle_ar;
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
	public String getLibelle_ar() {
		return libelle_ar;
	}
	public void setLibelle_ar(String libelle_ar) {
		this.libelle_ar = libelle_ar;
	}
}
