package com.shopnow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    

    
    private String name;
    
    private Integer price;
    
    private String description;
    
    private String color;
    
    private String image;
    
    private String category;
    
  
    
}