package com.shopnow.controller;

import java.util.List;

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
import com.shopnow.entity.Product;
import com.shopnow.exceptions.SomethingWentWrong;
import com.shopnow.service.CartService;
import com.shopnow.service.MyUserService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class MyUserController {

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
    private CartService cartService;
	

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
	

	@GetMapping("/addProductToCart/{productId}/{userId}")
	public ResponseEntity<?> addProductToCart(@PathVariable Integer productId, @PathVariable Integer userId) {
	    try {
	        Product product = cartService.addProductInCart(productId, userId);
	        return new ResponseEntity<Product>(product, HttpStatus.OK);
	    } catch (EntityNotFoundException e) {
	        return new ResponseEntity<String>("not able to fetch", HttpStatus.BAD_GATEWAY);
	    } 
	}
    @GetMapping("/getAllProduct/{userId}")
    public ResponseEntity<?> getAllProducts(@PathVariable Integer userId) {
	    try {
	        List<Product> products = myUserService.getAllProduct(userId);
	        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	    } catch (EntityNotFoundException e) {
	        return new ResponseEntity<String>("not able to fetch", HttpStatus.BAD_GATEWAY);
	    } 
	}
	
}
