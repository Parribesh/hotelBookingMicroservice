package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.client.QuestionClient;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class FaqController {
	
	@Autowired QuestionClient questionClient; 
	
	@PostMapping("/createQuestion")
	@ResponseBody
	public String createQuestion(@RequestBody JsonNode json) {
		return questionClient.addQuestion(json);
	}
	
	@GetMapping("/getQuestionsById/{hotelId}")
	@ResponseBody
	public JsonNode createQuestion(@PathVariable int hotelId) {
		JsonNode json = questionClient.getQuestionList(hotelId);
		return json;
	}
	
	@GetMapping("/getAllUnansweredQuestion")
	@ResponseBody
	public JsonNode getQestions() {
		JsonNode json = questionClient.getAllUnansweredQuestions();
		return json;
	}
	
	@GetMapping("/getSpecificQuestionById/{questionId}")
	@ResponseBody
	public JsonNode getSpecificQuestionById(@PathVariable int questionId) {
		JsonNode json = questionClient.getSpecificQuestionById(questionId);
		return json;
	}
	
	@PostMapping("/submitAnswer")
	@ResponseBody
	public String SumbitAnswer(@RequestBody JsonNode question) {
		return questionClient.submitAnswer(question);
	}
}
