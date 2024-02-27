package org.example.quanlisukien.repository;

import org.example.quanlisukien.data.entity.Feedbacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedbacks, Long> {

}
