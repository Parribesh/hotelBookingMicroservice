package com.booking.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HotelClient {
	
	public Object getHotelById(int hotelId) {
		// 
		
		HttpHeaders headers = new HttpHeaders();	
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/api/hotel/getHotelById/"+hotelId, Object.class);
		Object objects =  responseEntity.getBody();
		return objects;
	}

}
