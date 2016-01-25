package com.abd.mfc.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
//	@NamedQuery(
//			name = "calculPointUserSaison",
//			query = "select p.idUser, sum(p.nbrPt) from PointUserJournee p where p.idChampionnat = :idChampionnat group by p.idUser"
//			),

})


@Entity
@DiscriminatorValue("j")
public class PointUserJournee extends PointUserParent {

	private Long idJournee;
	
	public Long getIdJournee() {
		return idJournee;
	}

	public void setIdJournee(Long idJournee) {
		this.idJournee = idJournee;
	}
}
