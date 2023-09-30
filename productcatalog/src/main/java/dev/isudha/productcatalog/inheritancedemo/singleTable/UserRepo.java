package dev.isudha.productcatalog.inheritancedemo.singleTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_ur")
public interface UserRepo extends JpaRepository<User, Long> {

    @Override <S extends User> S save(S entity);
}
