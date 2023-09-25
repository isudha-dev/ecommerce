package dev.sudha.productcatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.sudha.productcatalog.models.Price;

public interface PriceRepo extends JpaRepository<Price, Long> {



}
