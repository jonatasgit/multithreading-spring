package com.example.multithreading.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private List<Seat> seats;

    public List<Seat> getSeatList() {
        return seats;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seats = seatList;
    }
}
