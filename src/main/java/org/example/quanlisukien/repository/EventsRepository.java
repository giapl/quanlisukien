package org.example.quanlisukien.repository;

import org.example.quanlisukien.data.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long> , JpaSpecificationExecutor<Events> {

  Page<Events> findAll(Pageable pageable);

  @Query(value = "select * from events where name_event = :name_event", nativeQuery = true)
  Page<Events> findByNameEvent(@Param("name_event") String name_event, Pageable pageable);

  @Query(value = "select events.* from events "
      + "inner join categories on events.category_id = categories.category_id "
      + "where categories.name = :name", nativeQuery = true)
  Page<Events> findAllByCategories(@Param("name") String name_category, Pageable pageable);
}
