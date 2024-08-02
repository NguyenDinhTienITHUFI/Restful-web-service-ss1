package com.tiennguyen.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiennguyen.rest.webservices.restful_web_services.Jpa.PostRepository;
import com.tiennguyen.rest.webservices.restful_web_services.Jpa.UserRepository;

import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*
;
@RestController
public class UserJpaResource {
	
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
		
		this.userRepository=userRepository;
		this.postRepository=postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUser(){
		
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User>  retrieveUser(@PathVariable int id){
		
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		EntityModel<User> entityModel=EntityModel.of(user.get());
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveAllUser());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	@PostMapping("/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User u) {
		User saveUser= userRepository.save(u);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		post.setUser(user.get());
		Post savePost=postRepository.save(post);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savePost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	
	

}
