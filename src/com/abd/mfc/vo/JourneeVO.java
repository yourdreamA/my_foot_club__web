package com.abd.mfc.vo;

import java.util.Date;

import com.abd.mfc.entities.Journee;

public class JourneeVO extends VO<Journee> {

	private long id;
	
	private int numero;
	
	private Date dateDeb;
	private Date dateFin;
	
	private long idChampionnat;
	private Long idPeriode;
	
	public JourneeVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public JourneeVO(Journee e) {
		super();
		this.id = e.getId();
		this.numero = e.getNumero();
		this.dateDeb = e.getDateDeb();
		this.dateFin = e.getDateFin();
		this.idChampionnat = e.getIdChampionnat();
		this.idPeriode = e.getIdPeriode();
	}



	@Override
	public Journee convertToEntity() {
		Journee e = new Journee();
		e.setId(id);
		
		return e;
	}

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

	public long getIdChampionnat() {
		return idChampionnat;
	}

	public void setIdChampionnat(long idChampionnat) {
		this.idChampionnat = idChampionnat;
	}

	public Long getIdPeriode() {
		return idPeriode;
	}

	public void setIdPeriode(Long idPeriode) {
		this.idPeriode = idPeriode;
	}

}
