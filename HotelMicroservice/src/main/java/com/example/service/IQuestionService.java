package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Question;

@Service
public interface IQuestionService {
	
	public Question getQuestionById(int questionId);
	public void createQuestion(Question question);
	public List<Question> getAllQuestions();
	List<Question> getAllUnansweredQuestions();
	String answerQuestion(Question question);
}
