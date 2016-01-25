package com.abd.mfc.dao;

import java.util.List;

import com.abd.mfc.entities.Championnat;
import com.abd.mfc.entities.Equipe;

public interface ParamGenerauxDao extends BaseDao<Championnat, Long> {

	List<Equipe> findEquipeByChampionnat(long idChampionnat);
	void addComment(String param);
}
