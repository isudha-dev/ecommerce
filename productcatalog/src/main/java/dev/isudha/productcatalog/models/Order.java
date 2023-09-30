package dev.isudha.productcatalog.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseModel {
    private String someStr;
    @ManyToMany
    @JoinTable(
        name="products_order",
        joinColumns = @JoinColumn(name="order_id"),
        inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> products;


}
