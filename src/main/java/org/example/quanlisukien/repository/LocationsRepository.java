package org.example.quanlisukien.repository;

import org.example.quanlisukien.data.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepository extends JpaRepository<Locations , Long> {

}
