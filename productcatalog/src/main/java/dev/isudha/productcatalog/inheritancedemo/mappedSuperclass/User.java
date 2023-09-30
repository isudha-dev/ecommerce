package dev.isudha.productcatalog.inheritancedemo.mappedSuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass

public abstract class User {
    private String name;
    private String email;

    @Id
    private int id;
}
