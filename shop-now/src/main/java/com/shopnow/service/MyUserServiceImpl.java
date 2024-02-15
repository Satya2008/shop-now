package com.shopnow.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopnow.dto.MyUserDto;
import com.shopnow.entity.MyUser;
import com.shopnow.entity.Seller;
import com.shopnow.exceptions.SellerException;
import com.shopnow.exceptions.SomethingWentWrong;
import com.shopnow.repository.MyUserRepository;

@Service
public class MyUserServiceImpl implements MyUserService {

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MyUser registerMyUSer(MyUserDto myUser) throws SomethingWentWrong {

		MyUser user = modelMapper.map(myUser, MyUser.class);

		return myUserRepository.save(user);
	}

	@Override
	public MyUser login(String email, String password) throws SomethingWentWrong {
		Optional<MyUser> myUser = myUserRepository.findByEmail(email);

		if (myUser.isPresent()) {
			MyUser my = myUser.get();

			if (my.getPassword().equals(password)) return my;
			 else 	throw new SomethingWentWrong("Invalid password");

			} 
		else {
			throw new SomethingWentWrong("Seller not found with email: " + email);
		}
	}

}
