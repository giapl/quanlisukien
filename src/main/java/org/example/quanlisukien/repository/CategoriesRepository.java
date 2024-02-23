package org.example.quanlisukien.repository;

import java.util.Optional;
import org.example.quanlisukien.data.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {

  @Query(value = "select * from categories where name = :name",nativeQuery = true)
  Optional<Categories> findByName(@Param("name") String name); //lay ra theo dk name
}
