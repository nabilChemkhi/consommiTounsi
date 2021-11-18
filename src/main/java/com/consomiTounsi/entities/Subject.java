package com.consomiTounsi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat; 

@Entity

public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	private String nomSubject ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateCreationSubject ;

	
	@ManyToOne()
	private Users user;
	
	@OneToMany
	private Set<Comment> comments ;
	
	public Subject(String nomSubject, Date dateCreationSubject, Users user, Set<Comment> comments) {
		super();
		this.nomSubject = nomSubject;
		this.dateCreationSubject = dateCreationSubject;
		this.user = user;
		this.comments = comments;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Subject() {
		super();
	}
	public String getNomSubject() {
		return nomSubject;
	}
	public void setNomSubject(String nomSubject) {
		this.nomSubject = nomSubject;
	}
	public Date getDateCreationSubject() {
		return dateCreationSubject;
	}
	public void setDateCreationSubject(Date dateCreationSubject) {
		this.dateCreationSubject = dateCreationSubject;
	}
	public Subject(String nomSubject, Date dateCreationSubject) {
		super();
		this.nomSubject = nomSubject;
		this.dateCreationSubject = dateCreationSubject;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Subject(String nomSubject, Date dateCreationSubject, Users user) {
		super();
		this.nomSubject = nomSubject;
		this.dateCreationSubject = dateCreationSubject;
		this.user = user;
	}
	
	
	
}
