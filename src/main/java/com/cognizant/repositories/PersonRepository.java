package com.cognizant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
	List<Person> findByUserIdAndPassword(String userId, String password);
	Person findByContactNumber(String contact);
	Person findByAns1AndAns2AndAns3(String ans1, String ans2, String ans3);
	Person findByUserId(String user);
	Person findByUserIdAndAns1AndAns2AndAns3(String contact, String ans1, String ans2, String ans3);
	Person findByContactNumberAndAns1AndAns2AndAns3(String contact, String ans1, String ans2, String ans3);
}
