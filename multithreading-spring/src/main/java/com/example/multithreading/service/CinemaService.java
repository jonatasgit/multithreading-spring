package com.example.multithreading.service;

import com.example.multithreading.model.Seat;
import com.example.multithreading.repository.SeatRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CinemaService {

    private final SeatRepository seatRepository;
    @Autowired
    private EntityManager entityManager;

    public CinemaService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    @Transactional
    public Seat book(Long roomId, Long seatId, String personName) {
        //final Seat seat = getSeat(roomId, seatId);
        System.out.println("Trying to get seat for "+ personName);
        final Seat seat = getSeatWithLock(roomId, seatId);
        if (seat == null) {
            return null;
        }
        System.out.println("Got seat for "+ personName);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        saveBookedSeat(personName, seat);

        return seat;
    }
    private Seat getSeatWithLock(Long roomId, Long seatId) {
        final Seat seat = seatRepository.findByIdAndRoomIdWithLock(seatId, roomId)
                .orElseThrow(() -> new EntityNotFoundException("Seat not found on DB"));

        if (Boolean.FALSE.equals(seat.isAssigned())) {
            System.out.println("Assigning a Seat: {}" + Thread.currentThread().getName());

            return seat;
        }
        System.out.println("Seat already assigned");
        return null;
    }

    //@Transactional(isolation = Isolation.READ_COMMITTED)
    private void saveBookedSeat(String personName, Seat seat) {
        seat.setAssigned(true);
        seat.setPerson(personName);
        try {
            seatRepository.save(seat);
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("Row was updated or deleted by another transaction");
        }
    }

    //@Transactional(isolation = Isolation.READ_COMMITTED)
    private Seat getSeat(Long roomId, Long seatId) {
        final Seat seat = seatRepository.findByIdAndRoomId(seatId, roomId)
                .orElseThrow(() -> new EntityNotFoundException("Seat not found on DB"));

        if (Boolean.FALSE.equals(seat.isAssigned())) {
            System.out.println("Assigning a Seat: {}" + Thread.currentThread().getName());

            return seat;
        }
        System.out.println("Seat already assigned");
        return null;
    }

    public Seat getSeat(Long seatId) {
        return seatRepository.findById(seatId).orElseThrow(() -> new EntityNotFoundException("Seat not found on DB"));
    }
}
