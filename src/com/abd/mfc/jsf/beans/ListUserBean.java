package com.abd.mfc.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.entities.EquipeUser;
import com.abd.mfc.entities.EquipeUserFilter;
import com.abd.mfc.entities.PointUser;
import com.abd.mfc.entities.User;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.jsf.model.UserMO;
import com.abd.mfc.manager.EquipeUserManager;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.EquipeUserFilterVO;
import com.abd.mfc.vo.EquipeUserVO;
import com.abd.mfc.vo.JourneeVO;
import com.abd.mfc.vo.PaginationEnum;
import com.abd.mfc.vo.PaginationVO;
import com.abd.mfc.vo.PointUserVO;
import com.abd.mfc.vo.ResultPaginateVO;
import com.abd.mfc.vo.UserVO;

@Controller
@Scope("view")
public class ListUserBean extends BaseBean {

	private List<UserMO> listUser;
	private EquipeUserVO filter;
	
	@Autowired
    private EquipeUserManager equipeUserManager;
	
	@Autowired
    private SessionBean sessionBean;
	
//	@Autowired
//    private PointUserManager pointUserManager;

	@PostConstruct
	public void init() {
		filter = new EquipeUserVO();
		//filter.setIdSaison(Long.valueOf(1));
		JourneeVO j = new JourneeVO();
		j.setNumero(sessionBean.getSelectedChampionnat().getActualJournee());
		j.setIdChampionnat(sessionBean.getSelectedChampionnat().getId());
		//filter.setJournee(j);
		
		filter.setSortBy("pointUserS");
		filter.setId_championnat(sessionBean.getSelectedChampionnat().getId());
		
		PaginationVO pagination = new PaginationVO(30);
		filter.setPagination(pagination);
		
		doSearch();
	}
	
	public String doSearch(String action) {
		if (PaginationEnum.FIRST.getCode().equals(action)) {
			filter.getPagination().setNumeroPage(0);
		} else if (PaginationEnum.PREVIOUS.getCode().equals(action)) {
			filter.getPagination().setNumeroPage(filter.getPagination().getNumeroPage() - 1);
		} else if (PaginationEnum.NEXT.getCode().equals(action)) {
			filter.getPagination().setNumeroPage(filter.getPagination().getNumeroPage() + 1);
		} else if (PaginationEnum.LAST.getCode().equals(action)) {
			filter.getPagination().setNumeroPage(filter.getPagination().getTotalPage() - 1);
		}
		
		return doSearch();
	}
	
	public String doSearch(int page) {
		filter.getPagination().setNumeroPage(page);
		
		return doSearch();
	}
	
	public String doSearchByUserName() {
		filter.getPagination().setNumeroPage(0);
		
		return doSearch();
	}
	
	public String doSearch() {
		
		ResultPaginateVO<EquipeUserFilter, EquipeUserFilterVO> resultat = null;
		
		try {
			
			resultat = equipeUserManager.search(filter);
		} catch (ApplicationAbdException e) {
			e.printStackTrace();
		}
		if (resultat != null && resultat.getListVO() != null && !resultat.getListVO().isEmpty()) {
			listUser = new ArrayList<UserMO>();
			for (EquipeUserFilterVO userVO : resultat.getListVO()) {
				listUser.add(new UserMO(userVO));
			}
		} else {
			listUser = null;
		}
		
		filter.setPagination(resultat.getP());
		
		return null;
	}
	
	public void sortByListener(AjaxBehaviorEvent event) {
		filter.getPagination().setNumeroPage(0);
        doSearch();
    }

	public List<UserMO> getListUser() {
		return listUser;
	}

	public EquipeUserVO getFilter() {
		return filter;
	}

	public void setFilter(EquipeUserVO filter) {
		this.filter = filter;
	}
	
}
