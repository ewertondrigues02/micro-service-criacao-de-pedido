package br.com.ewerton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ewerton.model.User;
import br.com.ewerton.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> allUser() {
		return userRepository.findAll();
	}

	public User createUser(User obj) {
		return userRepository.save(obj);
	}
}
