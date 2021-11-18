package com.consomiTounsi.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.dao.UserDao;
import com.consomiTounsi.entities.Role;
import com.consomiTounsi.entities.UserDTO;
import com.consomiTounsi.entities.Users;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	@Autowired UsersRepository usrRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//Users user = userDao.findByname(username);
		Users user = usrRepo.findbyUserName(username); //(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				new ArrayList<>());
	}

	//public Users save(UserDTO user) {
	public Users save(Users user) {
		Users newUser = new Users();
		//newUser.setName(user.getUsername());
		newUser.setName(user.getName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		newUser.setEmail(user.getEmail());
		newUser.setRole(Role.Customer);
		newUser.setLastName(user.getLastName());
		newUser.setAddresse(user.getAddresse());
		newUser.setSexe(user.getSexe());
		
		
		return userDao.save(newUser);
	}

}