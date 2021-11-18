package com.consomiTounsi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.consomiTounsi.entities.Users;

@Service
public interface UsersServiceInterface {
	Boolean FindUserByNameAndPassword(String email , String password);
	
	public List<Users> listUsers();
	public Users ajouterUsers (Users us );

}
