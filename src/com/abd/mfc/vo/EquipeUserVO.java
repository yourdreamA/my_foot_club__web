package com.abd.mfc.vo;

import java.util.Date;
import java.util.List;

import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserJ;
import com.abd.mfc.entities.PointUser;
import com.abd.mfc.jsf.model.FormationEnum;

public class EquipeUserVO extends VO<EquipeUser> {

	private long id;
	private String nom;
	private FormationEnum formation;
	private JoueurVO gardien;
	private JoueurVO defenseur1;
	private JoueurVO defenseur2;
	private JoueurVO defenseur3;
	private JoueurVO defenseur4;
	private JoueurVO milieu1;
	private JoueurVO milieu2;
	private JoueurVO milieu3;
	private JoueurVO milieu4;
	private JoueurVO att1;
	private JoueurVO att2;
	private JoueurVO att3;
	
	private long id_championnat;
	private long id_user;
	private int soldeUser;
	private int soldeUserRestant;
	
	private PointUserVO pointUserS;
	
	private PointUserVO pointUserJ;
	
	private List<MessageVO> errors;
	
	
private PaginationVO pagination;
	
	private String sortBy;
	
	private Date lastUpdate;
	
	@Override
	public EquipeUser convertToEntity() {

		EquipeUser e = new EquipeUser();
		
		e.setId(id);
		
		e.setId_user(id_user);
		e.setId_championnat(id_championnat);
		e.setSoldeUser(soldeUser);
		
		e.setNom(nom);
		
		e.setFormation(formation.getId());
		
		e.setGardien(gardien.convertToEntity());
		
		e.setDefenseur1(defenseur1.convertToEntity());
		e.setDefenseur2(defenseur2.convertToEntity());
		e.setDefenseur3(defenseur3.convertToEntity());
		if (defenseur4 != null) {
			e.setDefenseur4(defenseur4.convertToEntity());
		}
		
		
		e.setMilieu1(milieu1.convertToEntity());
		e.setMilieu2(milieu2.convertToEntity());
		e.setMilieu3(milieu3.convertToEntity());
		if (milieu4 != null) {
			e.setMilieu4(milieu4.convertToEntity());
		}
		
		
		e.setAtt1(att1.convertToEntity());
		e.setAtt2(att2.convertToEntity());
		if (att3 != null) {
			e.setAtt3(att3.convertToEntity());
		}
		
		if (pointUserS != null) {
			e.setPointUserS(pointUserS.convertToEntity());
		}
		
		e.setLastUpdate(lastUpdate);
		
	
		return e;
	}
	
	public EquipeUserJ convertToEntityJ(int journee) {


		EquipeUserJ e = new EquipeUserJ();
		e.setJournee(journee);
		//e.setId(id);
		
		e.setId_user(id_user);
		e.setId_championnat(id_championnat);
		e.setSoldeUser(soldeUser);
		
		e.setNom(nom);
		
		e.setFormation(formation.getId());
		
		if (gardien != null) {
			e.setGardien(gardien.convertToEntity());
		}
		
		
		if (defenseur1 != null) {
			e.setDefenseur1(defenseur1.convertToEntity());
		}
		if (defenseur2 != null) {
			e.setDefenseur2(defenseur2.convertToEntity());
		}
		if (defenseur3 != null) {
			e.setDefenseur3(defenseur3.convertToEntity());
		}
		if (defenseur4 != null) {
			e.setDefenseur4(defenseur4.convertToEntity());
		}
		
		if (milieu1 != null) {
			e.setMilieu1(milieu1.convertToEntity());
		}
		if (milieu2 != null) {
			e.setMilieu2(milieu2.convertToEntity());
		}
		if (milieu3 != null) {
			e.setMilieu3(milieu3.convertToEntity());
		}
		if (milieu4 != null) {
			e.setMilieu4(milieu4.convertToEntity());
		}
		
		if (att1 != null) {
			e.setAtt1(att1.convertToEntity());
		}
		if (att2 != null) {
			e.setAtt2(att2.convertToEntity());
		}
		if (att3 != null) {
			e.setAtt3(att3.convertToEntity());
		}
		
	
		return e;
	
	}
	
