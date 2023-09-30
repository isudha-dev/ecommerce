package dev.isudha.productcatalog.inheritancedemo.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ztpc_mentor")
public class Mentor extends User {
    private double averageRating;

}
