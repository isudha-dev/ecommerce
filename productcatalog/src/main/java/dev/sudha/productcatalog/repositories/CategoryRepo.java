package dev.sudha.productcatalog.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.sudha.productcatalog.models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {


}
