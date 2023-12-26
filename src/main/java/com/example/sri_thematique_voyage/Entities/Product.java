package com.example.sri_thematique_voyage.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    String sku;
    String name;
    Double price;

    public Product(String sku, String name, Double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }



}
