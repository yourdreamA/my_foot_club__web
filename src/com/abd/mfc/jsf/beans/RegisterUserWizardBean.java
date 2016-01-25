package com.abd.mfc.jsf.beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.jsf.model.LoginMO;
import com.abd.mfc.jsf.model.UserMO;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.MessageVO;
import com.abd.mfc.vo.UserVO;

@Controller
@Scope("session")
public class RegisterUserWizardBean extends BaseBean {

	public UserMO user;

	@Autowired
	private NavigationBean navigationBean;
	
	@Autowired
    private UserManager userManager;

	@PostConstruct
	public void init() {
		user = new UserMO();
		user.setLogin(new LoginMO());
	}

	public String doSave() {
		try {
			userManager.add(user.convertToVo() );
		} catch (ApplicationAbdException e) {
			e.printStackTrace();
			for (MessageVO msg : e.getMessages()) {
				FacesMessage message = new FacesMessage(msg.getContent(), "ERROR MSG");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
		        FacesContext.getCurrentInstance().addMessage("login_form", message);
			}
			
		}
		
		return "/loginSuccess.xhtml?faces-redirect=true";
	}

	public UserMO getUser() {
		return user;
	}

	public void setUser(UserMO user) {
		this.user = user;
	}
}
