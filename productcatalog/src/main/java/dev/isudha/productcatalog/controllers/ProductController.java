package dev.isudha.productcatalog.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.isudha.productcatalog.dtos.CreateProductDto;
import dev.isudha.productcatalog.dtos.GetProductDto;
import dev.isudha.productcatalog.exceptions.NotFoundException;
import dev.isudha.productcatalog.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    /*
        Field injection: This kind of dependency injection is not recommended.
        @Autowired
        @Value("${productservice.type}")
    */
    private ProductService productService;

    /*
        controller injecting
        may not work in case of cyclic dependency

        @Qualifier("FakeStoreProductService") to replace @primary
        @primary is used to mark a bean/object as default
        @Qualifier allows you to specially mark a particular object you need
        if both mentioned, @Qualifier is used
    */
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /*
    Setter injection: This is also not recommended
    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }
    */

    @GetMapping
    public ResponseEntity<List<GetProductDto>> getAllProducts(){
        ResponseEntity<List<GetProductDto>> response = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProductDto> getProductById(@PathVariable("id") Long id) {
        ResponseEntity<GetProductDto> response = new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id){
        // modifying response entity by adding specific status code. you can also add headers here.
        productService.deleteProductById(id);
        ResponseEntity<String> response = new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity<GetProductDto> createProduct(@RequestBody CreateProductDto product){
        ResponseEntity<GetProductDto> response = new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity<GetProductDto> updateProductById(@PathVariable("id") Long id, @RequestBody CreateProductDto product){
        ResponseEntity<GetProductDto> response = new ResponseEntity<>(productService.updateProductById(id, product), HttpStatus.ACCEPTED);
        return response;
    }

    @GetMapping("/category/{id}")
    private ResponseEntity<List<GetProductDto>> getProductByCategoryId(@PathVariable("id") Long id){
        ResponseEntity<List<GetProductDto>> response = new ResponseEntity<>(productService.getProductsByCategoryId(id), HttpStatus.OK);
        return response;
    }

}
