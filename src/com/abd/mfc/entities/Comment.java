package com.abd.mfc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mfc_comment")
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String message;
	private int actif;
	private Date dateM;
	
	@ManyToOne
	private User user;
	
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
