package dev.sudha.productcatalog.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import dev.sudha.productcatalog.dtos.ExceptionDto;
import dev.sudha.productcatalog.dtos.FakeStoreProductDto;
import dev.sudha.productcatalog.dtos.GenericProductDto;
import dev.sudha.productcatalog.exceptions.NotFoundException;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String productIdRequestURL = "https://fakestoreapi.com/products/{id}";
    private String productRequestURL = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override public GenericProductDto getProductById(final Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productIdRequestURL, FakeStoreProductDto.class, id);
        if(response.getBody() == null){
            throw new NotFoundException(String.format("Product %s not found during search", id));
        }

        return getGenericProductDto(response.getBody());
    }
    @Override public GenericProductDto createProduct(final FakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestURL, product, FakeStoreProductDto.class);

        return getGenericProductDto(response.getBody());
    }
    @Override public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<List> response = restTemplate.getForEntity(productRequestURL, List.class); Type erasure
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestURL, FakeStoreProductDto[].class);

//        List<FakeStoreProductDto> fakeStoreProductDtosList = response.getBody();
        List<GenericProductDto> genericProductList = new ArrayList<>();
        for(FakeStoreProductDto productDto : response.getBody()){
            genericProductList.add(getGenericProductDto(productDto));
        }

        return genericProductList;
    }
    @Override public GenericProductDto deleteProductById(final Long id) throws NotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productIdRequestURL, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
        if(response.getBody() == null){
            throw new NotFoundException(String.format("Product %s not found for delete", id));
        }
        return getGenericProductDto(response.getBody());
    }
    @Override
    public GenericProductDto updateProductById(final Long id, FakeStoreProductDto updatedProduct) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto existingProduct = new FakeStoreProductDto();
        existingProduct.setId(id);
        existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setTitle(updatedProduct.getTitle());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productIdRequestURL, HttpMethod.PUT, new HttpEntity<>(existingProduct), FakeStoreProductDto.class, id);

        return getGenericProductDto(response.getBody());
    }

    private GenericProductDto getGenericProductDto(final FakeStoreProductDto fakeStoreProductDtos) {
        GenericProductDto genericProduct = new GenericProductDto();
        genericProduct.setId(fakeStoreProductDtos.getId());
        genericProduct.setCategory(fakeStoreProductDtos.getCategory());
        genericProduct.setImage(fakeStoreProductDtos.getImage());
        genericProduct.setTitle(fakeStoreProductDtos.getTitle());
        genericProduct.setPrice(fakeStoreProductDtos.getPrice());
        genericProduct.setDescription(fakeStoreProductDtos.getDescription());

        return genericProduct;
    }

}
