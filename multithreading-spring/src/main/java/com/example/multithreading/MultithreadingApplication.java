package com.example.multithreading;

import com.example.multithreading.model.Room;
import com.example.multithreading.model.Seat;
import com.example.multithreading.repository.RoomRepository;
import com.example.multithreading.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MultithreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultithreadingApplication.class, args);
	}

	@Bean
	CommandLineRunner enrichRooms(final RoomRepository roomRepository, final SeatRepository seatRepository){
		return args -> {
			Seat seat1 = new Seat();
			Seat seat2 = new Seat();
			Room room1 = new Room();
			List<Seat> seatList = new ArrayList<>();
			seatList.add(seat1);
			seatList.add(seat2);

			room1.setSeatList(seatList);

			roomRepository.save(room1);
			seatRepository.save(seat1);

		};
	}
}
