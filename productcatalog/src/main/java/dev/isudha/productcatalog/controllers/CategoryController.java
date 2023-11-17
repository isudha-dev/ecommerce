package dev.isudha.productcatalog.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import dev.isudha.productcatalog.dtos.GetCategoryDto;
import dev.isudha.productcatalog.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<GetCategoryDto> createCategory(@RequestBody String name){
        ResponseEntity<GetCategoryDto> response = new ResponseEntity<>(categoryService.createCategory(name), HttpStatus.CREATED);
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCategoryDto> findById(@PathVariable("id") Long id){
        GetCategoryDto categoryDto = categoryService.findById(id);
        ResponseEntity<GetCategoryDto> response = new ResponseEntity<>(categoryDto, HttpStatus.OK);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<GetCategoryDto>> findAllCategories(){
        ResponseEntity<List<GetCategoryDto>> response = new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
            return response;
    }







}
