package src.java.main.org.msuffo.Blogy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.msuffo.Blogy.dao.PostDAO;
import org.msuffo.Blogy.exceptions.NoPermissionException;
import org.msuffo.Blogy.models.Post;
import org.msuffo.Blogy.services.ServicesPost;

@RestController
public class GreeterController {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private ServicesPost sPost;
	
	@GetMapping("/posts")
	public ResponseEntity<Post> getPosts() {
		Post post = new Post(0, "Post hard", "Contenido del post", new Date(-3), "admin");
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@GetMapping("/post")
	public ResponseEntity<Post> getPostById(@RequestParam("id") int id) {
		Post post = postDAO.get(id);
	return new ResponseEntity<Post>(post, HttpStatus.OK);	
	}
	
	@PostMapping("/newpost")
	public ResponseEntity<String> RegPost(@RequestBody Post post){
		try {
			sPost.RegPost(post);
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
		}catch (NoPermissionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
}