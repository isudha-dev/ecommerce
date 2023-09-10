package dev.sudha.productcatalog.services;

import java.util.List;
import dev.sudha.productcatalog.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.sudha.productcatalog.dtos.GenericProductDto;
import dev.sudha.productcatalog.exceptions.NotFoundException;

public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(FakeStoreProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id) throws NotFoundException;

    GenericProductDto updateProductById(Long id, FakeStoreProductDto product);

}
