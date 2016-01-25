package com.abd.mfc.vo;

import java.io.Serializable;
import java.util.List;

import com.abd.mfc.entities.BaseEntity;

public abstract class VO<ENTITY extends BaseEntity> implements Serializable {

	private boolean validate;
	private List<MessageVO> messages;
	
	public abstract ENTITY convertToEntity();
	
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public List<MessageVO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageVO> messages) {
		this.messages = messages;
	}
	
	
	
}
