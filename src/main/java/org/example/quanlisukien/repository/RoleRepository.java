package org.example.quanlisukien.repository;

import java.util.Optional;
import org.example.quanlisukien.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query(value = "select * from role where role_name =:role_name", nativeQuery = true)
  Optional<Role> getByRoleName(@Param("role_name") String RoleName); //lay ra theo dk role_name
}
