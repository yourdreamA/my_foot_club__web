package com.abd.mfc.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;

@NamedQueries({
//	@NamedQuery(
//	name = "findPointUserSaison",
//	query = "from PointUser p where p.idUser = :idUser and p.idChampionnat = :idChampionnat"
//	),
//	@NamedQuery(
//			name = "findUserWithoutPoint",
//			query = "from User u where not exists (select 1 from PointUser p where p.idUser = u.id and p.idChampionnat = :idChampionnat)"
//			),
//	@NamedQuery(
//		name = "updatePointUserSaison",
//		query = "update PointUser p set nbrPt=:nbrPt where p.idUser=:idUser and p.idChampionnat = :idChampionnat"
//	)
})

@Entity
@DiscriminatorValue(value="s")
public class PointUser extends PointUserParent {

	
//	@Formula("(select u.username from mfc_user u where u.id=idUser)")
//	private String username;
//	@Formula("(select CONCAT(u.lastname,' ',u.firstname) from mfc_user u where u.id=idUser)")
//	private String name;
	
}
