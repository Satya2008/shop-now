package com.shopnow.service;

import java.util.List;

import com.shopnow.dto.MyUserDto;
import com.shopnow.entity.MyUser;
import com.shopnow.entity.Product;
import com.shopnow.exceptions.SomethingWentWrong;

public interface MyUserService {
	
	MyUser registerMyUSer(MyUserDto myUser) throws SomethingWentWrong;
	MyUser login(String email, String Password) throws SomethingWentWrong ;
	List<Product> getAllProduct(Integer userId) throws SomethingWentWrong;

}
