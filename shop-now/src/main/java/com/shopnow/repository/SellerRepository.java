package com.shopnow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopnow.entity.Seller;
import com.shopnow.exceptions.SellerException;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
	
	Optional<Seller> findByEmail(String email) throws SellerException;

}
