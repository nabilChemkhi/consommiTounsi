package com.consomiTounsi.Controller;

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

import com.consomiTounsi.Repository.CommentRepository;
import com.consomiTounsi.Repository.SubjectRepository;
import com.consomiTounsi.entities.Comment;
import com.consomiTounsi.entities.Subject;
import com.consomiTounsi.services.CommentService;
import com.consomiTounsi.services.SubjectService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	
	
	@GetMapping("/all")
	public List<Comment> getComments(){
		return commentService.listComment();
	}

	@GetMapping("/{id}")
	public Optional<Comment> getComment(@PathVariable Long id){
		return commentService.rechercheByid(id);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Comment> ajouter(@RequestBody Comment c) {
		return commentService.ajouter(c);
	}
	@DeleteMapping("/delete/{id}")
	public void supprimerComment(@PathVariable Long id){
		commentService.supprimer(id);
		
	}
	@PutMapping("update/{id}" )
	public ResponseEntity<Comment> UpdateSubject (@PathVariable Long id ,@RequestBody Comment c){
		
		return commentService.update(id, c);
	}


}
