package dev.isudha.productcatalog.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.isudha.productcatalog.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Override Optional<Category> findById(Long aLong);
    @Override List<Category> findAll();
}
