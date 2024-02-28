package org.example.quanlisukien.repository;

import java.util.List;
import org.example.quanlisukien.data.entity.Feedbacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedbacks, Long> {

  @Query(value = "select * from feedbacks where event_id = :event_id",nativeQuery = true)
  List<Feedbacks> findByEventsEvent_id(@Param("event_id") Long id);
}
