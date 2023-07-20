package com.pozatticaique.projeto24.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pozatticaique.projeto24.entities.Post;
import com.pozatticaique.projeto24.resources.util.URL;
import com.pozatticaique.projeto24.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity <Post> findByID(@PathVariable String id){		
		Post result = postService.findById(id);
		return ResponseEntity.ok().body(result);		
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity <List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){	
		text = URL.decodeParam(text);
		List<Post> result = postService.findByTitle(text);
		return ResponseEntity.ok().body(result);		
	}
	
	
}
