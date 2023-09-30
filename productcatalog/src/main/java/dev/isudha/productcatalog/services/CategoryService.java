package dev.isudha.productcatalog.services;

import java.util.List;
import org.springframework.stereotype.Service;
import dev.isudha.productcatalog.dtos.GetCategoryDto;
import dev.isudha.productcatalog.models.Category;
import dev.isudha.productcatalog.repositories.CategoryRepo;

@Service
public interface CategoryService {

    GetCategoryDto createCategory(String name);

    GetCategoryDto findById(Long id);

    List<GetCategoryDto> findAllCategories();

}
