package dev.sudha.productcatalog.services;

import java.util.List;
import org.springframework.stereotype.Service;
import dev.sudha.productcatalog.dtos.FakeStoreProductDto;
import dev.sudha.productcatalog.dtos.GenericProductDto;
import dev.sudha.productcatalog.exceptions.NotFoundException;
import dev.sudha.productcatalog.models.Product;

public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(FakeStoreProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id) throws NotFoundException;

    GenericProductDto updateProductById(Long id, FakeStoreProductDto product);

}
