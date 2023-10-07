package dev.isudha.productcatalog.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import dev.isudha.productcatalog.dtos.GetCategoryDto;
import dev.isudha.productcatalog.dtos.GetPriceDto;
import dev.isudha.productcatalog.dtos.GetProductDto;
import dev.isudha.productcatalog.models.Category;
import dev.isudha.productcatalog.models.Price;
import dev.isudha.productcatalog.models.Product;
import dev.isudha.productcatalog.repositories.CategoryRepo;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    @Override public GetCategoryDto createCategory(final String name) {
        Category category = new Category(name);
        categoryRepo.save(category);
        return convertCategoryToCategoryDto(category);
    }
    @Override
    public GetCategoryDto findById(final Long id) {
        Category category = categoryRepo.findById(id).get();
        return convertCategoryToCategoryDto(category);
    }

    @Override public List<GetCategoryDto> findAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<GetCategoryDto> categoryDtos = new ArrayList<>();
        for(Category category: categories){
            GetCategoryDto categoryDto = convertCategoryToCategoryDto(category);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    private GetCategoryDto convertCategoryToCategoryDto(Category category){
        GetCategoryDto categoryDto = new GetCategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        List<Product> products = category.getProducts();
        List<GetProductDto> productDtos = new ArrayList<>();
        if(products == null){
            return categoryDto;
        }
        for(Product product: products){
            GetProductDto productDto = new GetProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setImageUrl(product.getImageUrl());
            productDto.setCategoryId(product.getCategory().getId());

            GetPriceDto priceDto = new GetPriceDto(product.getPrice().getCurrency(), product.getPrice().getAmount());
            productDto.setPrice(priceDto);
            productDtos.add(productDto);
        }

        categoryDto.setProducts(productDtos);
        return categoryDto;
    }
}
