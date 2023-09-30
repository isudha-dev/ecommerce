package dev.isudha.productcatalog.inheritancedemo.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="zjt_student")
@PrimaryKeyJoinColumn(name="user_id")
public class Student extends User {
    private double psp;
    private double attendance;

}
