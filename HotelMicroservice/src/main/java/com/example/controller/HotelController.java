package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Hotel;
import com.example.domain.Rating;
import com.example.service.IHotelService;

import jakarta.websocket.server.PathParam;

@RestController
public class HotelController {
	
	@Autowired IHotelService hotelService;
	
	@PostMapping("api/hotel/createHotel")
	public String createHotel(@RequestBody Hotel h) {
		
		return hotelService.createHotel(h);
		
	}
	
	@PostMapping("api/hotel/addReview/{hotelId}")
	public Rating addReview(@PathVariable int hotelId, @RequestBody Rating r) throws Exception {
		return hotelService.addAReview(hotelId, r);
		
	}
	
	@GetMapping("api/hotel/getHotel/{searchText}")
	public List<Hotel> getHotel( @PathVariable String searchText) {
		return hotelService.getHotelBySearchTerms(searchText);
		
	}
	
	@GetMapping("api/hotel/getHotelById/{hotelId}")
	public Hotel getHotelById( @PathVariable int hotelId) {
		return hotelService.getHotelById(hotelId);
		
	}
	
	@GetMapping("api/hotel/getRoomByType/{type}")
	public Hotel getRoomByType( @PathVariable String type) {
		return hotelService.getRoomByType(type);
		
	}
	
}
