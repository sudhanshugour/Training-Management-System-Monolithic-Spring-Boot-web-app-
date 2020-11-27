package com.cognizant.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cognizant.model.Topic;



public interface TopicRepository extends JpaRepository<Topic, Long> {
List<Topic> findBySkillId(long skillId);
	
	
	@Query("select max(t.topicNumber) from Topic t where skill_Id=?1")
	short maxTopicNoOfSkill(long skillid);
	
	@Query("select count(t.topicNumber) from Topic t where skill_Id=?1")
	long countTopicNoOfSkill(long skillid);
	
	@Query("select min(t.topicId) from Topic t where skill_Id=?1" )
	long firstTopicIdOfSkill(long skillid);

	
	@Query("select t from Topic t where skill_Id=?1 and topic_Number =?2")
	List<Topic> topicIdBySkillIdAndTopicNo(long skillid,short topicNumber);


}