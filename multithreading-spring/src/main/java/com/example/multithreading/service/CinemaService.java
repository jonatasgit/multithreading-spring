package com.example.multithreading.service;

import com.example.multithreading.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private final RoomRepository seatRepository;

    public CinemaService(RoomRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void book() {
    }
}
