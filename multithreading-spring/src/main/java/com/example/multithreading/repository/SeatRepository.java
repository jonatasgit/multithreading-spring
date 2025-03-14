package com.example.multithreading.repository;

import com.example.multithreading.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByIdAndRoomId(Long id, Long roomId);
}
