package dev.isudha.productcatalog.thirdpartyclients.productservice.fakestore;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import dev.isudha.productcatalog.exceptions.NotFoundException;

@Service
public class FakeStoreProductServiceClient {
    //just a representation of FakeStore APIs

    private RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;
    private String productIdRequestURL;

    private String productRequestURL;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakeStore.url}") String fakeStoreUrl, @Value("${fakeStore.api.product.path}")String fakeStoreApiProductPath){
        this.restTemplateBuilder = restTemplateBuilder;
        restTemplate = restTemplateBuilder.build();
        this.productIdRequestURL = fakeStoreUrl + fakeStoreApiProductPath + "/{id}";
        this.productRequestURL = fakeStoreUrl + fakeStoreApiProductPath;
    }

    public FakeStoreProductDto getProductById(final Long id) throws NotFoundException {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productIdRequestURL, FakeStoreProductDto.class, id);
        if(response.getBody() == null){
            throw new NotFoundException(String.format("Product %s not found during search", id));
        }
        return response.getBody();
    }
    public FakeStoreProductDto createProduct(final FakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestURL, product, FakeStoreProductDto.class);

        return response.getBody();
    }
    public List<FakeStoreProductDto> getAllProducts() {
        // ResponseEntity<List> response = restTemplate.getForEntity(productRequestURL, List.class); Type erasure
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestURL, FakeStoreProductDto[].class);

        // List<FakeStoreProductDto> fakeStoreProductDtosList = response.getBody();
        List<FakeStoreProductDto> genericProductList = new ArrayList<>();
        for(FakeStoreProductDto productDto : response.getBody()){
            genericProductList.add(productDto);
        }

        return genericProductList;
    }
    public FakeStoreProductDto deleteProductById(final Long id) throws NotFoundException{
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productIdRequestURL, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
        if(response.getBody() == null){
            throw new NotFoundException(String.format("Product %s not found for delete", id));
        }
        return response.getBody();
    }

    public FakeStoreProductDto updateProductById(final Long id, FakeStoreProductDto updatedProduct) {
        FakeStoreProductDto existingProduct = new FakeStoreProductDto();
        existingProduct.setId(id);
        existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setTitle(updatedProduct.getTitle());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productIdRequestURL, HttpMethod.PUT, new HttpEntity<>(existingProduct), FakeStoreProductDto.class, id);

        return response.getBody();
    }

}
