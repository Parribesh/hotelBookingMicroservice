package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.BookingClient;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
public class BookingController {
	@Autowired BookingClient bookingClient;
	
	@PostMapping("/api/createBooking")
	public Object createBoooking(@RequestBody JsonNode json) {
		return bookingClient.saveBooking(json);
	}
	
	@PostMapping("/api/getAvailableRooms")
	public Object getAvailableRooms(@RequestBody JsonNode json) {
		return bookingClient.getAvailabelRooms(json);
	}
	
	@GetMapping("/api/getMyBookings/{phone}")
	public Object getMyBookings(@PathVariable String phone) {
		return bookingClient.getBookingByPhone(phone);
	}
	
	@GetMapping("/api/cancelBookingById/{bookingId}")
	public Object cancelBookingById(@PathVariable int bookingId) {
		 
		 return bookingClient.cancelBookingById(bookingId);
	}
	
	@GetMapping("/api/getMyBookingsByEmail/{email}")
	public Object getMyBookingsByEmail(@PathVariable String email) {
		return bookingClient.getBookingByEmail(email);
	}
	
	
	
}
