package com.booking.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.domain.Booking;
import com.booking.service.BookingService;
import com.booking.service.InvoiceService;
import com.example.model.AvailableRooms;

@RestController
public class BookingController {
	
	@Autowired BookingService bookingService;
	@Autowired InvoiceService invoiceService;
	
	@PostMapping(value = "api/booking/createBooking", produces = MediaType.APPLICATION_JSON_VALUE)
	public Booking createBooking(@RequestBody Booking b) {
		Booking book = bookingService.createBooking(b);
		Object hotel = bookingService.getHotelById(b.getHotelId());
		try {
			invoiceService.createInvoice(book, hotel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	@GetMapping(("api/booking/getBooking/{bookingId}"))
	public Booking getBooking(@PathVariable int bookingId) {
		Booking b = bookingService.getBookingById(bookingId);
		return bookingService.updateStatus(b);
	}
	
	@PostMapping(("api/booking/getBookingByDate"))
	public int getAvailableRooms(@RequestBody AvailableRooms a ) {
		
		List<Booking> todaysBooking = bookingService.findBookingByCheckInDateAndHotelIdAndHotelRoomId(a.getCheckIn(), a.getHotelId(), a.getHotelRoomId());
		int totalRoomsBookedForThisHotelAndId = todaysBooking.stream().mapToInt(e -> e.getNoRooms()).sum();
		return totalRoomsBookedForThisHotelAndId;
	}
	
	@GetMapping(("api/booking/getAllBookings"))
	public List<Booking> findAllBooking() {
		List<Booking> bookings = bookingService.findAll();
		bookings.stream().map(e -> bookingService.updateStatus(e)).toList();
		return bookings ;
	}
	
	@GetMapping(("api/booking/getBookingByPhone/{phoneNumber}"))
	public List<Booking> getBookingByPhone(@PathVariable String phoneNumber) {
		List<Booking> bookings = bookingService.getBookingByPhone(phoneNumber);
		bookings.stream().map(e -> bookingService.updateStatus(e)).toList();
		return bookings;
	}
	
	@GetMapping(("api/booking/getBookingByEmail/{email}"))
	public List<Booking> getBookingByEmail(@PathVariable String email) {
		List<Booking> bookings = bookingService.getBookingByEmail(email);
		bookings.stream().map(e -> bookingService.updateStatus(e)).toList();
		return bookings;
	}
	
	@GetMapping(("api/booking/cancelBookingById/{bookingId}"))
	public String cancelBookingById(@PathVariable int bookingId) {
		
		return bookingService.cancelBookingById(bookingId);
	}
	
}
