package org.example.quanlisukien.repository;

import org.example.quanlisukien.data.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long> , JpaSpecificationExecutor<Events> {

  Page<Events> findAll(Pageable pageable);

}
