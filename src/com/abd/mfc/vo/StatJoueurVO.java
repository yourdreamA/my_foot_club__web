package com.abd.mfc.vo;

import com.abd.mfc.entities.StatJoueur;
import com.abd.stat.StatJoueurParse;

public class StatJoueurVO extends VO<StatJoueur> {

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
	private JourneeVO journee;
	private int positionJoueur;
	private int cleanSheetS;
	
	private String positionJoueurLabel;
	
	private long idJoueur;
	
	private int numJoueur;
	private long idEquipe;
	
	public StatJoueurVO() {}
	
	public StatJoueurVO(StatJoueurParse e) {
		this.participe = Integer.valueOf(e.getParticipe());
		this.nbrMinuteJoue = Integer.valueOf(e.getNbrMinuteJoue());
		this.nbrBut = Integer.valueOf(e.getNbrBut());
		this.nbrAssist = Integer.valueOf(e.getNbrAssist());
		this.nbrShot = Integer.valueOf(e.getNbrShot());
		this.nbrShotInG = Integer.valueOf(e.getNbrShotInG());
		this.nbrPenalty = Integer.valueOf(e.getNbrPenalty());
		
		this.nbrSave = Integer.valueOf(e.getNbrSave());
		this.nbrButRecu = Integer.valueOf(e.getNbrButRecu());
		this.cartonJ = Integer.valueOf(e.getCartonJ());
		this.cartonR = Integer.valueOf(e.getCartonR());
		
		this.nbrSavePenalty = 0;
		this.nbrPenaltyRate = 0;
		this.totalP = 0;
		
		//journee_id
		//idjoueur
		numJoueur = Integer.valueOf(e.getNumJ());
		/*(select j.id from mfc_joueur j where j.equipe_id=").append(idEq)
				.append(" and j.numero=").append(j.getNumJ()).append(")")*/
		
	}
	
	
	public StatJoueurVO(StatJoueur e) {
		this.id = e.getId();
		this.participe = e.getParticipe();
		this.nbrMinuteJoue = e.getNbrMinuteJoue();
		this.nbrBut = e.getNbrBut();
		this.nbrAssist = e.getNbrAssist();
		this.nbrShot = e.getNbrShot();
		this.nbrShotInG = e.getNbrShotInG();
		this.nbrPenalty = e.getNbrPenalty();
		this.nbrPenaltyRate = e.getNbrPenaltyRate();
		this.nbrSave = e.getNbrSave();
		this.nbrSavePenalty = e.getNbrSavePenalty();
		this.nbrButRecu = e.getNbrButRecu();
		this.cartonJ = e.getCartonJ();
		this.cartonR = e.getCartonR();
		this.totalP = e.getTotalP();
		if (e.getJournee() != null) {
			journee = new JourneeVO(e.getJournee());
		}
		positionJoueur = e.getPositionJoueur();
	}



	@Override
	public StatJoueur convertToEntity() {
		StatJoueur e = new StatJoueur();
		e.setParticipe(participe);
		e.setNbrMinuteJoue(nbrMinuteJoue);
		e.setNbrBut(nbrBut);
		e.setNbrAssist(nbrAssist);
		e.setNbrShot(nbrShot);
		e.setNbrShotInG(nbrShotInG);
		e.setNbrPenalty(nbrPenalty);
		e.setNbrPenaltyRate(nbrPenaltyRate);
		e.setNbrSave(nbrSave);
		e.setNbrSavePenalty(nbrSavePenalty);
		e.setNbrButRecu(nbrButRecu) ;
		e.setCartonJ(cartonJ);
		e.setCartonR(cartonR);
		e.setTotalP(totalP);
		
	
		
		return e;
	}
	
	/************************************************************************************************************************************/
	/**********************************************         TABLEAU          ************************************************************/
	/************************************************************************************************************************************/
	
	/*public String getJourneeC() {
		StringBuilder s = new StringBuilder(journee.getNumero());
		if (participe == 1) {
			s.append(b)
		}
		
		return s.toString();
	}*/
	
	public String getPtMinutesJoue() {
		int res = participe;
		if (nbrMinuteJoue >= 60) {
			res += 2;
		}
		return res == 0 ? "-" : String.valueOf(res);
	}
	
