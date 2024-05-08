package org.example.quanlisukien.repository;

import java.util.List;
import org.example.quanlisukien.data.entity.TokenFcm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenAppRepository extends JpaRepository<TokenFcm, Long> {

  @Query(value = "select token from token_fcm",nativeQuery = true)
  List<String> findByAllToken();
}
