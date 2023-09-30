package dev.isudha.productcatalog.inheritancedemo.mappedSuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="zms_student")
public class Student extends User{
    private double psp;
    private double attendance;

}
