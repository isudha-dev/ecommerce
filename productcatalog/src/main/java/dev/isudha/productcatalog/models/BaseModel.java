package dev.isudha.productcatalog.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //    @GeneratedValue(generator = "uuidgenerator")
    //    @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
    //    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private Long id;

}
