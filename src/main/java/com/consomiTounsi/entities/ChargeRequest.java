package com.consomiTounsi.entities;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.SerializedName;
@Component
public class ChargeRequest {
	
	
/*	@SerializedName("items")
	Object[] objects;

	public ChargeRequest(Object[] objects) {
		super();
		this.objects = objects;
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}*/
	
   // public enum Currency {
     //   EUR, USD;
    //} 
	
    private String description;
    private int amount;
    private String currency;
    public void setCurrency(String currency) {
		this.currency = currency;
	}
	private String stripeToken;
    
    /**************************************/
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	public String getStripeToken() {
		return stripeToken;
	}
	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}
	public ChargeRequest() {
		super();
	}
	
	public String getCurrency() {
		return currency;
	}
	public ChargeRequest(String description, int amount, String currency, String stripeToken) {
		super();
		this.description = description;
		this.amount = amount;
		this.currency = currency;
		this.stripeToken = stripeToken;
	}
	@Override
	public String toString() {
		return "ChargeRequest [description=" + description + ", amount=" + amount + ", currency=" + currency
				+ ", stripeEmail=" + ", stripeToken=" + stripeToken + "]";
	}

}
