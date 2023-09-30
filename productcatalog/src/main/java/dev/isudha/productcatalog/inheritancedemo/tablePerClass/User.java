package dev.isudha.productcatalog.inheritancedemo.tablePerClass;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ztpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// has a differentiator column which indicate which kind of user is
// represented by row
public class User {
    private String name;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Override public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", id=" + id +
            '}';
    }
}
