package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String addUser(@Valid @RequestBody User user) {
		String msg = userService.register(user);
		return msg;
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		String msg = userService.login(user);
		return msg;
	}

}
