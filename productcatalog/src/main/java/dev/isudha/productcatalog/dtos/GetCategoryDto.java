package dev.isudha.productcatalog.dtos;

import java.util.List;
import dev.isudha.productcatalog.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryDto {

    private Long id;
    private String name;
    private List<GetProductDto> products;

}
