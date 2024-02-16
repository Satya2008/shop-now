package com.shopnow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer productId;

	    private String name;
	    private Integer price;
	    private String description;
	    private String color;

	    @Column(columnDefinition = "TEXT")
	    private String image;
	    private String category;

	    @JsonIgnore
	    @ManyToOne
	    private Seller seller;
}
