package com.consomiTounsi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd")	
	private Date dateComment;
	
	private String Contenu;
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private Users user;

	public Comment(Date dateComment, String contenu, Subject subject, Users user) {
		super();
		this.dateComment = dateComment;
		Contenu = contenu;
		this.subject = subject;
		this.user = user;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateComment() {
		return dateComment;
	}

	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}

	public String getContenu() {
		return Contenu;
	}

	public void setContenu(String contenu) {
		Contenu = contenu;
	}

	public Comment() {
		super();
	}

	
	

}
