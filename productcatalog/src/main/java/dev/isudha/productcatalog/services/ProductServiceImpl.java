package dev.isudha.productcatalog.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import dev.isudha.productcatalog.dtos.CreateProductDto;
import dev.isudha.productcatalog.dtos.GetPriceDto;
import dev.isudha.productcatalog.models.Category;
import dev.isudha.productcatalog.models.Price;
import dev.isudha.productcatalog.models.Product;
import dev.isudha.productcatalog.repositories.CategoryRepo;
import dev.isudha.productcatalog.repositories.ProductRepo;
import dev.isudha.productcatalog.dtos.GetProductDto;

@Service("ProductService")
public class ProductServiceImpl implements ProductService{

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<GetProductDto> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        List<GetProductDto> genericProductList = new ArrayList<>();
        for(Product product: allProducts){
            genericProductList.add(convertProductToProductDto(product));
        }
        return genericProductList;
    }

    @Override
    public GetProductDto getProductById(Long id) {
        Product product = productRepo.findById(id).get();
        return convertProductToProductDto(product);
    }
    @Override
    public GetProductDto createProduct(CreateProductDto product) {
        Product productToCreate = new Product();
        productToCreate = convertProductDtoToProductDto(productToCreate, product);
        Product savedProduct = productRepo.save(productToCreate);
        return convertProductToProductDto(savedProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }
    @Override
    public GetProductDto updateProductById(final Long id, CreateProductDto productDto) {
        Product productToUpdate = productRepo.findById(id).get();
        productToUpdate = convertProductDtoToProductDto(productToUpdate, productDto);
        Product updatedProduct = productRepo.save(productToUpdate);
        return convertProductToProductDto(updatedProduct);
    }
    @Override
    public List<GetProductDto> getProductsByCategoryId(final Long id) {
        List<Product> productList = productRepo.findAllByCategory_Id(id);
        List<GetProductDto> genericProductList = new ArrayList<>();
        for(Product product: productList){
            genericProductList.add(convertProductToProductDto(product));
        }
        return genericProductList;
    }

    private GetProductDto convertProductToProductDto(Product product){
        GetProductDto productDto = new GetProductDto();

        productDto.setId(product.getId());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategoryId(product.getCategory().getId());

        GetPriceDto priceDto = new GetPriceDto(product.getPrice().getCurrency(), product.getPrice().getAmount());
        productDto.setPrice(priceDto);

        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getTitle());
        return productDto;
    }

    private Product convertProductDtoToProductDto(Product product, CreateProductDto productDto){
        Category category = categoryRepo.findById(productDto.getCategoryId()).get();

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());

        return product;
    }


}
