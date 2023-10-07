package dev.isudha.productcatalog.controller;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.isudha.productcatalog.controllers.ProductController;
import dev.isudha.productcatalog.dtos.CreateProductDto;
import dev.isudha.productcatalog.dtos.GetPriceDto;
import dev.isudha.productcatalog.dtos.GetProductDto;
import dev.isudha.productcatalog.exceptions.NotFoundException;
import dev.isudha.productcatalog.models.Price;
import dev.isudha.productcatalog.services.ProductService;

@WebMvcTest(ProductController.class)
// only initialise services/ controller that are required in test
// @WebMvcTest(ProductController.class) only initialises ProductController dependency and specific dependency mentioned in test
public class ProductControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProductReturnsEmptyListWhenNotProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
            .andExpect(status().is(200))
            .andExpect(content().string("[]"));
    }


    @Test
    void createProductShouldCreateNewProduct() throws Exception {
        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setTitle("Bottle");
        createProductDto.setDescription("Water bottle");
        createProductDto.setImageUrl("http://products");
        createProductDto.setCategoryId(1l);
        createProductDto.setPrice(new Price("INR", 4567.8));

        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setId(100l);
        getProductDto.setTitle("Bottle");
        getProductDto.setDescription("Water bottle");
        getProductDto.setImageUrl("http://products");
        getProductDto.setCategoryId(1l);
        getProductDto.setPrice(new GetPriceDto("INR", 4567.8));

        when(productService.createProduct(any())).thenReturn(getProductDto);

        mockMvc.perform(post("/products")
                .content(objectMapper.writeValueAsString(createProductDto))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            content().string(objectMapper.writeValueAsString(getProductDto))
        )
        .andExpect(status().is(201));
//            .andExpect(jsonPath("$.student.name",is(""))) // this is not working
//        .andExpect(jsonPath("$.length()",is(2)));
    }

    /*
        {
            student: {
                name: "",
                email: ""
            }
        }
     */
}
