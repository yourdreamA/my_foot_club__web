package com.abd.mfc.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.entities.EquipeUserFilter;
import com.abd.mfc.enums.PositionJoueurEnum;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.jsf.model.FormationEnum;
import com.abd.mfc.jsf.model.PositionJoueurMO;
import com.abd.mfc.jsf.model.UserMO;
import com.abd.mfc.manager.EquipeUserManager;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.manager.ParamGenerauxManager;
import com.abd.mfc.vo.ChampionnatVO;
import com.abd.mfc.vo.EquipeUserFilterVO;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.EquipeVO;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.mfc.vo.StatJoueurVO;

@Controller("appBean")
@Scope("singleton")
public class ApplicationBean implements Serializable {

	public final static String DEFAULT_SHIRT = "shirts/shirt-blank.png";
	@Autowired
	private ParamGenerauxManager paramGenerauxManager;
	@Autowired
	private EquipeUserManager equipeUserManager;
	
	@Autowired
	private JoueurManager joueurManager;
	
	private int totalUsers;
	
	private List<JoueurVO> listJoueur;
	
	//list and combobox
	private PositionJoueurMO[] listPositionJoueur;
	private List<ChampionnatVO> listChampionnat;
	private Map<Long, List<EquipeVO>> mapEquipe;
	private Map<Long, List<Integer>> mapJournee;
	private Map<Long, String> mapShirts;
	
	private FormationEnum[] formations;
	
	private StatJoueurVO[] listReglesJoueurs;
	
	private List<UserMO> listUsersEng;
	private List<UserMO> listUsersEsp;
	private Map<Long, List<UserMO>> listUsers;
	
	private Map<Long, List<JoueurVO>> bestJoueurs;
	
	@PostConstruct
	public void init() throws ApplicationAbdException {
		
		initFormations();
		
		initPositionJoueur();
		
		//init list championnat
		listChampionnat = paramGenerauxManager.getAll();
		
		initMapEquipe();
		
		initMapJournee();
		//init total users
		
		initReglesJoueurs();
		
		initMapShirts();
		
		initBestUsers();
		
		initBestJoueurs();
		
		initListJoueurs();
		
	}
	
	private void initListJoueurs() throws ApplicationAbdException {
		listJoueur = joueurManager.getAll();
	}
	
	
	private void initBestUsers() {
		listUsers = new HashMap<Long, List<UserMO>>();
		ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO> resultat = null;
		EquipeUserVO filter = new EquipeUserVO();
		PaginationVO pagination = new PaginationVO(3);
		pagination.setNumeroPage(0);
		filter.setPagination(pagination);
		filter.setSortBy("pointUserJ");
		
		for (Long idChampionnat : mapEquipe.keySet()) {
			
			filter.setId_championnat(idChampionnat);
			try {
				resultat = equipeUserManager.search(filter);
			} catch (ApplicationAbdException e) {
				e.printStackTrace();
			}
			List<UserMO> listUser = null;
			if (resultat != null && resultat.getListVO() != null && !resultat.getListVO().isEmpty()) {
				listUser = new ArrayList<UserMO>();
				for (EquipeUserFilterVO userVO : resultat.getListVO()) {
					listUser.add(new UserMO(userVO));
				}
				listUsers.put(idChampionnat, listUser);
			}
		}
	}
	
	private void initBestJoueurs() throws ApplicationAbdException {
		bestJoueurs = new HashMap<Long, List<JoueurVO>>();
		
		for (Long idChampionnat : mapEquipe.keySet()) {
			if (idChampionnat > 2) {
				break;
			}
			
			bestJoueurs.put(idChampionnat, joueurManager.searchBestJoueur(idChampionnat));
			
		}
		
		
		
	}
	
	
	private void initPositionJoueur() {
		int i = 0;
		
		listPositionJoueur = new PositionJoueurMO[4];
		for (PositionJoueurEnum p : PositionJoueurEnum.values()) {
			PositionJoueurMO pos = new PositionJoueurMO();
			pos.setValeur(p.getId());
			pos.setLabel(p.getCle());
			listPositionJoueur[i++] = pos;
		}
	}
	
	private void initMapShirts() throws ApplicationAbdException {
		if (mapEquipe != null && !mapEquipe.isEmpty()) {
			mapShirts = new HashMap<Long, String>();
			for (Long idChampionnat : mapEquipe.keySet()) {
				List<EquipeVO> list = mapEquipe.get(idChampionnat);
				if (list != null && !list.isEmpty()) {
					for (EquipeVO equipeVO : list) {
						
						if (equipeVO.getId() <= 20) {
							mapShirts.put(equipeVO.getId(), "shirts/shirt_" + equipeVO.getId() + ".png");
						} else {
							mapShirts.put(equipeVO.getId(), DEFAULT_SHIRT);
						}
						
					}
				}
				
			}
		}
	}
	
	private void initMapEquipe() throws ApplicationAbdException {
		if (listChampionnat != null && !listChampionnat.isEmpty()) {
			mapEquipe = new HashMap<Long, List<EquipeVO>>();
			for (ChampionnatVO ch : listChampionnat) {
				List<EquipeVO> list = paramGenerauxManager.findEquipeByChampionnat(ch.getId());
				
				mapEquipe.put(ch.getId(), list);
			}
		}
	}
	
