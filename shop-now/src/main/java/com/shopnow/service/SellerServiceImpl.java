package com.shopnow.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopnow.dto.SellerDto;
import com.shopnow.entity.Seller;
import com.shopnow.exceptions.SellerException;
import com.shopnow.repository.ProductRepository;
import com.shopnow.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
    @Autowired
    private ModelMapper modelMapper; 

    @Override
    public Seller signUpSeller(SellerDto sellerDto) throws SellerException {
        Optional<Seller> existSeller = sellerRepository.findByEmail(sellerDto.getEmail());
        
        if (existSeller.isPresent()) {
            throw new SellerException("Seller with this email already exists.");
        }

        Seller newSeller = modelMapper.map(sellerDto, Seller.class);
        return sellerRepository.save(newSeller);
    }


	@Override
	public Seller login(String email, String password) throws SellerException {
		 Optional<Seller> seller = sellerRepository.findByEmail(email);
	        
	        if (seller.isPresent()) {
	            Seller s = seller.get();
	          
	            if (s.getPassword().equals(password)) {
	                return s;
	            } else {
	                throw new SellerException("Invalid password");
	            }
	        } else {
	            throw new SellerException("Seller not found with email: " + email);
	        }
	    }


	
	}


