package com.consomiTounsi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Users;
@Service
public class UsersService implements UsersServiceInterface{

	@Autowired
	private UsersRepository usersRepository;
	@Override
	public Boolean FindUserByNameAndPassword(String email, String password) {
		if(usersRepository.FindUserByNameAndPassword(email, password)) {
			return true;
		}
		return false;
	}
	
	
	public Users ajouterUsers (Users us )
	{
		return usersRepository.save(us);
	}
	public List<Users> listUsers()
	{
		return usersRepository.findAll();
		
	}
	
	
	

}
