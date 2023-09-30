package dev.isudha.productcatalog.inheritancedemo.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="zst_ta")
@DiscriminatorValue(value = "3")
public class TA extends User {
    private double averageRating;


}
