package com.consomiTounsi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Subject;
import com.consomiTounsi.entities.Users;
import com.consomiTounsi.services.UsersService;

@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	private UsersService userService;
	@Autowired
	UsersRepository userRepoistory ; 
	
	@PostMapping("/user/add")
	public Users ajouterUsers(@RequestBody Users us) {
		
		 userRepoistory.save(us);
		 return(us);
		
		//return userService.ajouterUsers(us);
	}
	@GetMapping("/user/all")
	public List<Users> listUsers() {
		return userRepoistory.findAll();
	}
	
	@GetMapping("/user/get/{id}")
	public Users getUser(@PathVariable Long id) {
		return userRepoistory.findById(id).get();
	}
	
	@GetMapping("/user/get2")
	public Users getUser2() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user0 = userRepoistory.findbyUserName(auth.getName());
		long id0=user0.getId();
		
		return userRepoistory.findById(id0).get();
	}
	
	@PutMapping("/user/update/{id}" )
	public Users UpdateUser (@PathVariable Long id ,@RequestBody Users u){
		u.setId(id);
	
		return userRepoistory.save(u);
	}
	
//	@PutMapping("/user/update2/{id}" )
//	public Users UpdateUser2 (@PathVariable Long id ,@RequestBody Users u){
//		Users usr = userRepoistory.findById(id).get();
//		usr=u;
//	
//		 userRepoistory.save(usr);
//		 return usr;
//	}
//	
//	@PutMapping("/user/update3" )
//	public Users UpdateUser3 (@RequestBody Users u){
//		Users usr = userRepoistory.findById(u.getId()).get();
//		usr=u;
//	
//		 userRepoistory.save(u);
//		 return usr;
//	}

	@DeleteMapping("user/delete/{id}")
	public void supprimerUser(@PathVariable Long id){
		userRepoistory.deleteById(id);//deleteById(id); //.deleteById(id);
		
	}
	
	
}
