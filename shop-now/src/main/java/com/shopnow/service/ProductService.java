package com.shopnow.service;

import java.util.List;

import com.shopnow.dto.ProductDTO;
import com.shopnow.entity.Product;
import com.shopnow.exceptions.SellerException;
import com.shopnow.exceptions.SomethingWentWrong;

public interface ProductService {
   Product addProduct(Integer sellerId, ProductDTO productDto) throws SomethingWentWrong;
   
   List<Product> getAllProducts(Integer sellerId) throws SellerException;
   Product deleteProduct(Integer productId, Integer sellerId) throws SomethingWentWrong;
   Product updateProduct(Integer productId, Integer sellerId, ProductDTO productDto) throws SomethingWentWrong;
   Product getProduct(Integer productId) throws SomethingWentWrong;
   List<Product> getProductsWithLimit(Integer limit) throws SomethingWentWrong;
   
   List<Product> searchProducts(String keyword) throws SomethingWentWrong;
}
