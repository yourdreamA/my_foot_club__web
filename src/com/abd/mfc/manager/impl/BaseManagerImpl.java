package com.abd.mfc.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abd.mfc.dao.BaseDao;
import com.abd.mfc.entities.BaseEntity;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.BaseManager;
import com.abd.mfc.vo.VO;

@Transactional(readOnly = true)
public abstract class BaseManagerImpl<ENTITY extends BaseEntity, TYPE extends VO<ENTITY>, ID, DAO extends BaseDao<ENTITY, ID>> 
			implements BaseManager<ENTITY, TYPE, ID, DAO> {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void add(TYPE entity) throws ApplicationAbdException {
		getDao().add(entity.convertToEntity());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void update(TYPE entity) throws ApplicationAbdException {
		getDao().update(entity.convertToEntity());
		
	}

	@Override
	public TYPE get(ID id) throws ApplicationAbdException {
		return convertEntityToVO(getDao().get(getClazz(), id));
	}

	@Override
	public List<TYPE> getAll() throws ApplicationAbdException {
		List<TYPE> res = null;
		List<ENTITY> list = getDao().getAll(getClazz());
		if(list != null && !list.isEmpty()) {
			res = new ArrayList<TYPE>();
			for (ENTITY entity : list) {
				res.add(convertEntityToVO(entity));
			}
		}
		
		return res;
	}
	
	public abstract TYPE convertEntityToVO(ENTITY e)  throws ApplicationAbdException;
	public abstract DAO getDao();

}
