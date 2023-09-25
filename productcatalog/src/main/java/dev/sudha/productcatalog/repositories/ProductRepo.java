package dev.sudha.productcatalog.repositories;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import dev.sudha.productcatalog.models.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Product getProductById(Long id);

    @Query(value = "select * from Product", nativeQuery = true)
    List<Product> getAllProducts();

    Product deleteProductById(Long id);

    Product findByTitleEquals(String title);

    Product findByTitleEqualsAndPrice_Price(String title, double price);

    // native query in separate file
    @Query(value = CustomQueries.FIND_ALL_BY_PRICE_CURRENCY, nativeQuery = true)
    List<Product> findAllByPrice_Currency(String currency);

    // native query
    @Query(value = "select * from Product where category = :category", nativeQuery = true)
    Product findByCategory(String category);

    // HQL - Hibernate query language
    //    @Query("select Product from Product where Product.title= :title")
    //    Product getProductByTitle(String title);
    /*
        count
        distinct
        order by
        like
     */
}
