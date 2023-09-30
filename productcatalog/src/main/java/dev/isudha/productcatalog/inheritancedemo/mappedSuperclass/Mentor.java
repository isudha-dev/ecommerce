package dev.isudha.productcatalog.inheritancedemo.mappedSuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "zms_mentor")
public class Mentor extends User{
    private double averageRating;

}
