package com.abd.mfc.jsf.model;

public enum FormationEnum {

	Q_Q_D(1, "4-4-2", 4 , 4 , 2), T_Q_T(2, "3-4-3", 3 , 4 , 3), Q_T_T(3, "4-3-3", 4 , 3 , 3);
	
	private int id;
	private String label;
	private int nbD;
	private int nbM;
	private int nbA;
	
	
	private FormationEnum(int id, String label, int nbD, int nbM, int nbA) {
		this.id = id;
		this.label = label;
		this.nbD = nbD;
		this.nbM = nbM;
		this.nbA = nbA;
	}


	public String getLabel() {
		return label;
	}


	public int getNbD() {
		return nbD;
	}


	public int getNbM() {
		return nbM;
	}


	public int getNbA() {
		return nbA;
	}


	public int getId() {
		return id;
	}
	
	
}
