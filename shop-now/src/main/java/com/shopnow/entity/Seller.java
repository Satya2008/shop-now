package com.shopnow.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer sellerId;

	    private String name;

	    @Column(unique = true)
	    private String email;

	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    private String password;

	    @JsonIgnore
	    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	    private List<Product> products;
}
