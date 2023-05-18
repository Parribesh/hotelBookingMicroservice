package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}
