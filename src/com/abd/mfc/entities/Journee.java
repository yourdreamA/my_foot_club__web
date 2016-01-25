package com.abd.mfc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
	name = "findJourneeByNumero",
	query = "from Journee where numero=:numero and idChampionnat=:idChampionnat"
	)
})

@Entity
@Table(name="mfc_journee")
public class Journee extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int numero;
	
	private Date dateDeb;
	private Date dateFin;
	
	private long idChampionnat;
	private Long idPeriode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public long getIdChampionnat() {
		return idChampionnat;
	}

	public void setIdChampionnat(long idChampionnat) {
		this.idChampionnat = idChampionnat;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Long getIdPeriode() {
		return idPeriode;
	}

	public void setIdPeriode(Long idPeriode) {
		this.idPeriode = idPeriode;
	}
	
	
}
