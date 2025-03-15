package com.example.multithreading.service;

import com.example.multithreading.model.Seat;
import com.example.multithreading.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private final SeatRepository seatRepository;

    public CinemaService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat book(Long roomId, Long seatId, String personName) {
        final Seat seat = seatRepository.findByIdAndRoomId(roomId, seatId)
                .orElseThrow(() -> new EntityNotFoundException("Seat not found on DB"));

        if (Boolean.FALSE.equals(seat.isAssigned())) {
            seat.setAssigned(true);
            seat.setPerson(personName);

            seatRepository.save(seat);
        }

        return seat;
    }
}
