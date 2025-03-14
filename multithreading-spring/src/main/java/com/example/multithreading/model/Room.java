package com.example.multithreading.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    public Room() {
        this.seats = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;

    public List<Seat> getSeatList() {
        return seats;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seats = seatList;
    }

    public void addSeat(final Seat seat){
        this.seats.add(seat);
        seat.setRoom(this);
    }

}
