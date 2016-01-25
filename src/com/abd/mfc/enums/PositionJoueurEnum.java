package com.abd.mfc.enums;

public enum PositionJoueurEnum {

	G(1, "pos_g"), D(2, "pos_d"), M(3, "pos_m"), A(4, "pos_a");
	
	private int id;
	private String cle;
	
	private PositionJoueurEnum(int id, String cle) {
		this.id = id;
		this.cle = cle;
	}

	public int getId() {
		return id;
	}

	public String getCle() {
		return cle;
	}
	
}
