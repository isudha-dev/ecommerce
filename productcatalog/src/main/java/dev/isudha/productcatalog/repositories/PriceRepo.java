package dev.isudha.productcatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.isudha.productcatalog.models.Price;

public interface PriceRepo extends JpaRepository<Price, Long> {



}
