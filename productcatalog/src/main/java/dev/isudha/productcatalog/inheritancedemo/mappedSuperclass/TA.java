package dev.isudha.productcatalog.inheritancedemo.mappedSuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="zms_ta")
public class TA extends User{
    private double averageRating;


}
