package dev.isudha.productcatalog.inheritancedemo.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zst_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private double averageRating;

}
