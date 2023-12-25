package dev.isudha.productcatalog.services;

import java.util.List;
import dev.isudha.productcatalog.dtos.CreateProductDto;
import dev.isudha.productcatalog.exceptions.NotFoundException;
import dev.isudha.productcatalog.dtos.GetProductDto;

public interface ProductService {

    GetProductDto getProductById(Long id) throws NotFoundException;

    GetProductDto createProduct(CreateProductDto product);

    List<GetProductDto> getAllProducts();

    void deleteProductById(Long id);

    GetProductDto updateProductById(Long id, CreateProductDto product);

    List<GetProductDto> getProductsByCategoryId(Long id);



}
