package dev.sudha.productcatalog.services;

import java.util.List;
import org.springframework.stereotype.Service;
import dev.sudha.productcatalog.dtos.FakeStoreProductDto;
import dev.sudha.productcatalog.dtos.GenericProductDto;
import dev.sudha.productcatalog.models.Product;

@Service("SelfProductService")
public class SelfProductService implements ProductService{

    @Override public GenericProductDto getProductById(final Long id) {
        return null;
    }
    @Override public GenericProductDto createProduct(final FakeStoreProductDto product) {
        return null;
    }
    @Override public List<GenericProductDto> getAllProducts() {
        return null;
    }
    @Override public GenericProductDto deleteProductById(final Long id) {
        return null;
    }
    @Override public GenericProductDto updateProductById(final Long id, FakeStoreProductDto product) {
        return null;
    }
}
