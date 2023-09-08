package dev.sudha.productcatalog.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.sudha.productcatalog.dtos.ExceptionDto;
import dev.sudha.productcatalog.dtos.FakeStoreProductDto;
import dev.sudha.productcatalog.dtos.GenericProductDto;
import dev.sudha.productcatalog.exceptions.NotFoundException;
import dev.sudha.productcatalog.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    //    Field injection: This kind of dependency injection is not recommended.
    //    @Autowired
    //    @Value("${productservice.type}")
    private ProductService productService;

    // controller injecting
    // may not work in case of cyclic dependency
    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    // Setter injection: This is also not recommended
    /*
    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }
    */

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        // modifying response entity by adding specific status code. you can also add headers here.
        ResponseEntity<GenericProductDto> response = new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.NOT_FOUND);
        return response;
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody FakeStoreProductDto product){
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody FakeStoreProductDto product){
        return productService.updateProductById(id, product);
    }

}
