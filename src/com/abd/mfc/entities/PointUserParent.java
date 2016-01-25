package com.abd.mfc.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="mfc_point_user")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="typePt",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue(value="w")
public abstract class PointUserParent extends BaseEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int nbrPt;
	private int rang;
	//private char typePt;//tot journee (j), tot saison(s), tot periode(p)===>   (par championnat)
	
	private long idUser;
	
	
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
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
}
