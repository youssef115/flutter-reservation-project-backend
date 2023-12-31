package com.youssef.flutterProjectBackend.repository;

import com.youssef.flutterProjectBackend.enties.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Optional<List<Reservation>> findByUserReservationEmailOrToolReservationTitle(String userEmail, Optional<String> toolName);
}
