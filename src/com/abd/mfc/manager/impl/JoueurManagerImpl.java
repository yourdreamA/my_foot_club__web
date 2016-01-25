package com.abd.mfc.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abd.mfc.dao.JoueurDao;
import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.Joueur;
import com.abd.mfc.entities.Journee;
import com.abd.mfc.entities.PointUser;
import com.abd.mfc.entities.StatJoueur;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.jsf.model.FormationEnum;
import com.abd.mfc.manager.EquipeUserManager;
import com.abd.mfc.manager.JoueurManager;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.MessageVO;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.mfc.vo.StatJoueurVO;
import com.abd.mfc.vo.TypeMessage;
import com.abd.mfc.vo.UserVO;
import com.abd.stat.StatJoueurParse;

@Service("joueurManager")
public class JoueurManagerImpl extends BaseManagerImpl<Joueur, JoueurVO, Long, JoueurDao>
		implements JoueurManager {
	

	private final static long ID_ESP = 1;
	private final static long ID_ENG = 2;
	
	@Autowired
	private JoueurDao dao;
	
	@Autowired
	private EquipeUserManager equipeUserManager;
	
	public List<JoueurVO> findStatEquipe(long idChampionnat, EquipeUserVO equipeUser, int journee) throws ApplicationAbdException {
		List<JoueurVO> result = getListJoueur(equipeUser);
		
		
		List<Long> idsJoueur = getListIdJoueur(equipeUser);
		
		List<StatJoueur> list = dao.findStatEquipe(idChampionnat, idsJoueur, journee);
		
		if (list != null && !list.isEmpty()) {
			Map<Long, JoueurVO> mapJ = new HashMap<Long, JoueurVO>();
			for (JoueurVO joueurVO : result) {
				mapJ.put(joueurVO.getId(), joueurVO);
			}
			
			for (StatJoueur s : list) {
				mapJ.get(s.getIdJoueur()).setStatJ(new StatJoueurVO(s));
			}
		}
		
		return result;
	}
	
	private List<Long> getListIdJoueur(EquipeUserVO equipeUser) {
		List<Long> idsJoueur = new ArrayList<Long>();
		addIdJoueur(equipeUser.getGardien(), idsJoueur);
		
		addIdJoueur(equipeUser.getDefenseur1(), idsJoueur);
		addIdJoueur(equipeUser.getDefenseur2(), idsJoueur);
		addIdJoueur(equipeUser.getDefenseur3(), idsJoueur);
		addIdJoueur(equipeUser.getDefenseur4(), idsJoueur);
		
		addIdJoueur(equipeUser.getMilieu1(), idsJoueur);
		addIdJoueur(equipeUser.getMilieu2(), idsJoueur);
		addIdJoueur(equipeUser.getMilieu3(), idsJoueur);
		addIdJoueur(equipeUser.getMilieu4(), idsJoueur);
		
		addIdJoueur(equipeUser.getAtt1(), idsJoueur);
		addIdJoueur(equipeUser.getAtt2(), idsJoueur);
		addIdJoueur(equipeUser.getAtt3(), idsJoueur);
		
		
		return idsJoueur;
	}
	
	private void addIdJoueur(JoueurVO j, List<Long> idsJoueur) {
		long id = -2;
		if (j != null) {
			id = j.getId();
			if (id > -2) {
				idsJoueur.add(id);
			}
		}
		
	}
	
	private List<JoueurVO> getListJoueur(EquipeUserVO equipeUser) {
		List<JoueurVO> listJoueur = new ArrayList<JoueurVO>();
		addJoueur(equipeUser.getGardien(), listJoueur);
		
		addJoueur(equipeUser.getDefenseur1(), listJoueur);
		addJoueur(equipeUser.getDefenseur2(), listJoueur);
		addJoueur(equipeUser.getDefenseur3(), listJoueur);
		addJoueur(equipeUser.getDefenseur4(), listJoueur);
		
		addJoueur(equipeUser.getMilieu1(), listJoueur);
		addJoueur(equipeUser.getMilieu2(), listJoueur);
		addJoueur(equipeUser.getMilieu3(), listJoueur);
		addJoueur(equipeUser.getMilieu4(), listJoueur);
		
		addJoueur(equipeUser.getAtt1(), listJoueur);
		addJoueur(equipeUser.getAtt2(), listJoueur);
		addJoueur(equipeUser.getAtt3(), listJoueur);
		
		
		return listJoueur;
	}
	
	private void addJoueur(JoueurVO j, List<JoueurVO> listJoueur) {
		if (j != null) {
			listJoueur.add(j);
		}
	}
	
	public JoueurVO searchStatJoueur(long idChampionnat, long idJoueur) {
		Joueur e = dao.get(Joueur.class, idJoueur);
		if (e == null) {
			return null;
		}
		
		JoueurVO res = new JoueurVO(e);
		
		List<StatJoueurVO> result = null;
		List<StatJoueur> list = dao.findStatJoueur(idChampionnat, idJoueur);
		
		if (list != null && !list.isEmpty()) {
			result = new ArrayList<StatJoueurVO>();
			for (StatJoueur s : list) {
				result.add(new StatJoueurVO(s));
			}
			
			res.setStats(result);
		}
		
		return res;
	}
	
	public List<JoueurVO> searchBestJoueur(long idChampionnat) throws ApplicationAbdException {
		List<JoueurVO> result = new ArrayList<JoueurVO>();
		
		//gardien
		JoueurVO filter = new JoueurVO();
		PaginationVO pagination = new PaginationVO(1);
		filter.setIdChampionnat(idChampionnat);
		filter.setSortBy("pointJournee");
		filter.setPosition(1);
		filter.setPagination(pagination);
		filter.setWithEquipe(true);
		
		ResultPaginateVO<Joueur, JoueurVO> res = dao.search(filter);
		Joueur gardien = res.getList().get(0);
		result.add(new JoueurVO(gardien, true));
		
		//def
		filter.setPosition(2);
		filter.getPagination().setNombreLigne(3);
		ResultPaginateVO<Joueur, JoueurVO> res2 = dao.search(filter);
		for (int i = 0; i < 3; i++) {
			Joueur j = res2.getList().get(i);
			result.add(new JoueurVO(j, true));
		}
		
		//mil
		filter.setPosition(3);
		filter.getPagination().setNombreLigne(4);
		ResultPaginateVO<Joueur, JoueurVO> res3 = dao.search(filter);
		for (int i = 0; i < 4; i++) {
			Joueur j = res3.getList().get(i);
			result.add(new JoueurVO(j, true));
		}
		
		//att
		filter.setPosition(4);
		filter.getPagination().setNombreLigne(3);
		ResultPaginateVO<Joueur, JoueurVO> res4 = dao.search(filter);
		for (int i = 0; i < 3; i++) {
			Joueur j = res4.getList().get(i);
			result.add(new JoueurVO(j, true));
		}
		
		return result;
	}
	
	public ResultPaginateVO<Joueur, JoueurVO> search(JoueurVO filter) throws ApplicationAbdException {
		List<JoueurVO> result = null;
		ResultPaginateVO<Joueur, JoueurVO> res = dao.search(filter);
		
		if (res != null && res.getList() != null && !res.getList().isEmpty()) {
			result = new ArrayList<JoueurVO>();
			for (Joueur j : res.getList()) {
				result.add(new JoueurVO(j, filter.isWithEquipe()));
			}
			
			res.setList(null);
			res.setListVO(result);
			//MAJ nbr pages
			if (res.getP() != null && res.getP().getNombreLigne() > 0) {
				int nombrePageTotal = (int) (res.getP().getTotalResultat() / res.getP().getNombreLigne());
				
				if (res.getP().getTotalResultat() % res.getP().getNombreLigne() > 0) {
					nombrePageTotal++;
				}
				res.getP().setTotalPage(nombrePageTotal);
			}
		}
		
		return res;
	}
	
	@Override
	public Class<Joueur> getClazz() {
		return Joueur.class;
	}
	@Override
	public JoueurVO convertEntityToVO(Joueur e) throws ApplicationAbdException {
		return new JoueurVO(e);
	}
	@Override
	public JoueurDao getDao() {
		return dao;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public EquipeUserVO saveFormationJoueur(EquipeUserVO equipeUser)
			throws ApplicationAbdException {
		
		List<MessageVO> list = validateFormationJoueur(equipeUser);
		
		if (!list.isEmpty()) {
			equipeUser.setErrors(list);
		} else {
			equipeUser.setLastUpdate(new Date());
			EquipeUser eq = dao.saveFormationJoueur(equipeUser.convertToEntity());
			//EquipeUserVO resVO = new EquipeUserVO(eq);
			//TODO equipeUser = ;
		}
		 
		 return equipeUser;
	}
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void initEquipeUser() throws ApplicationAbdException {
		List<UserVO> listUsers = equipeUserManager.rechercherStatsUsers();
		
		
		for (UserVO userVO : listUsers) {
			if ("0".equals(userVO.getEqUserEsp())) {
				dao.saveFormationJoueur(getNewEquipeUser(ID_ESP, userVO.getId(), userVO.getUsername()));
			}
			
			if ("0".equals(userVO.getEqUserEng())) {
				dao.saveFormationJoueur(getNewEquipeUser(ID_ENG, userVO.getId(), userVO.getUsername()));
			}
		}
		
		
	}
	
	private EquipeUser getNewEquipeUser(long idChampionnat, long idUser, String equipeName) {
		EquipeUser equipeUser = new EquipeUser();
		equipeUser.setFormation(FormationEnum.Q_Q_D.getId());
		equipeUser.setId_championnat(idChampionnat);
		equipeUser.setId_user(idUser);
		equipeUser.setNom(equipeName);
		equipeUser.setSoldeUser(1000);

		PointUser p = new PointUser();
		p.setNbrPt(0);
		equipeUser.setPointUserS(p);
		
		return equipeUser;
	}
	
	public List<MessageVO> validateFormationJoueur(EquipeUserVO equipeUser)
			throws ApplicationAbdException {
		List<MessageVO> list = new ArrayList<MessageVO>();
		
		//controle tous joueurs saisis
		if (equipeUser == null || equipeUser.getFormation() == null) {
			list.add(new MessageVO(TypeMessage.ERROR, "error_equipe"));
		} else {
			if (equipeUser.getGardien() == null) {
				list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_gardien"));
			}
			
			if (equipeUser.getDefenseur1() == null || equipeUser.getDefenseur2() == null
					 || equipeUser.getDefenseur3() == null) {
				list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_defenseur"));
			}
			if (equipeUser.getMilieu1() == null || equipeUser.getMilieu2() == null
					 || equipeUser.getMilieu3() == null) {
				list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_milieu"));
			}
			
			if (equipeUser.getAtt1() == null || equipeUser.getAtt2() == null) {
				list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_attaquant"));
			}
			if (list.isEmpty()) {
				switch (equipeUser.getFormation()) {
				case Q_Q_D:
					if (equipeUser.getDefenseur4() == null) {
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_defenseur"));
					}
					if (equipeUser.getMilieu4() == null) {
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_milieu"));
					}
					if (equipeUser.getAtt3() != null) {//invalide
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_attaquant"));
					}
					break;
				case Q_T_T:
					if (equipeUser.getDefenseur4() == null) {
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_defenseur"));
					}
					if (equipeUser.getMilieu4() != null) {//invalide
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_milieu"));
					}
					if (equipeUser.getAtt3() == null) {
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_attaquant"));
					}
					break;
				
				case T_Q_T:
					if (equipeUser.getDefenseur4() != null) {//invalide
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_defenseur"));
					}
					if (equipeUser.getMilieu4() == null) {
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_milieu"));
					}
					if (equipeUser.getAtt3() == null) {
						list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_attaquant"));
					}
					break;

				default:
					break;
				}
				
				//TODO duplication de joueurs
			}
			
		}
		
		if (list.isEmpty()) {
			//controle solde
			/*int soldeR = calculerSoldeRestant(equipeUser);
			equipeUser.setSoldeUser(soldeR);*/
			//solde(importe a partir de labse) = soldeR + somme
			int sommeJ = 0;
			Map<Long, Integer> mapEquipe = new HashMap<Long, Integer>();
			
			sommeJ = putJ(mapEquipe, equipeUser.getGardien(), sommeJ);
			
			sommeJ = putJ(mapEquipe, equipeUser.getDefenseur1(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getDefenseur2(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getDefenseur3(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getDefenseur4(), sommeJ);
			
			sommeJ = putJ(mapEquipe, equipeUser.getMilieu1(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getMilieu2(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getMilieu3(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getMilieu4(), sommeJ);
			
			sommeJ = putJ(mapEquipe, equipeUser.getAtt1(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getAtt2(), sommeJ);
			sommeJ = putJ(mapEquipe, equipeUser.getAtt3(), sommeJ);
			
			//verification solde par somme joueurs
			if (sommeJ > equipeUser.getSoldeUser()) {
				list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_solde"));
			}
			//verification 3 joueurs par equipe
			for (Long key : mapEquipe.keySet()) {
				if (mapEquipe.get(key) > 3) {
					list.add(new MessageVO(TypeMessage.ERROR, "error_equipe_nbr_max"));
				}
			}
		}
		
		return list;
	}
	
	private int putJ(Map<Long, Integer> mapEquipe, JoueurVO j, int somme) {
		if (j != null) {
			int i = 0;
			if (mapEquipe.containsKey(j.getIdEquipe())) {
				i = mapEquipe.get(j.getIdEquipe());
				i++;
				mapEquipe.put(j.getIdEquipe(), i);
			} else {
				mapEquipe.put(j.getIdEquipe(), 1);
			}
			
			return somme + j.getCout();
		}
		
		return somme;
	}
	
	private int calculerSommeJoueur(EquipeUserVO equipeUser) {
		int somme = 0;
		
		somme += getCoutJoueur(equipeUser.getGardien());
		
		somme += getCoutJoueur(equipeUser.getDefenseur1());
		somme += getCoutJoueur(equipeUser.getDefenseur2());
		somme += getCoutJoueur(equipeUser.getDefenseur3());
		somme += getCoutJoueur(equipeUser.getDefenseur4());
		
		somme += getCoutJoueur(equipeUser.getMilieu1());
		somme += getCoutJoueur(equipeUser.getMilieu2());
		somme += getCoutJoueur(equipeUser.getMilieu3());
		somme += getCoutJoueur(equipeUser.getMilieu4());
		
		somme += getCoutJoueur(equipeUser.getAtt1());
		somme += getCoutJoueur(equipeUser.getAtt2());
		somme += getCoutJoueur(equipeUser.getAtt3());
		
		return somme;
	}
	
	private int getCoutJoueur(JoueurVO j) {
		int cout = 0;
		if (j != null) {
			cout = j.getCout();
		}
		
		return cout;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void traiterTotalPointJoueur(long idChampionnat, int journee) {
		//1 select list joueur
		List<StatJoueur> list = dao.findStatJournee(idChampionnat, journee);
		if (list != null && !list.isEmpty()) {
			for (StatJoueur stat : list) {
				int tot = calculerTotalPointJoueur(stat);
				stat.setTotalP(tot);
				dao.updateStat(stat);
				Joueur j = dao.get(getClazz(), stat.getIdJoueur());
				j.setPointJournee(tot);
				dao.update(j);
			}
			
			
		}
		
		//2 calcul total point
		//3 maj champs totalP
		
		
		//4 maj joueur
		//dao.updatePointJourneeJoueur(idChampionnat, journee);
		
	}
	
	public int calculerTotalPointJoueur(StatJoueur stat) {
		int tot = 0;
		if (stat != null) {
			if (stat.getParticipe() > 0) {
				tot++;
				if (stat.getNbrMinuteJoue() >= 60) {
					tot += 2;
					
					int cleanSheet = 0;
					if (stat.getNbrButRecu() == 0) {
						switch (stat.getPositionJoueur()) {
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
						tot += cleanSheet;
					}
					
					
				}
				
				if (stat.getNbrBut() > 0) {
					int pt = 0;
					switch (stat.getPositionJoueur()) {
					case 1:
						pt = stat.getNbrBut() * 8;
						break;
					case 2:
						pt = stat.getNbrBut() * 6;
						break;
					case 3:
						pt = stat.getNbrBut() * 5;
						break;
					case 4:
						pt = stat.getNbrBut()* 4;
						break;
						
					default:
						break;
					}
					tot += pt;
				}
				
				if (stat.getNbrAssist() > 0) {
					int pt = 0;
					switch (stat.getPositionJoueur()) {
					case 1:
						pt = stat.getNbrAssist() * 3;
						break;
					case 2:
						pt = stat.getNbrAssist() * 3;
						break;
					case 3:
						pt = stat.getNbrAssist() * 2;
						break;
					case 4:
						pt = stat.getNbrAssist() * 2;
						break;
						
					default:
						break;
					}
					tot += pt;
				}
				
				if (stat.getNbrShotInG() > 0) {
					int pt = 0;
					switch (stat.getPositionJoueur()) {
					case 1:
						pt = stat.getNbrShotInG() * 2;
						break;
					case 2:
						pt = stat.getNbrShotInG() * 2;
						break;
					case 3:
						pt = stat.getNbrShotInG();
						break;
					case 4:
						pt = stat.getNbrShotInG();
						break;
						
					default:
						break;
					}
					tot += pt;
				}
				
				if (stat.getNbrPenalty() > 0) {
					int pt = 0;
					switch (stat.getPositionJoueur()) {
					case 1:
						pt = stat.getNbrPenalty() * 5;
						break;
					case 2:
						pt = stat.getNbrPenalty() * 3;
						break;
					case 3:
						pt = stat.getNbrPenalty() * 3;
						break;
					case 4:
						pt = stat.getNbrPenalty() * 3;
						break;
						
					default:
						break;
					}
					tot += pt;
				}
				
				if (stat.getNbrSavePenalty() > 0) {
					tot += (stat.getNbrSavePenalty()*4);
				}
				
				if (stat.getNbrSave() > 0) {
					tot += stat.getNbrSave();
				}
				
				if (stat.getNbrPenaltyRate() > 0) {
					tot -= (stat.getNbrPenaltyRate() * 2);
				}
				
				if (stat.getNbrButRecu() > 0) {
					int pt = 0;
					switch (stat.getPositionJoueur()) {
					case 1:
						pt = stat.getNbrButRecu();
						break;
					case 2:
						pt = stat.getNbrButRecu();
						break;
					
					default:
						break;
					}
					tot -= pt;
				}
				
				if (stat.getCartonJ() > 0) {
					tot--;
					if (stat.getPositionJoueur() == 1) {
						tot--;
					}
				}
				
				if (stat.getCartonR() > 0) {
					tot -= 2;
				}
			}
			
		}
		
		return tot;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateTotalPointJoueur() {
		dao.updateTotalPointJoueur();
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void traiterCoutJoueur(long idChampionnat) {
		List<Joueur> list = dao.findJoueurChampionnat(idChampionnat);

		if (list != null && !list.isEmpty()) {
			for (Joueur joueur : list) {
				int cout = joueur.getCout();
				int pointJournne = joueur.getPointJournee() != null ? joueur.getPointJournee() : 0;
				int pointTot = joueur.getTotalPoint() != null ? joueur.getTotalPoint() : 0;

				/***********   cout > 150    *************************/
				if (cout > 150) {
					if (pointJournne > 0) {
						cout += pointJournne;
						cout -= 8;

					} else {
						cout -= 10;
					}
					
				}
				
				/***********   cout > 100    *************************/
				else if (cout > 100) {
					if (pointJournne > 0) {
						cout += pointJournne;
						cout -= 3;

					} else {
						cout -= 5;
					}
					
				}
				/***********   cout >= 90    *************************/
				else if (cout >= 90) {
					if (pointJournne > 0) {
						cout += pointJournne;
					}
					
					if (pointJournne == 0) {
						cout -= 3;
					}
				}

				/*********** 45 <  cout < 90    *************************/
				else {
					cout += pointJournne;
					
					if (pointJournne == 0) {
						cout -= 1;
					}

				}
				
				//a supprimer au debut de saison
				if (pointJournne == 0 && pointTot == 0) {
					cout -= 10;
				}
				if (cout < 45) {
					cout = 45;
				}
				
				if (cout > 200) {
					cout = 200;
				}
				int diff = cout - joueur.getCout();
				joueur.setCout(cout);
				joueur.setDiffCout(diff);
				dao.update(joueur);
			}
		}
	}
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<String> insertStatJ(long idChampionnat, int numJournne, long idEquipe, List<StatJoueurParse> listJ)  throws ApplicationAbdException {
		
		List<String> messagesErreurs = new ArrayList<String>();
		messagesErreurs.add("Erreurs equipe " + idEquipe + " : ");
		if (listJ != null && !listJ.isEmpty()) {
			Journee jj = dao.findJourneeByNumero(idChampionnat, numJournne);
			for (StatJoueurParse statJoueurParse : listJ) {
				Long idJoueur = dao.findIdJoueurByNumero(idEquipe, Integer.valueOf(statJoueurParse.getNumJ()));
				if (idJoueur != null) {
					StatJoueur e = new StatJoueurVO(statJoueurParse).convertToEntity();
					e.setIdJoueur(idJoueur);
					e.setJournee(jj);
					dao.insertStatJ(e);
				} else {
					messagesErreurs.add("Erreurs equipe " + idEquipe + " : " + "num j : " + statJoueurParse.getNumJ() + ", id:" + idJoueur);
				}
				
				
			}
		}
		
		return messagesErreurs;
	}
	
	
	
	
	

}