	public int getNbrButPt() {
		int pt = 0;
		if (nbrBut > 0) {
			switch (positionJoueur) {
			case 1:
				pt = getNbrBut() * 8;
				break;
			case 2:
				pt = getNbrBut() * 6;
				break;
			case 3:
				pt = getNbrBut() * 5;
				break;
			case 4:
				pt = getNbrBut()* 4;
				break;
				
			default:
				break;
			}
		}
		
		return pt;
	}
	
	public int getNbrAssistPt() {
		int pt = 0;
		if (getNbrAssist() > 0) {
			
			switch (getPositionJoueur()) {
			case 1:
				pt = getNbrAssist() * 3;
				break;
			case 2:
				pt = getNbrAssist() * 3;
				break;
			case 3:
				pt = getNbrAssist() * 2;
				break;
			case 4:
				pt = getNbrAssist() * 2;
				break;
				
			default:
				break;
			}
		}
		
		return pt;
	}
	
	public int getNbrShotInGPt() {
		int pt = 0;
		if (getNbrShotInG() > 0) {
			
			switch (getPositionJoueur()) {
			case 1:
				pt = getNbrShotInG() * 2;
				break;
			case 2:
				pt = getNbrShotInG() * 2;
				break;
			case 3:
				pt = getNbrShotInG();
				break;
			case 4:
				pt = getNbrShotInG();
				break;
				
			default:
				break;
			}
		}
		return pt;
	}
	
	public int getNbrPenaltyPt() {
		int pt = 0;
		if (getNbrPenalty() > 0) {
			switch (getPositionJoueur()) {
			case 1:
				pt = getNbrPenalty() * 5;
				break;
			case 2:
				pt = getNbrPenalty() * 3;
				break;
			case 3:
				pt = getNbrPenalty() * 3;
				break;
			case 4:
				pt = getNbrPenalty() * 3;
				break;
				
			default:
				break;
			}
		}
		return pt;
	}
	
	public int getNbrSavePenaltyPt() {
		int pt = 0;
		if (getNbrSavePenalty() > 0) {
			pt = (getNbrSavePenalty()*4);
		}
		return pt;
	}
	
	public int getNbrPenaltyRatePt() {
		int pt = 0;
		if (getNbrPenaltyRate() > 0) {
			pt = (getNbrPenaltyRate() * -2);
		}
		return pt;
	}
	
	public int getCartonJPt() {
		int pt = 0;
		if (getCartonJ() > 0) {
			pt--;
			if (getPositionJoueur() == 1) {
				pt--;
			}
		}
		return pt;
	}
	
	public int getCleanSheetF() {
		return (getNbrButRecu() == 0 && getNbrMinuteJoue() >= 60 ? 1 : 0);
	}
	
	public int getCleanSheet() {
		int cleanSheet = 0;
		if (getNbrButRecu() == 0 && getNbrMinuteJoue() >= 60) {
			switch (getPositionJoueur()) {
			case 1:
				cleanSheet = 3;
				break;
			case 2:
				cleanSheet = 2;
				break;
			case 3:
				cleanSheet = 1;
				break;

			default:
				break;
			}
		}
		
		return cleanSheet;
	}
	
	public int getNbrButRecuPt() {
		int pt = 0;
		if (getParticipe() > 0 && getNbrButRecu() > 0) {
			
			switch (getPositionJoueur()) {
			case 1:
				pt -= getNbrButRecu();
				break;
			case 2:
				pt -= getNbrButRecu();
				break;
			
			default:
				break;
			}
		}
		
		return pt;
	}
	
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



	public JourneeVO getJournee() {
		return journee;
	}



	public void setJournee(JourneeVO journee) {
		this.journee = journee;
	}



	public int getPositionJoueur() {
		return positionJoueur;
	}



	public void setPositionJoueur(int positionJoueur) {
		this.positionJoueur = positionJoueur;
	}


	public int getCleanSheetS() {
		return cleanSheetS;
	}


	public void setCleanSheetS(int cleanSheetS) {
		this.cleanSheetS = cleanSheetS;
	}


	public String getPositionJoueurLabel() {
		return positionJoueurLabel;
	}


	public void setPositionJoueurLabel(String positionJoueurLabel) {
		this.positionJoueurLabel = positionJoueurLabel;
	}

	public long getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(long idEquipe) {
		this.idEquipe = idEquipe;
	}

	public long getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(long idJoueur) {
		this.idJoueur = idJoueur;
	}

	public int getNumJoueur() {
		return numJoueur;
	}

	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}
}
