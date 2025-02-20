package br.com.ewerton.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ewerton.model.User;
import br.com.ewerton.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private UserService userService;

	public ResponseEntity<List<User>> userAll(@RequestBody User user) {
		user = userService.createUser(user);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<User> createUser(@RequestBody User user) {
		user = userService.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