	public EquipeUserVO() {}
	
	public EquipeUserVO(EquipeUser e, boolean forSearch) {
		id = e.getId();
		nom = e.getNom();
		
		id_user = e.getId_user();
		id_championnat = e.getId_championnat();
		soldeUser = e.getSoldeUser();
		if (e.getPointUserS() != null) {
			pointUserS = new PointUserVO(e.getPointUserS());
		}
		lastUpdate = e.getLastUpdate();
	}

	public void EquipeUserVO() {
		// TODO Auto-generated method stub

	}
	
	public EquipeUserVO(EquipeUser e) {
		super();
		id = e.getId();
		nom = e.getNom();
		switch (e.getFormation()) {
		case 1:
			formation = FormationEnum.Q_Q_D;
			break;
		case 2:
			formation = FormationEnum.T_Q_T;
			break;
		case 3:
			formation = FormationEnum.Q_T_T;
			break;

		default:
			break;
		}
		if (e.getGardien() != null) {
			this.gardien = new JoueurVO(e.getGardien());
		}
		if (e.getDefenseur1() != null) {
			this.defenseur1 = new JoueurVO(e.getDefenseur1());
		}
		if (e.getDefenseur2() != null) {
			this.defenseur2 = new JoueurVO(e.getDefenseur2());
		}
		if (e.getDefenseur3() != null) {
			this.defenseur3 = new JoueurVO(e.getDefenseur3());
		}
		if (e.getDefenseur4() != null) {
			this.defenseur4 = new JoueurVO(e.getDefenseur4());
		}
		
		if (e.getMilieu1() != null) {
			this.milieu1 = new JoueurVO(e.getMilieu1());
		}
		if (e.getMilieu2() != null) {
			this.milieu2 = new JoueurVO(e.getMilieu2());
		}
		if (e.getMilieu3() != null) {
			this.milieu3 = new JoueurVO(e.getMilieu3());
		}
		if (e.getMilieu4() != null) {
			this.milieu4 = new JoueurVO(e.getMilieu4());
		}
		
		if (e.getAtt1() != null) {
			this.att1 = new JoueurVO(e.getAtt1());
		}
		if (e.getAtt2() != null) {
			this.att2 = new JoueurVO(e.getAtt2());
		}
		if (e.getAtt3() != null) {
			this.att3 = new JoueurVO(e.getAtt3());
		}
		
		id_user = e.getId_user();
		id_championnat = e.getId_championnat();
		soldeUser = e.getSoldeUser();
		if (e.getPointUserS() != null) {
			pointUserS = new PointUserVO(e.getPointUserS());
		}
		lastUpdate = e.getLastUpdate();
	}
	
	public EquipeUserVO(EquipeUserJ e) {

		super();
		id = e.getId();
		nom = e.getNom();
		switch (e.getFormation()) {
		case 1:
			formation = FormationEnum.Q_Q_D;
			break;
		case 2:
			formation = FormationEnum.T_Q_T;
			break;
		case 3:
			formation = FormationEnum.Q_T_T;
			break;

		default:
			break;
		}
		if (e.getGardien() != null) {
			this.gardien = new JoueurVO(e.getGardien());
		}
		if (e.getDefenseur1() != null) {
			this.defenseur1 = new JoueurVO(e.getDefenseur1());
		}
		if (e.getDefenseur2() != null) {
			this.defenseur2 = new JoueurVO(e.getDefenseur2());
		}
		if (e.getDefenseur3() != null) {
			this.defenseur3 = new JoueurVO(e.getDefenseur3());
		}
		if (e.getDefenseur4() != null) {
			this.defenseur4 = new JoueurVO(e.getDefenseur4());
		}
		
		if (e.getMilieu1() != null) {
			this.milieu1 = new JoueurVO(e.getMilieu1());
		}
		if (e.getMilieu2() != null) {
			this.milieu2 = new JoueurVO(e.getMilieu2());
		}
		if (e.getMilieu3() != null) {
			this.milieu3 = new JoueurVO(e.getMilieu3());
		}
		if (e.getMilieu4() != null) {
			this.milieu4 = new JoueurVO(e.getMilieu4());
		}
		
		if (e.getAtt1() != null) {
			this.att1 = new JoueurVO(e.getAtt1());
		}
		if (e.getAtt2() != null) {
			this.att2 = new JoueurVO(e.getAtt2());
		}
		if (e.getAtt3() != null) {
			this.att3 = new JoueurVO(e.getAtt3());
		}
		
		id_user = e.getId_user();
		id_championnat = e.getId_championnat();
		soldeUser = e.getSoldeUser();
		
		if (e.getPointUser() != null) {
			pointUserJ = new PointUserVO(e.getPointUser().getNbrPt());
		}
	}



