package com.abd.mfc.dao;

import java.util.List;

import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.Joueur;
import com.abd.mfc.entities.StatJoueur;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.ResultPaginateVO;

public interface JoueurDao extends BaseDao<Joueur, Long> {
	ResultPaginateVO<Joueur, JoueurVO> search(JoueurVO filter);

	List<StatJoueur> findStatJoueur(long idChampionnat, long idJoueur);
	
	EquipeUser saveFormationJoueur(EquipeUser equipeUser);

	List<StatJoueur> findStatEquipe(long idChampionnat, List<Long> idsJoueur,
			int journee);

	List<StatJoueur> findStatJournee(long idChampionnat, int journee);

	void updateStat(StatJoueur entity);

	void updateTotalPointJoueur();

	void updatePointJourneeJoueur(long idChampionnat, int journee);

	public List<Joueur> findJoueurChampionnat(long idChampionnat);

	void insertStatJ(StatJoueur entity);

	Long findIdJoueurByNumero(long idEquipe, int numero);

}
