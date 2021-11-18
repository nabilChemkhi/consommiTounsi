package com.consomiTounsi.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.consomiTounsi.entities.Subject;
import com.consomiTounsi.services.SubjectService;
import com.consomiTounsi.Repository.SubjectRepository;
import com.consomiTounsi.Repository.UsersRepository;
@RestController
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	
	
	@GetMapping("/all")
	public List<Subject> getSubjects(){
		return subjectService.listSubject();
	}

	@GetMapping("/{id}")
	public Optional<Subject> getSubjects(@PathVariable Long id){
		return subjectService.rechercheByid(id);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Subject> ajouter(@RequestBody Subject s) {
		return subjectService.ajouter(s);
	}
	@DeleteMapping("/delete/{id}")
	public void supprimerSubject(@PathVariable Long id){
		subjectService.supprimer(id);
		
	}
	@PutMapping("update/{id}" )
	public ResponseEntity<Subject> UpdateSubject (@PathVariable Long id ,@RequestBody Subject s){
		
		return subjectService.update(id, s);
	}

}
