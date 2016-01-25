package com.abd.mfc.vo;

import java.util.Date;
import java.util.List;

import com.abd.mfc.entities.Joueur;
import com.abd.mfc.enums.PositionJoueurEnum;

public class JoueurVO extends VO<Joueur> {

private long id;
	
	private String nomJ;
	private String nomJ_ar;
	private String nomC;
	private int numero;
	private Date dateNaissance;
	private int position;
	private int cout;
	
	private int pointJournee;
	private int totalPoint;
	private int pointPeriodeAct;
	
	private long idEquipe;
	private long idChampionnat;
	private String labelEquipe;
	private String labelEquipe_ar;
	
	private PaginationVO pagination;
	private String sortBy;
	
	private List<StatJoueurVO> stats;
	
	private StatJoueurVO statJ;
	
	private boolean appartienEquipe;
	
	private Integer diffCout;
	
	private EquipeVO equipe;
	
	private boolean withEquipe;
	
	
	public JoueurVO() {}
	
	public JoueurVO(Joueur e) {
		super();
		this.id = e.getId();
		this.nomJ = e.getNomJ();
		this.nomJ_ar = e.getNomJ_ar();
		this.nomC = e.getNomC();
		this.numero = e.getNumero();
		this.dateNaissance = e.getDateNaissance();
		this.position = e.getPosition();
		this.cout = e.getCout();
		if (e.getTotalPoint() != null) {
			this.totalPoint = e.getTotalPoint();
		}
		if (e.getPointPeriodeAct() != null) {
			this.pointPeriodeAct = e.getPointPeriodeAct();
		}
		
		if (e.getPointJournee() != null) {
			this.pointJournee = e.getPointJournee();
		}
		
		idEquipe = e.getEquipe().getId();
		idChampionnat = e.getEquipe().getIdChampionnat();
		labelEquipe = e.getEquipe().getLibelle();
		labelEquipe_ar = e.getEquipe().getLibelle_ar();
		diffCout = e.getDiffCout();
	}
	
	public JoueurVO(Joueur e, boolean withEquipe) {
		this(e);
		if (withEquipe) {
			equipe = new EquipeVO(e.getEquipe());
		}
		
	}



	@Override
	public Joueur convertToEntity() {
		Joueur e = new Joueur();
		e.setId(id);
		
		return e;
	}
	
	public String getLabelPosition() {
		String label = "";
		switch (position) {
		case 1:
			label = PositionJoueurEnum.G.getCle();
			break;
		case 2:
			label = PositionJoueurEnum.D.getCle();
			break;
		case 3:
			label = PositionJoueurEnum.M.getCle();
			break;
		case 4:
			label = PositionJoueurEnum.A.getCle();
			break;

		default:
			break;
		}
		
		return label;
	}
	
	public String getKeyJoueur() {
		return new StringBuilder(labelEquipe).append(".").append(numero).toString();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomC() {
		return nomC;
	}
	public void setNomC(String nomC) {
		this.nomC = nomC;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	public int getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
	public int getPointPeriodeAct() {
		return pointPeriodeAct;
	}
	public void setPointPeriodeAct(int pointPeriodeAct) {
		this.pointPeriodeAct = pointPeriodeAct;
	}



	public long getIdEquipe() {
		return idEquipe;
	}



	public void setIdEquipe(long idEquipe) {
		this.idEquipe = idEquipe;
	}



	public String getSortBy() {
		return sortBy;
	}



	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}



	public PaginationVO getPagination() {
		return pagination;
	}



	public void setPagination(PaginationVO pagination) {
		this.pagination = pagination;
	}

	public String getLabelEquipe() {
		return labelEquipe;
	}

	public void setLabelEquipe(String labelEquipe) {
		this.labelEquipe = labelEquipe;
	}

	public long getIdChampionnat() {
		return idChampionnat;
	}

	public void setIdChampionnat(long idChampionnat) {
		this.idChampionnat = idChampionnat;
	}

	public List<StatJoueurVO> getStats() {
		return stats;
	}

	public void setStats(List<StatJoueurVO> stats) {
		this.stats = stats;
	}

	public String getLabelEquipe_ar() {
		return labelEquipe_ar;
	}

	public void setLabelEquipe_ar(String labelEquipe_ar) {
		this.labelEquipe_ar = labelEquipe_ar;
	}

	public String getNomJ() {
		return nomJ;
	}

	public void setNomJ(String nomJ) {
		this.nomJ = nomJ;
	}

	public String getNomJ_ar() {
		return nomJ_ar;
	}

	public void setNomJ_ar(String nomJ_ar) {
		this.nomJ_ar = nomJ_ar;
	}

	public boolean isAppartienEquipe() {
		return appartienEquipe;
	}

	public void setAppartienEquipe(boolean appartienEquipe) {
		this.appartienEquipe = appartienEquipe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JoueurVO other = (JoueurVO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public StatJoueurVO getStatJ() {
		return statJ;
	}

	public void setStatJ(StatJoueurVO statJ) {
		this.statJ = statJ;
	}

	public int getPointJournee() {
		return pointJournee;
	}

	public void setPointJournee(int pointJournee) {
		this.pointJournee = pointJournee;
	}

	public Integer getDiffCout() {
		return diffCout;
	}

	public void setDiffCout(Integer diffCout) {
		this.diffCout = diffCout;
	}

	public EquipeVO getEquipe() {
		return equipe;
	}

	public void setEquipe(EquipeVO equipe) {
		this.equipe = equipe;
	}

	public boolean isWithEquipe() {
		return withEquipe;
	}

	public void setWithEquipe(boolean withEquipe) {
		this.withEquipe = withEquipe;
	}
	
	
	
	
}
