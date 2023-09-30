package dev.isudha.productcatalog.inheritancedemo.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="zjt_ta")
@PrimaryKeyJoinColumn(name="user_id")
public class TA extends User {
    private double averageRating;


}
