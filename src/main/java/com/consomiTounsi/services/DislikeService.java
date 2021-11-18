package com.consomiTounsi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.consomiTounsi.Repository.DislikeRepository;
import com.consomiTounsi.entities.Dislike;
import com.consomiTounsi.entities.Likes;

@Service
public class DislikeService implements DislikeServiceInterface {
	
	@Autowired
	private DislikeRepository dislikeRepository;
	
	
	public ResponseEntity<Dislike> ajouter(Dislike d) {
		
		//Optional<Users> users = usersRepository.findById(s.getUser().getId());
		dislikeRepository.save(d);
		return new ResponseEntity<Dislike>(d, HttpStatus.OK);
	}
	
	public List<Dislike> listLikes(){
		
		return dislikeRepository.findAll();
	}
	
	public void supprimer(Long id) {
		
		dislikeRepository.deleteById(id);
	}
	public Optional<Dislike> rechercheByid(Long id) {
		return dislikeRepository.findById(id);
	}
	

}
