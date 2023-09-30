package dev.isudha.productcatalog.inheritancedemo.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="zst_student")
@DiscriminatorValue(value = "1")
public class Student extends User {
    private double psp;
    private double attendance;

}
