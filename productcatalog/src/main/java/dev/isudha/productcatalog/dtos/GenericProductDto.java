package dev.isudha.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private double price;

}
