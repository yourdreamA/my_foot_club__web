package com.abd.mfc.dao;

import java.util.List;

import com.abd.mfc.entities.BaseEntity;
import com.abd.mfc.entities.Journee;

public interface BaseDao<TYPE extends BaseEntity, ID> {
	void add(TYPE entity);
	void update(TYPE entity);
	TYPE get(Class<TYPE> clazz, ID id);
	List<TYPE> getAll(Class<TYPE> clazz);
	Journee findJourneeByNumero(long idChampionnat, int num);
	
	BaseEntity getObject(Class clazz, ID id);
	void updateObject(BaseEntity entity);
	BaseEntity addObject(BaseEntity entity);
}
