package com.consomiTounsi.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Dislike implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private Users user;

}
