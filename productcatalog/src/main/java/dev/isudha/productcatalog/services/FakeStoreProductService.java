package dev.isudha.productcatalog.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import dev.isudha.productcatalog.exceptions.NotFoundException;
import dev.isudha.productcatalog.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.isudha.productcatalog.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import dev.isudha.productcatalog.dtos.GenericProductDto;
import lombok.Getter;

@Primary
@Service("FakeStoreProductService")
@Getter
public class FakeStoreProductService {// implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

//    @Override
    public GenericProductDto getProductById(final Long id) throws NotFoundException {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }
//    @Override
    public GenericProductDto createProduct(final GenericProductDto product) {
        FakeStoreProductDto createFakeStoreProductDto = convertGenericProductDtoToFakeStoreProductDto(product);
        FakeStoreProductDto createdProduct = fakeStoreProductServiceClient.createProduct(createFakeStoreProductDto);
        return convertFakeStoreProductDtoToGenericProductDto(createdProduct);
    }
//    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtoList =  fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtoList){
            genericProductDtoList.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtoList;
    }
//    @Override
    public GenericProductDto deleteProductById(final Long id) throws NotFoundException{
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }
//    @Override
    public GenericProductDto updateProductById(final Long id, GenericProductDto product) {
        FakeStoreProductDto updateFakeStoreProductDto = convertGenericProductDtoToFakeStoreProductDto(product);
        FakeStoreProductDto updatedProduct = fakeStoreProductServiceClient.updateProductById(id, updateFakeStoreProductDto);
        return convertFakeStoreProductDtoToGenericProductDto(updatedProduct);
    }

    private FakeStoreProductDto convertGenericProductDtoToFakeStoreProductDto(GenericProductDto genericProductDto){
        FakeStoreProductDto product = new FakeStoreProductDto();
        product.setId(genericProductDto.getId());
        product.setImage(genericProductDto.getImageUrl());
        product.setCategory(genericProductDto.getCategory());
        product.setPrice(genericProductDto.getPrice());
        product.setDescription(genericProductDto.getDescription());
        return product;
    }

    private GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        return product;
    }

}