	public FormationEnum getFormation() {
		return formation;
	}

	public void setFormation(FormationEnum formation) {
		this.formation = formation;
	}

	public JoueurVO getGardien() {
		return gardien;
	}

	public void setGardien(JoueurVO gardien) {
		this.gardien = gardien;
	}

	public JoueurVO getDefenseur1() {
		return defenseur1;
	}

	public void setDefenseur1(JoueurVO defenseur1) {
		this.defenseur1 = defenseur1;
	}

	public JoueurVO getDefenseur2() {
		return defenseur2;
	}

	public void setDefenseur2(JoueurVO defenseur2) {
		this.defenseur2 = defenseur2;
	}

	public JoueurVO getDefenseur3() {
		return defenseur3;
	}

	public void setDefenseur3(JoueurVO defenseur3) {
		this.defenseur3 = defenseur3;
	}

	public JoueurVO getDefenseur4() {
		return defenseur4;
	}

	public void setDefenseur4(JoueurVO defenseur4) {
		this.defenseur4 = defenseur4;
	}

	public JoueurVO getMilieu1() {
		return milieu1;
	}

	public void setMilieu1(JoueurVO milieu1) {
		this.milieu1 = milieu1;
	}

	public JoueurVO getMilieu2() {
		return milieu2;
	}

	public void setMilieu2(JoueurVO milieu2) {
		this.milieu2 = milieu2;
	}

	public JoueurVO getMilieu3() {
		return milieu3;
	}

	public void setMilieu3(JoueurVO milieu3) {
		this.milieu3 = milieu3;
	}

	public JoueurVO getMilieu4() {
		return milieu4;
	}

	public void setMilieu4(JoueurVO milieu4) {
		this.milieu4 = milieu4;
	}

	public JoueurVO getAtt1() {
		return att1;
	}

	public void setAtt1(JoueurVO att1) {
		this.att1 = att1;
	}

	public JoueurVO getAtt2() {
		return att2;
	}

	public void setAtt2(JoueurVO att2) {
		this.att2 = att2;
	}

	public JoueurVO getAtt3() {
		return att3;
	}

	public void setAtt3(JoueurVO att3) {
		this.att3 = att3;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public int getSoldeUser() {
		return soldeUser;
	}

	public void setSoldeUser(int soldeUser) {
		this.soldeUser = soldeUser;
	}

	public List<MessageVO> getErrors() {
		return errors;
	}

	public void setErrors(List<MessageVO> errors) {
		this.errors = errors;
	}

	public long getId_championnat() {
		return id_championnat;
	}

	public void setId_championnat(long id_championnat) {
		this.id_championnat = id_championnat;
	}

	public int getSoldeUserRestant() {
		return soldeUserRestant;
	}

	public void setSoldeUserRestant(int soldeUserRestant) {
		this.soldeUserRestant = soldeUserRestant;
	}

	public PointUserVO getPointUserS() {
		return pointUserS;
	}

	public void setPointUserS(PointUserVO pointUserS) {
		this.pointUserS = pointUserS;
	}

	public PaginationVO getPagination() {
		return pagination;
	}

	public void setPagination(PaginationVO pagination) {
		this.pagination = pagination;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public PointUserVO getPointUserJ() {
		return pointUserJ;
	}

	public void setPointUserJ(PointUserVO pointUserJ) {
		this.pointUserJ = pointUserJ;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
