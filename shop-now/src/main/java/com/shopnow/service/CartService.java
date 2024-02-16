package com.shopnow.service;

import com.shopnow.entity.Product;
import com.shopnow.exceptions.SomethingWentWrong;

public interface CartService {
   Product addProductInCart(Integer productId, Integer userId) throws SomethingWentWrong;
	
}
