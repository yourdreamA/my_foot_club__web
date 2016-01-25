package com.abd.mfc.jsf.beans;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.CommentManager;
import com.abd.mfc.vo.CommentVO;
import com.abd.mfc.vo.UserVO;

@Controller
@Scope("request")
public class CommentBean extends BaseBean {

	private List<CommentVO> listMessages;
	
	@Autowired
    private CommentManager commentManager;
	
	@Autowired
	private LoginBean loginBean;
	
	@Autowired
	 private LocaleBean localeBean;
	
	private String messageUser;
	
	@PostConstruct
	public void init() throws ApplicationAbdException {
		listMessages = commentManager.getAll();
		messageUser = "";
		
	}
	
	public void addMessage() throws ApplicationAbdException {
		if (loginBean.isLoggedIn()) {
			CommentVO message = new CommentVO();
			message.setDateM(new Date());
			message.setMessage(messageUser);
			
			UserVO user = new UserVO();
			user.setId(loginBean.getConnectedUser().getId());
			message.setUser(user);
			
			commentManager.add(message);
			
			init();
		} else {
			FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("com.abd.mfc.i18n.messages", localeBean.getLanguage().getLocale()).getString("error_login_comment_required"), "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage("comment_form", msg);
		}
		
	}

	public List<CommentVO> getListMessages() {
		return listMessages;
	}

	public String getMessageUser() {
		return messageUser;
	}

	public void setMessageUser(String messageUser) {
		this.messageUser = messageUser;
	}
}
