package com.abd.mfc.enums;

public enum TypePointEnum {

	//idChampionnat
	TOT_JOURNEE('j'),//idJournee
	TOT_PERIODE('p'),//idPeriode
	TOT_SAISON('s'),//idSaison
	;
	
	
	private char abr;

	private TypePointEnum(char abr) {
		this.abr = abr;
	}

	public char getAbr() {
		return abr;
	}
}
