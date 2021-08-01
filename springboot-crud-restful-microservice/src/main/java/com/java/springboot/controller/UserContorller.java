package com.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.entity.User;
import com.java.springboot.exception.ResourceNotFoundException;
import com.java.springboot.repo.UserRepository;



@RestController
@RequestMapping("/api/users")
public class UserContorller {

	
	
	@Autowired
	private UserRepository userRepo;
	
@GetMapping
	public java.util.List<User> getAllUsers() {
	return this.userRepo.findAll();
}

@GetMapping("/{id}")
public User getUserById(@PathVariable(value="id") long userId) {
	 return this.userRepo.findById(userId)
	 .orElseThrow(()->new ResourceNotFoundException("User not found with id:"+userId));
}

@PostMapping()
public User createUser(@RequestBody User user){
	return this.userRepo.save(user);
}

@PutMapping("{id}")
public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
	User existing =this.userRepo.findById(userId)
	 .orElseThrow(()->new ResourceNotFoundException("User not found with id:"+userId));
existing.setFirstName(user.getLastName());
existing.setLastName(user.getLastName());
existing.setEmail(user.getEmail());

return this.userRepo.save(existing);
}

@DeleteMapping("{id}")
public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
	User existing =this.userRepo.findById(userId)
			 .orElseThrow(()->new ResourceNotFoundException("User not found with id:"+userId));
	this.userRepo.delete(existing);
	return ResponseEntity.ok().build();
}

}

