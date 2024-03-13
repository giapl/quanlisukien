package org.example.quanlisukien.repository;

import java.util.Optional;
import org.example.quanlisukien.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  @Query(value = "select * from account where username = :username", nativeQuery = true)
  Optional<Account> findByUsername(
      @Param("username") String username); //lay du lieu ra dk la username

  Boolean existsByUsername(String username); //kiem tra username da ton tai hay ch

  Boolean existsByEmail(String email); //kiem tra email da ton tai hay ch

  @Query(value = "select * from account where user_id =:user_id", nativeQuery = true)
  Optional<Account> findByUserId(@Param("user_id") Long userId); //lay ra theo dk user_id

}
