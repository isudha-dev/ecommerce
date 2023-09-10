package dev.sudha.productcatalog.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import dev.sudha.productcatalog.dtos.GenericProductDto;
import dev.sudha.productcatalog.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.sudha.productcatalog.exceptions.NotFoundException;
import dev.sudha.productcatalog.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;

@Primary
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override public GenericProductDto getProductById(final Long id) throws NotFoundException {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }
    @Override public GenericProductDto createProduct(final FakeStoreProductDto product) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }
    @Override public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtoList =  fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtoList){
            genericProductDtoList.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtoList;
    }
    @Override public GenericProductDto deleteProductById(final Long id) throws NotFoundException{
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }
    @Override
    public GenericProductDto updateProductById(final Long id, FakeStoreProductDto updatedProduct) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id, updatedProduct));
    }

    private GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        return product;
    }

}
