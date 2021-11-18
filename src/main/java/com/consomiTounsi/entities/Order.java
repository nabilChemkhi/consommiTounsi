package com.consomiTounsi.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="T_ORDER")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id ;
		@JsonIgnore
		@OneToOne(cascade =CascadeType.REMOVE)
		private Bill bill;
		
		//@ManyToOne
		//private ShoppingCart choppingCart;
		@JsonIgnore
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
		@JsonManagedReference
		
//		@JsonIgnore
//		@OneToMany(mappedBy="order", 
//				   cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
//				   fetch=FetchType.LAZY)
		private List<CartItem> cartItems = new ArrayList<>();
		@JsonIgnore
		@ManyToOne
		private Users user;
		
		@Enumerated(EnumType.STRING)
		private  OrderStatus orderStatus;
		
		@Enumerated(EnumType.STRING)
		private PaymentMethod paymentMethod;

		//@Temporal(TemporalType.DATE)
	    @Column(name = "Order_Date")
	    private LocalDateTime orderDate;
		
	    @Column(name = "Amount")
	    private float amount;
	    
	    float DelivFees;
	 
	 /*   @Column(name = "Customer_Name")
	    private String customerName;
	 
	    @Column(name = "Customer_Address")
	    private String customerAddress;
	 
	    @Column(name = "Customer_Email")
	    private String customerEmail;
	 
	    @Column(name = "Customer_Phone")
	    private String customerPhone;
	*/
	/*******************************************************/
	    
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public float getDelivFees() {
		return DelivFees;
	}
	public void setDelivFees(float delivFees) {
		DelivFees = delivFees;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users customer) {
		this.user = customer;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime localDateTime) {
		this.orderDate = localDateTime;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/*public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	

	*/
	
	
	
	
}
