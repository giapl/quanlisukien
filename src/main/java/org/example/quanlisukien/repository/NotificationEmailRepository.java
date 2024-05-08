package org.example.quanlisukien.repository;

import org.example.quanlisukien.data.entity.NotificationsEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationEmailRepository extends JpaRepository<NotificationsEmail, Long> {

}
