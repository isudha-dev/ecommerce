package dev.sudha.productcatalog.models;

import lombok.Setter;

@Setter
public class Product {
    private String title;
    private String description;
    private String imageUrl;
    private Category category;
    private double price;

}
