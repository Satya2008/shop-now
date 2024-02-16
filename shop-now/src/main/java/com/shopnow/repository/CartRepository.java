package com.shopnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopnow.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
