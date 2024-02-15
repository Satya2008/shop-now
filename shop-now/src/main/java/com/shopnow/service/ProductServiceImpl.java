package com.shopnow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopnow.dto.ProductDTO;
import com.shopnow.entity.Product;
import com.shopnow.entity.Seller;
import com.shopnow.exceptions.SellerException;
import com.shopnow.exceptions.SomethingWentWrong;
import com.shopnow.repository.ProductRepository;
import com.shopnow.repository.SellerRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public Product addProduct(Integer sellerId, ProductDTO productDto) throws SomethingWentWrong {
		Seller seller = sellerRepository.findById(sellerId)
				.orElseThrow(() -> new SomethingWentWrong("Seller not found with ID: " + sellerId));

		Product product = modelMapper.map(productDto, Product.class);
		product.setSeller(seller);

		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts(Integer sellerId) throws SellerException {
		Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
		if (optionalSeller.isEmpty()) {
			throw new SellerException("Seller not found with ID: " + sellerId);
		}
		return productRepository.findBySellerId(sellerId);
	}

	@Override
	public Product deleteProduct(Integer productId, Integer sellerId) throws SomethingWentWrong {
		try {
			Optional<Product> productToDelete = productRepository.findByProductIdwithSellerId(productId, sellerId);

			if (productToDelete.isPresent()) {
				Product product = productToDelete.get();
				productRepository.delete(product);
				return product;
			} else {
				throw new SomethingWentWrong(
						"Product with ID " + productId + " not found for seller with ID " + sellerId);
			}
		} catch (Exception e) {
			throw new SomethingWentWrong(
					"Error deleting product with ID " + productId + " for seller with ID " + sellerId);
		}
	}

	@Transactional
	@Override
	public Product updateProduct(Integer productId, Integer sellerId, ProductDTO productDto) throws SomethingWentWrong {
		try {
			Optional<Product> productOptional = productRepository.findByProductIdwithSellerId(productId, sellerId);

			if (productOptional.isPresent()) {
				Product existingProduct = productOptional.get();
				modelMapper.map(productDto, existingProduct);
				productRepository.save(existingProduct);
				return existingProduct;
			} else {
				throw new SomethingWentWrong(
						"Product with ID " + productId + " not found for seller with ID " + sellerId);
			}
		} catch (Exception e) {
			throw new SomethingWentWrong(
					"Error updating product with ID " + productId + " for seller with ID " + sellerId);
		}
	}

	@Override
	public Product getProduct(Integer productId) throws SomethingWentWrong {
		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new SomethingWentWrong("Product not found with ID: " + productId);
		}
		return product.get();
	}

	@Override
	public List<Product> getProductsWithLimit(Integer limit) throws SomethingWentWrong {
		try {
			List<Product> products = productRepository.findAll();
			List<Product> productsForEendpoints = new ArrayList<>();
			for (int i = 0; i < limit; i++) {
				productsForEendpoints.add(products.get(i));
			}
			return productsForEendpoints;
		} catch (Exception e) {
			throw new SomethingWentWrong("Error occurred while fetching top N products");
		}
	}

	@Override
	public List<Product> searchProducts(String keyword) throws SomethingWentWrong {
		try {
			return productRepository.findByKeyword(keyword);
		} catch (Exception e) {
			throw new SomethingWentWrong("Error occurred while searching for products");
		}
	}
}
