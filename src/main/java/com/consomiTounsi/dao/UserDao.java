package com.consomiTounsi.dao;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.repository.CrudRepository;

import com.consomiTounsi.entities.UserDTO;
import com.consomiTounsi.entities.Users;

public interface UserDao extends CrudRepository<Users, Long> {
	
	Users findByname(String username);

}
