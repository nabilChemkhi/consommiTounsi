package com.consomiTounsi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity

public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	private String name;
	
	private String lastName;
	
	private String addresse;
	
	private String sexe;
	
	private String email;
	
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users(String name, String lastName, String addresse, String sexe, String email, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.addresse = addresse;
		this.sexe = sexe;
		this.email = email;
		this.password = password;
	}

	public Users() {
		super();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	public List<Subject> getSubjects() {
//		return subjects;
//	}
//
//	public void setSubjects(List<Subject> subjects) {
//		this.subjects = subjects;
//	}

	public Users(String name, String lastName, String addresse, String sexe, String email, String password, Role role) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.addresse = addresse;
		this.sexe = sexe;
		this.email = email;
		this.password = password;
		this.role = role;
		//this.subjects = subjects;
	}

	

}
