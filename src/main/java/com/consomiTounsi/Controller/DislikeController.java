package com.consomiTounsi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consomiTounsi.entities.Dislike;
import com.consomiTounsi.services.DislikeService;

@RestController
@RequestMapping("/Dislike")
public class DislikeController {
	
	@Autowired
	private DislikeService dislikeService;
	
	
	@GetMapping("/all")
	public List<Dislike> getAllDislike(){
		return dislikeService.listLikes();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Dislike> ajouter(@RequestBody Dislike d){
		return dislikeService.ajouter(d);
	}
	
	@DeleteMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		dislikeService.supprimer(id);
	}

}
