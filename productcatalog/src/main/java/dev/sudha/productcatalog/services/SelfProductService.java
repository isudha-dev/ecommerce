package dev.sudha.productcatalog.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import dev.sudha.productcatalog.models.Category;
import dev.sudha.productcatalog.models.Price;
import dev.sudha.productcatalog.models.Product;
import dev.sudha.productcatalog.repositories.ProductRepo;
import dev.sudha.productcatalog.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.sudha.productcatalog.dtos.GenericProductDto;

@Service("SelfProductService")
public class SelfProductService implements ProductService{

    ProductRepo productRepo;

    public SelfProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }

    @Override public GenericProductDto getProductById(Long id) {
        return convertProductToGenericProductDto(productRepo.getProductById(id));
    }
    @Override public GenericProductDto createProduct(final GenericProductDto product) {
        Product createProduct = convertGenericProductDtoToProductDto(product);
        Product savedProduct = productRepo.save(createProduct);
        return convertProductToGenericProductDto(savedProduct);
    }
    @Override public List<GenericProductDto> getAllProducts() {
        List<Product> allProducts = productRepo.getAllProducts();
        List<GenericProductDto> genericProductList = new ArrayList<>();
        for(Product product: allProducts){
            genericProductList.add(convertProductToGenericProductDto(product));
        }
        return genericProductList;
    }
    @Override public GenericProductDto deleteProductById(final Long id) {
        Product deletedProduct = productRepo.deleteProductById(id);
        return convertProductToGenericProductDto(deletedProduct);
    }
    @Override public GenericProductDto updateProductById(final Long id, GenericProductDto product) {
        Product updateProduct = convertGenericProductDtoToProductDto(product);
        Product updatedProduct = productRepo.save(updateProduct);
        return convertProductToGenericProductDto(updatedProduct);
    }

    private GenericProductDto convertProductToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setImageUrl(product.getImageUrl());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setDescription(product.getDescription());
        return genericProductDto;
    }

    private Product convertGenericProductDtoToProductDto(GenericProductDto genericProductDto){
        Product product = new Product();
        product.setId(genericProductDto.getId());
        product.setImageUrl(genericProductDto.getImageUrl());
        product.setCategory(new Category(genericProductDto.getCategory()));
        product.setPrice(new Price(genericProductDto.getPrice()));
        product.setDescription(genericProductDto.getDescription());
        return product;
    }

}
