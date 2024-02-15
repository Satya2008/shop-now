package com.shopnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopnow.dto.MyUserDto;
import com.shopnow.entity.MyUser;
import com.shopnow.entity.Seller;
import com.shopnow.exceptions.SellerException;
import com.shopnow.exceptions.SomethingWentWrong;
import com.shopnow.service.MyUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class MyUserController {

	@Autowired
	private MyUserService myUserService;

	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody MyUserDto  myUser) {
		try {
			MyUser user = myUserService.registerMyUSer(myUser);
			return new ResponseEntity<MyUser>(user, HttpStatus.CREATED);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<String>("Something is wrong during saving data of USer", HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			MyUser myUser = myUserService.login(email, password);
			return new ResponseEntity<MyUser>(myUser, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<String>("not able to login", HttpStatus.BAD_REQUEST);
		}

	}
	
}
