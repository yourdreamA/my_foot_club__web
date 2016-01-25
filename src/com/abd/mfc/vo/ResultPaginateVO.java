package com.abd.mfc.vo;

import java.util.List;

import com.abd.mfc.entities.BaseEntity;

public class ResultPaginateVO<ENTITY extends BaseEntity, TYPE extends VO<ENTITY>> {

	private List<ENTITY> list;
	private List<TYPE> listVO;
	private PaginationVO p;
	
	public ResultPaginateVO() {}
	
	
	
	public ResultPaginateVO(PaginationVO p) {
		super();
		this.p = p;
	}



	public List<ENTITY> getList() {
		return list;
	}
	public void setList(List<ENTITY> list) {
		this.list = list;
	}
	public PaginationVO getP() {
		return p;
	}
	public void setP(PaginationVO p) {
		this.p = p;
	}



	public List<TYPE> getListVO() {
		return listVO;
	}



	public void setListVO(List<TYPE> listVO) {
		this.listVO = listVO;
	}
	
	
	
	
}
