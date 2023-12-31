package dev.isudha.productcatalog.repositories;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dev.isudha.productcatalog.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Product getProductById(Long id);

    @Override
    List<Product> findAll();

    @Query(value = "select p from Product p right join p.price pr where p.price.id = pr.id")
//    @Query(value = "select p.id as productId, p.title, p.description, p.image_url, pr.id as priceId, pr.currency, pr.amount from product p left join price pr on p.price_id = pr.id", nativeQuery = true)
    List<Product> findAllProducts();

    @Override
    void deleteById(Long id);
    Product findByTitleEquals(String title);

    Product findByTitleEqualsAndPrice_Amount(String title, double amount);

    // native query in separate file
    @Query(value = CustomQueries.FIND_ALL_BY_PRICE_CURRENCY, nativeQuery = true)
    List<Product> findAllByPrice_Currency(String currency);

    // native query
    @Query(value = "select * from Product where category = :category", nativeQuery = true)
    Product findByCategory(String category);
    List<Product> findAllByCategory_Id(Long id);

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
