package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Question;
import com.example.repo.QuestionRepo;

@Service
public class QuestionService implements IQuestionService {

	@Autowired QuestionRepo questionRepo;
	@Override
	public Question getQuestionById(int questionId) {
		// TODO Auto-generated method stub
		return questionRepo.findById(questionId).orElse(null);
	}

	@Override
	public void createQuestion(Question question) {
		// TODO Auto-generated method stub
		questionRepo.save(question);

	}

	@Override
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionRepo.findAll();
	}
	
	@Override
	public List<Question> getAllUnansweredQuestions() {
		List<Question> unanswered = questionRepo.findAll().stream().filter(e -> e.getAnswer() == null).toList();
		return unanswered;
	}
	
	@Override
	public String answerQuestion(Question question) {
		// TODO Auto-generated method stub
		System.out.println(question.getAnswer());
		Question answered = questionRepo.findById(question.getQuestionId()).orElse(null);
		answered.setAnswer(question.getAnswer());
		questionRepo.save(answered);
		return "success";
	}

}
