package com.consomiTounsi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import net.minidev.json.annotate.JsonIgnore;


@Entity
public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	
    
	@JsonIgnore
  @ManyToOne
	private Product product;
  @JsonIgnore
  @ManyToOne
  @JsonBackReference
	private Order order;
   @JsonIgnore
   @ManyToOne
   private Users user;
   
   

	//@ManyToOne
	//private ShoppingCart choppingCart;
   


	
    @Column(name = "Quanity")
    private int quanity;
 
    @Column(name = "Amount")
    private float amount;
    
    @Column(name = "stat")
    private int stat;



public CartItem(int quanity, String test, float amount) {
		super();
		this.quanity = quanity;
	
		this.amount = amount;
	}






	
	public int isStat() {
	return stat;
}







public void setStat(int stat) {
	this.stat = stat;
}







	/*****************************************************************************/



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}



	public Users getUser() {
		return user;
	}



	public void setUser(Users user) {
		this.user = user;
	}



	public int getQuanity() {
		return quanity;
	}



	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}



	public float getAmount() {
		return amount;
	}



	public void setAmount(float amount) {
		this.amount = amount;
	}



	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
    

	










	
	
	
	
	
	

}
