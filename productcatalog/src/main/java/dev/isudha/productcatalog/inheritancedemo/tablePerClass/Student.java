package dev.isudha.productcatalog.inheritancedemo.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ztpc_student")
public class Student extends User {
    private double psp;
    private double attendance;

}
