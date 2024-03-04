package org.example.quanlisukien.repository;

import org.example.quanlisukien.data.entity.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationsRepository extends JpaRepository<Registrations, Long> {

}
