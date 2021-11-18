package com.consomiTounsi.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role {
	Customer,Administrator,StoreManager;

}





/*public enum Role implements GrantedAuthority {
	//Administrator,Customer,StoreManager;
	
	
    @Override
    public String toString() {
        return  "ROLE_"+this.name();
    }

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

   // USER("USER");
  /*  public final String name;

    ERole(String name) {
        this.name = name;
    }

   @Override
    public String getAuthority() {
        return "ROLE_" + this.name;
    }
    
    @Override
    public String getAuthority() {
        return this.name;
    }
    
    */
