package com.abd.mfc.vo;

import java.util.List;

import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserFilter;
import com.abd.mfc.entities.PointUserJournee;

public class EquipeUserFilterVO extends VO<EquipeUserFilter> {

	private long id;
	private String nom;
	
	private long id_championnat;
	private long id_user;
	private String nomUser;
	
	private PointUserVO pointUserS;
	private PointUserVO pointUserJ;
	
	private List<MessageVO> errors;
	
	
private PaginationVO pagination;
	
	private String sortBy;
	
	@Override
	public EquipeUserFilter convertToEntity() {
		return null;
	}
	
	
	public EquipeUserFilterVO() {}
	
	public EquipeUserFilterVO(EquipeUserFilter e, boolean forSearch) {
		id = e.getId();
		nom = e.getNom();
		nomUser = e.getNomUser();
		
		id_user = e.getId_user();
		id_championnat = e.getId_championnat();
		if (e.getPointUserS() != null) {
			pointUserS = new PointUserVO(e.getPointUserS());
		}
		if (e.getPointUserJ() != null) {
			pointUserJ = new PointUserVO(e.getPointUserJ().getNbrPt());
		}
	}

	public EquipeUserFilterVO(EquipeUser e) {
		super();
		id = e.getId();
		nom = e.getNom();

		
		id_user = e.getId_user();
		id_championnat = e.getId_championnat();
		if (e.getPointUserS() != null) {
			pointUserS = new PointUserVO(e.getPointUserS());
		}
	}


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


	public long getId_championnat() {
		return id_championnat;
	}


	public void setId_championnat(long id_championnat) {
		this.id_championnat = id_championnat;
	}


	public long getId_user() {
		return id_user;
	}


	public void setId_user(long id_user) {
		this.id_user = id_user;
	}


	public String getNomUser() {
		return nomUser;
	}


	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}


	public PointUserVO getPointUserS() {
		return pointUserS;
	}


	public void setPointUserS(PointUserVO pointUserS) {
		this.pointUserS = pointUserS;
	}


	public List<MessageVO> getErrors() {
		return errors;
	}


	public void setErrors(List<MessageVO> errors) {
		this.errors = errors;
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


	public PointUserVO getPointUserJ() {
		return pointUserJ;
	}


	public void setPointUserJ(PointUserVO pointUserJ) {
		this.pointUserJ = pointUserJ;
	}
	



	

}
