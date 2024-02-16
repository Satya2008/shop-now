package com.shopnow.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopnow.entity.Cart;
import com.shopnow.entity.MyUser;
import com.shopnow.entity.Product;
import com.shopnow.exceptions.SomethingWentWrong;
import com.shopnow.repository.CartRepository;
import com.shopnow.repository.MyUserRepository;
import com.shopnow.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MyUserRepository myUserRepository;

	@Transactional(rollbackFor = SomethingWentWrong.class)
	public Product addProductInCart(Integer productId, Integer userId) throws SomethingWentWrong {

		  MyUser user = myUserRepository.findById(userId)
		            .orElseThrow(() -> new EntityNotFoundException("User not found"));
		    
		    Cart cart = user.getCart();
		    if (cart == null) {
		        cart = new Cart();
		        cart.setMyUser(user);
		        cart.setProducts(new ArrayList<>());
		    }

		    Product product = productRepository.findById(productId)
		            .orElseThrow(() -> new EntityNotFoundException("Product not found"));

		    cart.getProducts().add(product);
		    cartRepository.save(cart);

		    return product;
	}

}
