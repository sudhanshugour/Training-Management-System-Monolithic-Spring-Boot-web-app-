package com.cognizant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cognizant.model.SecretQuestions;
@Repository
public interface SecretQuestionsRepository extends JpaRepository<SecretQuestions, Integer> {
	
	List<SecretQuestions> findBySecQueId(int queId1);
}
