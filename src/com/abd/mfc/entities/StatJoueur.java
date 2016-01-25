package com.abd.mfc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@NamedQueries({
	@NamedQuery(
	name = "findStatJoueur",
	query = "from StatJoueur s where s.journee.idChampionnat = :idChampionnat and s.idJoueur = :idJoueur"
	),
	@NamedQuery(
			name = "findStatEquipe",
			query = "from StatJoueur s where s.journee.idChampionnat = :idChampionnat and s.journee.numero = :journee and s.idJoueur in (:idsJoueur)"
			),
	@NamedQuery(
			name = "findStatJournee",
			query = "from StatJoueur s where s.journee.idChampionnat = :idChampionnat and s.journee.numero = :journee"
			),
	@NamedQuery(
			name = "findTotalPointJoueur",
			query = "select s.idJoueur, sum(s.totalP) from StatJoueur s where s.journee.idChampionnat = :idChampionnat group by s.idJoueur"
			),
	@NamedQuery(
			name = "updateTotalPointJoueur",
			query = "update Joueur j set totalPoint=(select  sum(s.totalP) from StatJoueur s where j.id = s.idJoueur group by s.idJoueur)"
			),
	@NamedQuery(
			name = "updatePointJourneeJoueur",
			query = "update Joueur j set pointJournee=(select s.totalP from StatJoueur s where j.id = s.idJoueur and s.journee.idChampionnat = :idChampionnat and s.journee.numero = :journee)"
			)
})

/*@NamedNativeQueries({
	@NamedNativeQuery(
	name = "updateTotalPointJoueurMySQL",
	query = "update mfc_joueur set totalPoint=(select coalesce(sum(statjoueur1_.totalP), 0) from mfc_stat_joueur statjoueur1_ where mfc_joueur.id=statjoueur1_.idJoueur group by statjoueur1_.idJoueur)",
	resultClass = StatJoueur.class
	)
})*/

@Entity
@Table(name="mfc_stat_joueur")
public class StatJoueur extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int participe;
	private int nbrMinuteJoue;
	private int nbrBut;
	private int nbrAssist;
	private int nbrShot;
	private int nbrShotInG;
	private int nbrPenalty;
	private int nbrPenaltyRate;
	private int nbrSave;
	private int nbrSavePenalty;
	private int nbrButRecu;
	private int cartonJ;
	private int cartonR;
	private int totalP;
	
	@Formula("(select j.position from mfc_joueur j where j.id=idJoueur)")
	private int positionJoueur;
	
	@ManyToOne
	private Journee journee;
	
	private long idJoueur;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getParticipe() {
		return participe;
	}
	public void setParticipe(int participe) {
		this.participe = participe;
	}
	public int getNbrMinuteJoue() {
		return nbrMinuteJoue;
	}
	public void setNbrMinuteJoue(int nbrMinuteJoue) {
		this.nbrMinuteJoue = nbrMinuteJoue;
	}
	public int getNbrBut() {
		return nbrBut;
	}
	public void setNbrBut(int nbrBut) {
		this.nbrBut = nbrBut;
	}
	public int getNbrAssist() {
		return nbrAssist;
	}
	public void setNbrAssist(int nbrAssist) {
		this.nbrAssist = nbrAssist;
	}
	public int getNbrShot() {
		return nbrShot;
	}
	public void setNbrShot(int nbrShot) {
		this.nbrShot = nbrShot;
	}
	public int getNbrShotInG() {
		return nbrShotInG;
	}
	public void setNbrShotInG(int nbrShotInG) {
		this.nbrShotInG = nbrShotInG;
	}
	public int getNbrPenalty() {
		return nbrPenalty;
	}
	public void setNbrPenalty(int nbrPenalty) {
		this.nbrPenalty = nbrPenalty;
	}
	public int getNbrPenaltyRate() {
		return nbrPenaltyRate;
	}
	public void setNbrPenaltyRate(int nbrPenaltyRate) {
		this.nbrPenaltyRate = nbrPenaltyRate;
	}
	public int getNbrSave() {
		return nbrSave;
	}
	public void setNbrSave(int nbrSave) {
		this.nbrSave = nbrSave;
	}
	public int getNbrSavePenalty() {
		return nbrSavePenalty;
	}
	public void setNbrSavePenalty(int nbrSavePenalty) {
		this.nbrSavePenalty = nbrSavePenalty;
	}
	public int getNbrButRecu() {
		return nbrButRecu;
	}
	public void setNbrButRecu(int nbrButRecu) {
		this.nbrButRecu = nbrButRecu;
	}
	public int getCartonJ() {
		return cartonJ;
	}
	public void setCartonJ(int cartonJ) {
		this.cartonJ = cartonJ;
	}
	public int getCartonR() {
		return cartonR;
	}
	public void setCartonR(int cartonR) {
		this.cartonR = cartonR;
	}
	public int getTotalP() {
		return totalP;
	}
	public void setTotalP(int totalP) {
		this.totalP = totalP;
	}
	public Journee getJournee() {
		return journee;
	}
	public void setJournee(Journee journee) {
		this.journee = journee;
	}
	public long getIdJoueur() {
		return idJoueur;
	}
	public void setIdJoueur(long idJoueur) {
		this.idJoueur = idJoueur;
	}
	public int getPositionJoueur() {
		return positionJoueur;
	}
	public void setPositionJoueur(int positionJoueur) {
		this.positionJoueur = positionJoueur;
	}
	
	
}
