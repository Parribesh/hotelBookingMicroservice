package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.HotelClient;
import com.fasterxml.jackson.databind.JsonNode;



@Controller
public class ConsumerController {
	
	@Autowired HotelClient hotelClient;
	
	@PostMapping("/create")
	public String createHotel(@RequestBody JsonNode json) {
		return hotelClient.saveHotel(json);
		
	}
	
	@PostMapping("/hotel/addReview/{hotelId}")
	@ResponseBody
	public String addHotelReview(@RequestBody JsonNode json, @PathVariable int hotelId) {
		Object obj = hotelClient.addReview(json, hotelId);
		System.out.println("review Obj" + obj);
		if(obj != null) return "success";
		
		return "error";
	}
	
	@GetMapping("/getHotels/{text}")
	@ResponseBody
	public Object getHotels(@PathVariable String text) {
		return hotelClient.getHotelList(text);
		
	}
	
	@GetMapping("/getHotelById/{hotelId}")
	@ResponseBody
	public Object getHotelById(@PathVariable int hotelId) {
		return hotelClient.getHotelById(hotelId);
		
	}
	
	@GetMapping("/getRoomByType/{type}")
	@ResponseBody
	public Object getRoomDetailsByTypeName(@PathVariable String type) {
		return hotelClient.getRoomByType(type);
		
	}
	
	@GetMapping("/profile")
	public Object getProfile() {
		return "userProfile";
		
	}
	
	@GetMapping({"/home", "/"})
	public Object getHome() {
		return "Home";
		
	}

}
