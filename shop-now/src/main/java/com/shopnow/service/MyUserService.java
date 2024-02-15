package com.shopnow.service;

import com.shopnow.dto.MyUserDto;
import com.shopnow.entity.MyUser;
import com.shopnow.exceptions.SomethingWentWrong;

public interface MyUserService {
	
	MyUser registerMyUSer(MyUserDto myUser) throws SomethingWentWrong;
	MyUser login(String email, String Password) throws SomethingWentWrong ;

}
