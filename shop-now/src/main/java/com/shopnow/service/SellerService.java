package com.shopnow.service;

import com.shopnow.dto.SellerDto;
import com.shopnow.entity.Seller;
import com.shopnow.exceptions.SellerException;

public interface SellerService {
	
	Seller signUpSeller(SellerDto sellerDto) throws SellerException;
	Seller login(String email, String Password) throws SellerException;
	

}
