package com.abd.mfc.manager;

import java.util.List;

import com.abd.mfc.dao.JoueurDao;
import com.abd.mfc.entities.Joueur;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.JoueurVO;
import com.abd.mfc.vo.MessageVO;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.stat.StatJoueurParse;

public interface JoueurManager extends BaseManager<Joueur, JoueurVO, Long, JoueurDao> {
	ResultPaginateVO<Joueur, JoueurVO> search(JoueurVO filter) throws ApplicationAbdException;
	JoueurVO searchStatJoueur(long idChampionnat, long idJoueur) throws ApplicationAbdException;
	EquipeUserVO saveFormationJoueur(EquipeUserVO equipeUser) throws ApplicationAbdException;
	
	List<MessageVO> validateFormationJoueur(EquipeUserVO equipeUser)
			throws ApplicationAbdException;
	
	List<JoueurVO> findStatEquipe(long idChampionnat, EquipeUserVO equipeUser, int journee) throws ApplicationAbdException;
	
	void traiterTotalPointJoueur(long idChampionnat, int journee);
	
	void updateTotalPointJoueur();
	void traiterCoutJoueur(long idChampionnat);
	List<String> insertStatJ(long idChampionnat, int numJournne, long idEquipe,
			List<StatJoueurParse> listJ) throws ApplicationAbdException;
	
	List<JoueurVO> searchBestJoueur(long idChampionnat) throws ApplicationAbdException;
	void initEquipeUser() throws ApplicationAbdException;
}
