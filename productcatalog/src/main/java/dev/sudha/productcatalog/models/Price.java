package dev.sudha.productcatalog.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseModel {
    private String currency;
    private double price;
    @OneToOne
    private Product product;

    public Price(double price){
        this.price = price;
        this.currency = "INR";
    }

}
