package com.abd.mfc.manager;

import java.io.Serializable;
import java.util.List;

import com.abd.mfc.dao.BaseDao;
import com.abd.mfc.entities.BaseEntity;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.vo.VO;

public interface BaseManager<ENTITY extends BaseEntity, TYPE extends VO<ENTITY>, ID, DAO extends BaseDao> extends Serializable {
	void add(TYPE entity) throws ApplicationAbdException;
	void update(TYPE entity) throws ApplicationAbdException;
	TYPE get(ID id) throws ApplicationAbdException;
	List<TYPE> getAll() throws ApplicationAbdException;
	Class<ENTITY> getClazz();
}
