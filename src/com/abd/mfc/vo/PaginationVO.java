package com.abd.mfc.vo;

public class PaginationVO {

	private int numeroPage;
	private int nombreLigne;//par page
	private int totalPage;
	private long totalResultat;
	
	private boolean first;
	private boolean last;
	
	public PaginationVO() {}
	
	public PaginationVO(int nombreLigne) {
		super();
		this.nombreLigne = nombreLigne;
	}




	public int getDebut() {
		return (numeroPage * nombreLigne) + 1;
	}

	public long getFin() {
		return Math.min(getDebut() + nombreLigne - 1, totalResultat);
	}
	
	public boolean isFirst() {
		return first;
	}
	
	public boolean isLast() {
		return last;
	}
	
	
	public int getNumeroPage() {
		return numeroPage;
	}
	public void setNumeroPage(int numeroPage) {
		this.numeroPage = numeroPage;
	}
	public int getNombreLigne() {
		return nombreLigne;
	}
	public void setNombreLigne(int nombreLigne) {
		this.nombreLigne = nombreLigne;
	}
	public long getTotalResultat() {
		return totalResultat;
	}
	public void setTotalResultat(long totalResultat) {
		this.totalResultat = totalResultat;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
