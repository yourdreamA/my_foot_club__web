package com.abd.mfc.jsf.beans;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.jsf.model.LoginMO;
import com.abd.mfc.jsf.model.UserMO;
import com.abd.mfc.manager.UserManager;
import com.abd.mfc.vo.UserVO;

/**
 * http://www.itcuties.com/j2ee/jsf-2-login-filter-example/
 * FOR LOGIN FILTER
 *
 */


//@ManagedBean
//@SessionScoped
@Controller
@Scope("session")
public class LoginBean extends BaseBean {

	public LoginMO login;
	public UserMO connectedUser;
	 private boolean loggedIn;
	 
	 private String nextPage;
	 
	 private String responseName;
	 private String responseId;
	 
	 @Autowired
	 private LocaleBean localeBean;
	
    @Autowired
    private UserManager userManager;
    
    
    @PostConstruct
    public void init() {
    	login = new LoginMO();
    	
    }
    
    public String doLoginFb() {
    	
    	String responseMail = responseId + "@facebook.com";
    	UserVO filter = new UserVO();
    	filter.setMail(responseMail);
    	filter.setUsername(responseName);
		//test exist
    	if (userManager.existUser(filter)) {
    		if (loggedIn && connectedUser != null 
    				&& connectedUser.getLogin().getUsername().equals(responseName)) {
    			return "/equipeUser.xhtml?faces-redirect=true";
    		}
    	} else {
    		//registratin
    		UserVO user = new UserVO();
        	user.setUsername(responseName);
        	user.setMail(responseMail);
        	user.setPwd(responseId);
        	
        	try {
    			userManager.add(user);
    		} catch (ApplicationAbdException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	//login app
    	UserVO loginU = new UserVO();
		loginU.setUsername(filter.getUsername());
		loginU.setPwd(responseId);
		return doLogin2(loginU);
		
    }
	
	public String doLogin() {
		
		UserVO loginU = new UserVO();
		loginU.setUsername(login.getUsername());
		loginU.setPwd(login.getPwd());
		loginU = userManager.login(loginU);
		
		if (loginU == null) {
			FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("com.abd.mfc.i18n.messages", localeBean.getLanguage().getLocale()).getString("error_login"), "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage("login_form", msg);
	        loggedIn = false;
	        return null;
		}
		connectedUser = new UserMO(loginU);
		loggedIn = true;
		
		String toView = "listMatch";
		
		if (nextPage != null && !nextPage.isEmpty()) {
			toView = nextPage;
		}
		
        return toView;
	}
	
	private String doLogin2(UserVO loginU) {
		String toView = "/equipeUser.xhtml?faces-redirect=true";
		
		loginU = userManager.login(loginU);
		
		if (loginU == null) {
			FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("com.abd.mfc.i18n.messages", localeBean.getLanguage().getLocale()).getString("error_login"), "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage("login_form", msg);
	        loggedIn = false;
	        return null;
		}
		connectedUser = new UserMO(loginU);
		loggedIn = true;

		
        return toView;
	}
	
	public String doLogout() {
		connectedUser = null;
		loggedIn = false;
		nextPage = "listMatch";
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(false);
		httpSession.invalidate();
		
		return "/login.xhtml?faces-redirect=true";
	}
	
	public void isConnected(ComponentSystemEvent event) {
		 
		FacesContext fc = FacesContext.getCurrentInstance();
		/*UIViewRoot v = FacesContext.getCurrentInstance().getViewRoot();
		Map<String, Object> viewMap = v.getViewMap();
		System.out.println("-------------------viewMap=" + viewMap);*/
		if (!loggedIn) {
			/*String msgRequi = ResourceBundle.getBundle("com.abd.mfc.i18n.messages", localeBean.getLanguage().getLocale()).getString("error_login_required");
			FacesMessage msg = new FacesMessage(msgRequi, "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        
	        Flash flash = fc.getExternalContext().getFlash();
	        flash.setKeepMessages(true);
	        //flash.setRedirect(true);
	        fc.addMessage("login_form", msg);*/
	        
			ConfigurableNavigationHandler nav 
			   = (ConfigurableNavigationHandler) 
				fc.getApplication().getNavigationHandler();
			nextPage = "equipeUser";
			nav.performNavigation("loginError");
			
			
		}
		
		
		
	  }	

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public LoginMO getLogin() {
		return login;
	}

	public void setLogin(LoginMO login) {
		this.login = login;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public UserMO getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(UserMO connectedUser) {
		this.connectedUser = connectedUser;
	}

	public String getNextPage() {
		return nextPage;
	}



	public String getResponseName() {
		return responseName;
	}

	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

}
