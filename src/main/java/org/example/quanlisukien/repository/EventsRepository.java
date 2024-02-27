package org.example.quanlisukien.repository;

import java.util.List;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.response.EventsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Events , Long> {

@Query("SELECT new org.example.quanlisukien.data.response.EventsResponse("
    + "events.event_id, events.name_event, events.description, events.event_image, "
    + "categories.name, locations.name, locations.address, locations.description, "
    + "events.start_time, events.end_time) "
    + "FROM Events events "
    + "LEFT JOIN events.categories categories "
    + "LEFT JOIN events.locations locations "
    + "WHERE categories.name = :name")
  List<EventsResponse>findByCategories(@Param("name") String name_category);

}
