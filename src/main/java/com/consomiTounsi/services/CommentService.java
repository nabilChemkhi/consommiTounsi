package com.consomiTounsi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.consomiTounsi.Repository.CommentRepository;
import com.consomiTounsi.Repository.SubjectRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Comment;
import com.consomiTounsi.entities.Subject;
import com.consomiTounsi.entities.Users;

@Service
public class CommentService implements CommentServiceInterface {
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public ResponseEntity<Comment> ajouter(Comment c) {
		
		
		c.setSubject(subjectRepository.findById(c.getSubject().getId()).get());
		//Optional<Users> users = usersRepository.findById(s.getUser().getId());
		commentRepository.save(c);
		return new ResponseEntity<Comment>(c, HttpStatus.OK);
	}
	
	public List<Comment> listComment(){
		
		return commentRepository.findAll();
	}
	
	public void supprimer(Long id) {
		
		commentRepository.deleteById(id);
	}
	public Optional<Comment> rechercheByid(Long id) {
		return commentRepository.findById(id);
	}
	public ResponseEntity<Comment> update(Long id,Comment c) {
		c.setId(id);
		commentRepository.save(c);
		return new ResponseEntity<Comment>(c, HttpStatus.OK);	
	}
	
}



