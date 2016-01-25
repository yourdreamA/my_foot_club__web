package com.abd.mfc.vo;

import com.abd.mfc.entities.PointUser;

public class PointUserVO extends VO<PointUser> {

	private long id;
	
	private int nbrPt;
	private int rang;
	
	private long idUser;
//	private String username;
//	private String name;
	
	private PaginationVO pagination;
	
	private String sortBy;
	
	public PointUserVO() {}
	




	public PointUserVO(int nbrPt) {
		this.nbrPt = nbrPt;
	}


	public PointUserVO(PointUser u) {
		super();
		this.id = u.getId();
		this.nbrPt = u.getNbrPt();
		rang = u.getRang();
	/*	switch (u.getTypePt()) {
		case 's':
			typePt = TypePointEnum.TOT_SAISON;
			break;
		case 'j':
			typePt = TypePointEnum.TOT_JOURNEE;
			break;
		case 'p':
			typePt = TypePointEnum.TOT_PERIODE;
			break;
		default:
			break;
		}
		this.idJournee = u.getIdJournee();
		this.idPeriode = u.getIdPeriode();*/
		//this.idUser = u.getIdUser();
//		username = u.getUsername();
//		name = u.getName();
		
	}







	@Override
	public PointUser convertToEntity() {
		PointUser e = new PointUser();
		e.setId(id);
		e.setNbrPt(nbrPt) ;
		/*if (typePt != null) {
			e.setTypePt(typePt.getAbr());
		}
		
		e.setIdJournee(idJournee) ;
		e.setIdPeriode(idPeriode) ;*/
		//e.setIdUser(idUser) ;
		
		return e;
	}







	public long getId() {
		return id;
	}







	public void setId(long id) {
		this.id = id;
	}







	public int getNbrPt() {
		return nbrPt;
	}







	public void setNbrPt(int nbrPt) {
		this.nbrPt = nbrPt;
	}








	public long getIdUser() {
		return idUser;
	}







	public void setIdUser(long idUser) {
		this.idUser = idUser;
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





	public int getRang() {
		return rang;
	}





	public void setRang(int rang) {
		this.rang = rang;
	}
}
