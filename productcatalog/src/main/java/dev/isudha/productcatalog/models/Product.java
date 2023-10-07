package dev.isudha.productcatalog.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Getter
public class Product extends BaseModel {
    private String title;
    private String description;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    @Fetch(FetchMode.JOIN)
    private Price price;
    private int inventoryCount;

}
