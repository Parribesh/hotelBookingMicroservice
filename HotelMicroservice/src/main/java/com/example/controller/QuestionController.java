package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Question;
import com.example.service.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired QuestionService questionService;
	
	@GetMapping("api/question/getQuestionsById/{hotelId}")
	public List<Question> getQuestionById(@PathVariable int hotelId) {
		return questionService.getAllQuestions();
	}
	
	@PostMapping("/api/question/createQuestion")
	public String createQuestion(@RequestBody Question question) {
		questionService.createQuestion(question);
		return "success";
	}
	
	@GetMapping("api/question/getUnansweredQuestion")
	public List<Question> getUnansweredQuestions() {
		return questionService.getAllUnansweredQuestions();
	}
	
	@GetMapping("api/question/getSpecificQuestionById/{questionId}")
	public Question getSpecificQuestionById(@PathVariable int  questionId) {
		return questionService.getQuestionById(questionId);
	}
	
	@PostMapping("/api/question/submitAnswer")
	public String answerQuestion(@RequestBody Question question) {
		return questionService.answerQuestion(question);
	}
	
}
