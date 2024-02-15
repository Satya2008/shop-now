package com.shopnow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopnow.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer>{
	 Optional<MyUser>  findByEmail(String email);
}
