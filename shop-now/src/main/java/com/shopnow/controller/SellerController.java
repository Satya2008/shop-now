package com.shopnow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopnow.dto.ProductDTO;
import com.shopnow.dto.SellerDto;
import com.shopnow.entity.Product;
import com.shopnow.entity.Seller;
import com.shopnow.exceptions.SellerException;
import com.shopnow.exceptions.SomethingWentWrong;
import com.shopnow.service.ProductService;
import com.shopnow.service.SellerService;

@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = "*")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ProductService productService;

	@PostMapping("/")
	public ResponseEntity<?> signUpSeller(@RequestBody SellerDto sellerDto) {
		try {

			Seller seller = sellerService.signUpSeller(sellerDto);
			return new ResponseEntity<Seller>(seller, HttpStatus.CREATED);
		} catch (SellerException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			Seller seller = sellerService.login(email, password);
			return new ResponseEntity<Seller>(seller, HttpStatus.OK);
		} catch (SellerException e) {
			return new ResponseEntity<String>("not able to login", HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/add/{sellerId}")
	public ResponseEntity<?> addProduct(@PathVariable Integer sellerId, @RequestBody ProductDTO productDto) {
		try {
			Product addedProduct = productService.addProduct(sellerId, productDto);
			return new ResponseEntity<Product>(addedProduct, HttpStatus.CREATED);
		} catch (SomethingWentWrong exception) {
			return new ResponseEntity<String>("not able to add product", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{sellerId}/products")
	public ResponseEntity<?> getAllProductsForSeller(@PathVariable Integer sellerId) {
		try {
			List<Product> products = productService.getAllProducts(sellerId);
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} catch (SomethingWentWrong ex) {
			return new ResponseEntity<String>("Something is wrong, fetching all products", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/products/{productId}/seller/{sellerId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId, @PathVariable Integer sellerId) {
		try {
			Product deletedProduct = productService.deleteProduct(productId, sellerId);
			return new ResponseEntity<Product>(deletedProduct, HttpStatus.OK);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<String>("Not able to Delete", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/products/{productId}/seller/{sellerId}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @PathVariable Integer sellerId,
			@RequestBody ProductDTO productDto) {
		try {
			Product updatedProduct = productService.updateProduct(productId, sellerId, productDto);

			return new ResponseEntity<Product>(updatedProduct, HttpStatus.ACCEPTED);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<String>("Not able to Update", HttpStatus.NOT_FOUND);
		}
	}

	

	@GetMapping("/product/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable Integer productId) {
		try {
			Product product = productService.getProduct(productId);
			return  new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
		} catch (SomethingWentWrong e) {
			return new ResponseEntity<String>("Not able to fetch Product ", HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/products/{limit}")
	public ResponseEntity<?> getProductsWithLimit(@PathVariable Integer limit) {
	    try {
	        List<Product> products = productService.getProductsWithLimit(limit);
	        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	    } catch (SomethingWentWrong e) {
	        return new ResponseEntity<String>("Not able to fetch", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	  @GetMapping("/products/search/{keyword}")
	    public ResponseEntity<?> searchProducts(@PathVariable String keyword) {
	        try {
	            List<Product> products = productService.searchProducts(keyword);
	            return new ResponseEntity<List<Product> >(products, HttpStatus.OK);
	        } catch (SomethingWentWrong e) {
	            return new ResponseEntity<String>("Error occurred while searching for products", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
