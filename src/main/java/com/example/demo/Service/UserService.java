package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;

	public String register(User user) {
		User validEmail = userRepo.findByEmail(user.getEmail());
		if (validEmail == null) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String encryptedPwd = bcrypt.encode(user.getPassword());
			user.setPassword(encryptedPwd);
			User savedUser = userRepo.save(user);
			return savedUser.getUsername() + " added to the database successfully";
		} else {
			return "User already Exist";
		}
	}

	public String login(User user) {
		User validEmail = userRepo.findByEmail(user.getEmail());
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		if (validEmail != null) {
			if (bcrypt.matches(user.getPassword(), validEmail.getPassword())) {
				return "Welcome to login page " + validEmail.getUsername();
			} else {
				return "Incorrect password";
			}
		} else {
			return "Incorrect credentials";
		}

	}
}