	private void initMapJournee() {
		if (listChampionnat != null && !listChampionnat.isEmpty()) {
			mapJournee = new HashMap<Long, List<Integer>>();
			for (ChampionnatVO ch : listChampionnat) {
				if (ch.getActualJournee() > 0) {
					List<Integer> list = new ArrayList<Integer>();
					for (int i = 1; i <= ch.getActualJournee(); i++) {
						list.add(i);
					}
					
					mapJournee.put(ch.getId(), list);
				}
				
			}
		}
	}
	
	public void updateJournee() throws ApplicationAbdException {
		listChampionnat = paramGenerauxManager.getAll();
		initMapJournee();
	}
	
	private void initReglesJoueurs() {
		listReglesJoueurs = new StatJoueurVO[4];
		StatJoueurVO gardien = new StatJoueurVO();
		gardien.setNbrMinuteJoue(2);
		gardien.setNbrBut(8);
		gardien.setNbrAssist(3);
		gardien.setNbrShotInG(2);
		gardien.setNbrPenalty(5);
		gardien.setNbrSavePenalty(4);
		gardien.setNbrSave(1);
		gardien.setNbrPenaltyRate(-2);
		gardien.setNbrButRecu(-1);
		gardien.setCleanSheetS(3);
		gardien.setCartonJ(-1);
		gardien.setCartonR(-2);
		gardien.setPositionJoueurLabel(listPositionJoueur[0].getLabel());
		
		listReglesJoueurs[0] = gardien;
		
		StatJoueurVO def = new StatJoueurVO();
		def.setNbrMinuteJoue(2);
		def.setNbrBut(6);
		def.setNbrAssist(3);
		def.setNbrShotInG(2);
		def.setNbrPenalty(3);
		def.setNbrSavePenalty(4);
		def.setNbrSave(1);
		def.setNbrPenaltyRate(-2);
		def.setNbrButRecu(-1);
		def.setCleanSheetS(2);
		def.setCartonJ(-1);
		def.setCartonR(-2);
		def.setPositionJoueurLabel(listPositionJoueur[1].getLabel());
		
		listReglesJoueurs[1] = def;
		
		
		StatJoueurVO mil = new StatJoueurVO();
		mil.setNbrMinuteJoue(2);
		mil.setNbrBut(5);
		mil.setNbrAssist(2);
		mil.setNbrShotInG(1);
		mil.setNbrPenalty(3);
		mil.setNbrSavePenalty(4);
		mil.setNbrSave(1);
		mil.setNbrPenaltyRate(-2);
		mil.setNbrButRecu(0);
		mil.setCleanSheetS(1);
		mil.setCartonJ(-1);
		mil.setCartonR(-2);
		mil.setPositionJoueurLabel(listPositionJoueur[2].getLabel());
		
		listReglesJoueurs[2] = mil;
		
		StatJoueurVO att = new StatJoueurVO();
		att.setNbrMinuteJoue(2);
		att.setNbrBut(4);
		att.setNbrAssist(2);
		att.setNbrShotInG(1);
		att.setNbrPenalty(3);
		att.setNbrSavePenalty(4);
		att.setNbrSave(1);
		att.setNbrPenaltyRate(-2);
		att.setNbrButRecu(0);
		att.setCleanSheetS(0);
		att.setCartonJ(-1);
		att.setCartonR(-2);
		att.setPositionJoueurLabel(listPositionJoueur[3].getLabel());
		
		listReglesJoueurs[3] = att;
		

	}
	
	private void initFormations() {
		formations = new FormationEnum[3];
		int i = 0;
		for (FormationEnum f : FormationEnum.values()) {
			formations[i++] = f;
		}
	}
	

	public List<ChampionnatVO> getListChampionnat() {
		return listChampionnat;
	}

	public int getTotalUsers() {
		return totalUsers;
	}

	public PositionJoueurMO[] getListPositionJoueur() {
		return listPositionJoueur;
	}

	public Map<Long, List<EquipeVO>> getMapEquipe() {
		return mapEquipe;
	}

	public Map<Long, List<Integer>> getMapJournee() {
		return mapJournee;
	}

	public FormationEnum[] getFormations() {
		return formations;
	}

	public StatJoueurVO[] getListReglesJoueurs() {
		return listReglesJoueurs;
	}

	public Map<Long, String> getMapShirts() {
		return mapShirts;
	}

	public List<UserMO> getListUsersEng() {
		return listUsersEng;
	}

	public List<UserMO> getListUsersEsp() {
		return listUsersEsp;
	}


	public Map<Long, List<UserMO>> getListUsers() {
		return listUsers;
	}


	public void setListUsers(Map<Long, List<UserMO>> listUsers) {
		this.listUsers = listUsers;
	}


	public Map<Long, List<JoueurVO>> getBestJoueurs() {
		return bestJoueurs;
	}


	public List<JoueurVO> getListJoueur() {
		return listJoueur;
	}

}
