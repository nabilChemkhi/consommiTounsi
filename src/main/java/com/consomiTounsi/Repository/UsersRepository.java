package com.consomiTounsi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.consomiTounsi.entities.Users;

@Service
public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query("FROM Users u WHERE (u.email LIKE %:email%)" + "AND (u.password LIKE %:password%)")
	Boolean FindUserByNameAndPassword(String email , String password);
	Optional<Users> findById(int i);
	
	@Query("SELECT u FROM Users u WHERE u.name = :name" )
	//Optional<Users>
	Users findbyUserName(@Param("name")String name);
	
	//@Query("SELECT e FROM Employe e WHERE e.email=:email and e.password=:password")
	//public Employe getEmployeByEmailAndPassword(@Param("email")String login, @Param("password")String password);
}
