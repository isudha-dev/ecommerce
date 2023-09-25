package dev.sudha.productcatalog.inheritancedemo.tablePerClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_mr")
public interface MentorRepo extends JpaRepository<Mentor, Long> {
    @Override <S extends Mentor> S save(S entity);
}
