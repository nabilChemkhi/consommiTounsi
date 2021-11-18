package com.consomiTounsi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

import net.minidev.json.annotate.JsonIgnore;



@Entity
public class Bill implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	//@JsonIgnore
	//@OneToOne(mappedBy = "bill")
	//private Order order;
	@JsonIgnore
	@ManyToOne
	private Users user;
	
	private float delivFees;
	/**************************************************************************************/
	/**************************************************************************************/
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public float getDelivFees() {
		return delivFees;
	}
	public void setDelivFees(float fraisDeliv) {
		this.delivFees = fraisDeliv;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
//	public Order getOrder() {
//		return order;
//	}
//	public void setOrder(Order order) {
//		this.order = order;
//	}
	
	
	

}
