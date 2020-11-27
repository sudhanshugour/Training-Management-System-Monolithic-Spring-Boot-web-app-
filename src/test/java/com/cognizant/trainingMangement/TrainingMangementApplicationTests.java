package com.cognizant.trainingMangement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.model.SecretQuestions;
import com.cognizant.repositories.SecretQuestionsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainingMangementApplicationTests {

	@Autowired
	SecretQuestionsRepository secretQuestionsRepository;
	@Test
	public void contextLoads() {
		Map<String, Integer> secretQuestionsList=new HashMap<String, Integer>();
		List<SecretQuestions> pro= secretQuestionsRepository.findAll();
		System.out.println("data"+pro);
		  if(pro == null){
			  System.out.println("ksdfjksllklsdfj");
		  }
		  System.out.println(pro);
		  for(SecretQuestions secretQuestion: pro){
				System.out.println("in the dddddd");
			  secretQuestionsList.put(secretQuestion.getQuestion(),secretQuestion.getSecQueId());
			  System.out.println(secretQuestionsList.get(secretQuestion.getSecQueId()));
			}
		  
		
		System.out.println("in the test of my life");
		Iterator<Map.Entry<String, Integer>> itr = secretQuestionsList.entrySet().iterator(); 
        
        while(itr.hasNext()) 
        { 
             Map.Entry<String, Integer> entry = itr.next(); 
             System.out.println("Key = " + entry.getKey() +  
                                 ", Value = " + entry.getValue()); 
        } 
	}

}
