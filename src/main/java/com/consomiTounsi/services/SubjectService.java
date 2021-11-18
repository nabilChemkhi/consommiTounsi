package com.consomiTounsi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.consomiTounsi.Repository.SubjectRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Subject;
import com.consomiTounsi.entities.Users;

@Service
public class SubjectService implements SubjectServiceInterface{

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	public ResponseEntity<Subject> ajouter(Subject s) {
		
		Optional<Users> users = usersRepository.findById(s.getUser().getId());
		subjectRepository.save(s);
		return new ResponseEntity<Subject>(s, HttpStatus.OK);
	}
	
	public List<Subject> listSubject(){
		
		return subjectRepository.findAll();
	}
	
	public void supprimer(Long id) {
		
		 subjectRepository.deleteById(id);
	}
	public Optional<Subject> rechercheByid(Long id) {
		return subjectRepository.findById(id);
	}
	public ResponseEntity<Subject> update(Long id,Subject s) {
		s.setId(id);
		subjectRepository.save(s);
		return new ResponseEntity<Subject>(s, HttpStatus.OK);	
	}
	
}
