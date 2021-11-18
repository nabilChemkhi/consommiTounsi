package com.consomiTounsi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.consomiTounsi.Repository.LikeRepository;
import com.consomiTounsi.Repository.SubjectRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Comment;
import com.consomiTounsi.entities.Likes;
import com.consomiTounsi.entities.Subject;
import com.consomiTounsi.entities.Users;

@Service
public class LikeService implements LikeServiceInterface {
	
	@Autowired
	UsersRepository usersRepository ; 
	
	@Autowired
	
	SubjectRepository subjectRepository ; 
	
	@Autowired
	
	LikeRepository likeRepository ; 
	
	
public ResponseEntity<Likes> ajouter(Likes l) {
		
		//Optional<Users> users = usersRepository.findById(s.getUser().getId());
		likeRepository.save(l);
		return new ResponseEntity<Likes>(l, HttpStatus.OK);
	}
	
	public List<Likes> listLikes(){
		
		return likeRepository.findAll();
	}
	
	public void supprimer(Long id) {
		
		likeRepository.deleteById(id);
	}
	public Optional<Likes> rechercheByid(Long id) {
		return likeRepository.findById(id);
	}
	
	

}
