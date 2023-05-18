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
public class HotelClient {
	
public String saveHotel(JsonNode json) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8181/api/hotel/createHotel", request, String.class);
		String objects = responseEntity.getBody();
		return objects;
		
	}

public Object addReview(JsonNode json, int hotelId) {
	
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);		
	HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8181/api/hotel/addReview/"+hotelId, request, Object.class);
	Object object = responseEntity.getBody();
	return object;
	
}

public Object getHotelList(String searchText) {
	
	HttpHeaders headers = new HttpHeaders();	
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/api/hotel/getHotel/"+searchText, Object.class);
	Object objects =  responseEntity.getBody();
	return objects;
	
}

public Object getHotelById(int hotelId) {
	// 
	
	HttpHeaders headers = new HttpHeaders();	
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/api/hotel/getHotelById/"+hotelId, Object.class);
	Object objects =  responseEntity.getBody();
	return objects;
}

public Object getRoomByType(String type) {
	HttpHeaders headers = new HttpHeaders();	
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/api/hotel/getRoomByType/"+type, Object.class);
	Object objects =  responseEntity.getBody();
	return objects;
}
	
//	public JsonNode getCountryList() {
//		
//		System.out.println("Inside Rest Client");
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/getCountryList",Object.class);
//		Object objects = responseEntity.getBody();
//		
//		ObjectMapper mapper = new ObjectMapper();	
//		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
//		return returnObj;
//		
//	}

}
