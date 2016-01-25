package com.abd.mfc.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="mfc_point_user")
@DiscriminatorValue("p")
public class PointUserPeriode extends PointUserParent {

	private Long idPeriode;

	public Long getIdPeriode() {
		return idPeriode;
	}

	public void setIdPeriode(Long idPeriode) {
		this.idPeriode = idPeriode;
	}
}
