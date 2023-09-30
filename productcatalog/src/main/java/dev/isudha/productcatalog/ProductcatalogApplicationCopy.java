package dev.isudha.productcatalog;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import dev.isudha.productcatalog.models.Category;
import dev.isudha.productcatalog.models.Price;
import dev.isudha.productcatalog.models.Product;
import dev.isudha.productcatalog.repositories.PriceRepo;
import dev.isudha.productcatalog.repositories.CategoryRepo;
import dev.isudha.productcatalog.repositories.ProductRepo;

//@SpringBootApplication
public class ProductcatalogApplicationCopy implements CommandLineRunner {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private final PriceRepo priceRepo;
    public ProductcatalogApplicationCopy(ProductRepo productRepo, CategoryRepo categoryRepo,
        final PriceRepo priceRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.priceRepo = priceRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductcatalogApplicationCopy.class, args);
    }

    @Override public void run(final String... args) throws Exception {
        Category category = new Category();
        category.setName("Iphone");
//        categoryRepo.save(category);

        Price price = new Price();
        price.setCurrency("INR");
        price.setAmount(11.5);
//        priceRepo.save(price);

        Product product = new Product();
        product.setTitle("Iphone 15 pro");
        product.setDescription("Best iPhone");
        product.setCategory(category);
        product.setPrice(price);
        productRepo.save(product);

        // find a category by UUID
        // Category category1 = categoryRepo.findById(UUID.fromString("38922561-033c-4ef0-94b4-434d84b0c56d")).get();
        // System.out.println("Category name is: " + category1.getName());
        System.out.println("Printing all products in the category");

        // delete a product by UUID -> also deletes corresponding price table entries
        // productRepo.deleteById(UUID.fromString(""));

        List<Product> productList = productRepo.findAllByPrice_Currency("INR");
        System.out.println(productList);
    }
}
