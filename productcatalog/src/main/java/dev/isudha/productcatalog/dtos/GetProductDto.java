package dev.isudha.productcatalog.dtos;

import dev.isudha.productcatalog.models.Price;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetProductDto {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Long categoryId;
    private GetPriceDto price;

}
