package org.tool.question.collection;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Integer> {
	
	List<QuestionEntity> findByTeacherUsernameAndCollectionCode(String teacherUsername, String collectionCode);
	
	QuestionEntity findByTeacherUsernameAndQuestionId(String teacherUsername, int questionId);
	
	void deleteByTeacherUsernameAndQuestionId(String teacherUsername, int questionId);

}
