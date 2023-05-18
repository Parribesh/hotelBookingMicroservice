package com.example.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class QuestionClient {

	public String addQuestion(JsonNode json) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8181/api/question/createQuestion", request, String.class);
		String object = responseEntity.getBody();
		return object;
		
	}
	
	public JsonNode getQuestionList(int hotelId) {
		
		HttpHeaders headers = new HttpHeaders();	
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/api/question/getQuestionsById/"+hotelId, Object.class);
		Object objects =  responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.convertValue(objects, JsonNode.class);
		return json;
		
	}
	
public JsonNode getAllUnansweredQuestions() {
		
		HttpHeaders headers = new HttpHeaders();	
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/api/question/getUnansweredQuestion", Object.class);
		Object objects =  responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.convertValue(objects, JsonNode.class);
		return json;
		
	}

public JsonNode getSpecificQuestionById(int questionId) {
	
	HttpHeaders headers = new HttpHeaders();	
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/api/question/getSpecificQuestionById/"+questionId, Object.class);
	Object objects =  responseEntity.getBody();
	
	ObjectMapper mapper = new ObjectMapper();
	JsonNode json = mapper.convertValue(objects, JsonNode.class);
	return json;
	
}

public String submitAnswer(JsonNode question) {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);	
	HttpEntity<String> request = new HttpEntity<String>(question.toString(), headers);
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8181/api/question/submitAnswer",request, String.class);
	String object = responseEntity.getBody();
	return object;
}


}
