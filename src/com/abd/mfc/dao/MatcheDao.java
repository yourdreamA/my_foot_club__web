package com.abd.mfc.dao;

import java.util.List;

import com.abd.mfc.entities.Match;

public interface MatcheDao extends BaseDao<Match, Long> {

	List<Match> findMatchsByDay(long idChampionnat, int numJ);

}
