package com.example.multithreading.controller;

import com.example.multithreading.model.Seat;
import com.example.multithreading.service.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/{roomId}/{seatId}/{personName}")
    public ResponseEntity<Seat> bookASeat(@PathVariable Long roomId,
                                          @PathVariable Long seatId,
                                          @PathVariable String personName){
        final Seat bookedSeat = cinemaService.book(roomId, seatId, personName);

        return ResponseEntity.ok(bookedSeat);
    }
}
