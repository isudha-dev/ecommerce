package dev.sudha.productcatalog.inheritancedemo.singleTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_mr")
public interface MentorRepo extends JpaRepository<Mentor, Long> {
    @Override <S extends Mentor> S save(S entity);
}
