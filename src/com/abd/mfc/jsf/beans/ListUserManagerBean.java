package com.abd.mfc.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.EquipeUserManager;
import com.abd.mfc.vo.UserVO;

@Controller
@Scope("request")
public class ListUserManagerBean {

	private List<UserVO> listUser;
	
	public List<UserVO> getListUser() {
		return listUser;
	}

	@Autowired
    private EquipeUserManager equipeUserManager;

	@PostConstruct
	public void init() throws ApplicationAbdException {
		
		
		listUser = equipeUserManager.rechercherStatsUsers();
	}
}
