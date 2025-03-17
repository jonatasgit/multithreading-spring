package com.example.multithreading.repository;

import com.example.multithreading.model.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByIdAndRoomId(Long id, Long roomId);

    //PESSIMISTIC LOCKING, THE SECOND USER CANNOT EVEN GET DIRT READING, BECAUSE DE ROW IS LOCKED
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s WHERE s.id = :seatId AND s.room.id = :roomId")
    Optional<Seat> findByIdAndRoomIdWithLock(@Param("seatId") Long seatId, @Param("roomId") Long roomId);
}
