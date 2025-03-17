package com.example.multithreading;

import com.example.multithreading.model.Room;
import com.example.multithreading.model.Seat;
import com.example.multithreading.repository.RoomRepository;
import com.example.multithreading.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MultithreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultithreadingApplication.class, args);
	}

	@Bean
	CommandLineRunner enrichRooms(final RoomRepository roomRepository, final SeatRepository seatRepository){
		return args -> {
			initDb(roomRepository);

			System.out.println(seatRepository.findByIdAndRoomId(2L, 1L).get().isAssigned());
		};
	}

	private static void initDb(RoomRepository roomRepository) {
		Seat seat1 = new Seat();
		Seat seat2 = new Seat();
		Seat seat3 = new Seat();
		Seat seat4 = new Seat();
		Seat seat5 = new Seat();
		Seat seat6 = new Seat();
		Seat seat7 = new Seat();
		Seat seat8 = new Seat();
		Seat seat9 = new Seat();
		Room room1 = new Room();
		Room room2 = new Room();

		room1.addSeat(seat1);
		room1.addSeat(seat2);
		room1.addSeat(seat3);
		room1.addSeat(seat4);
		room1.addSeat(seat5);
		room1.addSeat(seat6);
		room1.addSeat(seat7);
		room1.addSeat(seat8);
		room2.addSeat(seat9);
		roomRepository.save(room1);
		roomRepository.save(room2);
	}
}
