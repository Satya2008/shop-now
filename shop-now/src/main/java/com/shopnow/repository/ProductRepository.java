package com.shopnow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopnow.entity.Product;
import com.shopnow.exceptions.SomethingWentWrong;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.seller.id = :sellerId")
	List<Product> findBySellerId(Integer sellerId) throws SomethingWentWrong;

	@Query("SELECT p FROM Product p WHERE p.productId = :productId AND p.seller.sellerId = :sellerId")
	Optional<Product> findByProductIdwithSellerId(@Param("productId") Integer productId,
			@Param("sellerId") Integer sellerId) throws SomethingWentWrong;

	@Query("SELECT p FROM Product p ORDER BY p.id ASC")
	List<Product> findTopNProducts(int limit) throws SomethingWentWrong;

	@Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR CAST(p.price AS string) LIKE %:keyword% OR p.description LIKE %:keyword% OR p.color LIKE %:keyword%")
	List<Product> findByKeyword(@Param("keyword") String keyword);

}
