package dev.isudha.productcatalog.models;

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
public class Price extends BaseModel {
    private String currency;
    private double amount;
//    @OneToOne
//    private Product product;

//    public Price(String currency, double amount){
//        this.amount = amount;
//        this.currency = currency;
//    }

}
