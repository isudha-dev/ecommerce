package dev.isudha.productcatalog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import dev.isudha.productcatalog.controllers.ProductController;
import dev.isudha.productcatalog.dtos.GetProductDto;
import dev.isudha.productcatalog.exceptions.NotFoundException;
import dev.isudha.productcatalog.services.ProductService;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    @Autowired
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;


    @Test
    @DisplayName("1+1 equals 2")
    public void testOnePlusOneIsTwo(){
        System.out.println(1+1);

        assertEquals(2, 1+1, "1+1 is not 2");
    }

    @Test
    void throwExceptionWhenProductDoesntExist() throws NotFoundException {
        when(productService.getProductById(121l))
            .thenReturn(null);
        GetProductDto getProductDto;
        try {
            getProductDto = productController.getProductById(121l).getBody();
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        assertNull(getProductDto);
    }

    @Test
    void returnTitleAsAbcWhenProductIDIs1() throws NotFoundException {
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setTitle("Abc");
        when(
            productService.getProductById(1l)
        ).thenReturn(getProductDto);

        GetProductDto getProductDto1;
        try {
            getProductDto1 = productController.getProductById(1l).getBody();
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Abc", getProductDto1.getTitle());
    }

    @Test
    void prodControllerCallsProdServiceWithSameProductId() throws NotFoundException {
        Long id = 101l;
        when(productService.getProductById(id)).thenReturn(new GetProductDto());
        productController.getProductById(id);
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(id, idCaptor.getValue());
    }



}
