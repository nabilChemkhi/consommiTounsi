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

import com.consomiTounsi.entities.Likes;
import com.consomiTounsi.services.LikeService;
@RestController
@RequestMapping("/like")
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
	@GetMapping("/all")
	public List<Likes> getAllLikes(){
		return likeService.listLikes();
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Likes> ajouter(@RequestBody Likes l){
		return likeService.ajouter(l);
	}
	
	@DeleteMapping("/delete/{id}")
	public void supprimerLikes(@PathVariable Long id ) {
		likeService.supprimer(id);
	}
	
	
	

}
