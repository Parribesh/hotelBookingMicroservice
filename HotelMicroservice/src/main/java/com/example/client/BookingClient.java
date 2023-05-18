package com.example.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BookingClient {
	
	public JsonNode getBookingById(int bookingId) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/api/booking/getBooking/" + bookingId, Object.class);
		Object object = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.convertValue(object, JsonNode.class);
		return json;
	}
}
