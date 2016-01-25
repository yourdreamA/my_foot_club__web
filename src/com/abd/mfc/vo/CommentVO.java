package com.abd.mfc.vo;

import java.util.Date;

import com.abd.mfc.entities.Comment;

public class CommentVO extends VO<Comment> {

	private long id;
	
	private String message;
	private int actif;
	private Date dateM;
	
	private UserVO user;

	@Override
	public Comment convertToEntity() {
		Comment entity = new Comment();
		entity.setMessage(message);
		entity.setActif(1);
		entity.setDateM(dateM);
		entity.setUser(user.convertToEntity());
		
		return entity;
	}
	
	public CommentVO() {}

	public CommentVO(Comment entity) {
		super();
		this.id = entity.getId();
		this.message = entity.getMessage();
		this.actif = entity.getActif();
		this.dateM = entity.getDateM();
		this.user = new UserVO(entity.getUser());
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getActif() {
		return actif;
	}

	public void setActif(int actif) {
		this.actif = actif;
	}

	public Date getDateM() {
		return dateM;
	}

	public void setDateM(Date dateM) {
		this.dateM = dateM;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
	
	
	
	
}
