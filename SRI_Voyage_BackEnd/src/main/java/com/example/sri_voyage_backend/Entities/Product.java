package com.example.sri_voyage_backend.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class Product {

    String sku;
    String name;
    Double price;
    String description;



    public Product(String sku, String name, Double price,String description) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.description = description;
    }



}
