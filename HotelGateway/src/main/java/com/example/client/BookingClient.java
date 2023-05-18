package com.example.client;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class BookingClient {
	
	public Object saveBooking(JsonNode json) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);		
			HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/api/booking/createBooking", request, Object.class);
			Object object = responseEntity.getBody();
			return object;
			
		}
	
	public Integer getAvailabelRooms(JsonNode json) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("http://localhost:8484/api/booking/getBookingByDate", request, Integer.class);
		Integer object = responseEntity.getBody();
		return object;
		
	}
	
	public Object getBookingByPhone(String phone) {
		
//		HttpHeaders headers = new HttpHeaders();
	
//		HttpEntity<String> request = new HttpEntity<String>(phone, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/api/booking/getBookingByPhone/" + phone, Object.class);
		Object object = responseEntity.getBody();
		return object;
		
	}
	
	public String cancelBookingById(int bookingId) {
			
	//		HttpHeaders headers = new HttpHeaders();
			System.out.println("called");
	//		HttpEntity<Integer> request = new HttpEntity<Integer>(bookingId, headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8484/api/booking/cancelBookingById/" + bookingId, String.class);
			String object = responseEntity.getBody();
			System.out.println(object);
			return object;
			
		}
	
	public Object getBookingByEmail(String email) {
			
	//		HttpHeaders headers = new HttpHeaders();
		
	//		HttpEntity<String> request = new HttpEntity<String>(phone, headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/api/booking/getBookingByEmail/" + email, Object.class);
			Object object = responseEntity.getBody();
			return object;
			
		}
		
	
	
}
