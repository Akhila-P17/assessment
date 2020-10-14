package org.tool.teacher;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface TeacherSubjectRepository extends CrudRepository<TeacherSubjectEntity, String> {
	
	boolean existsTeacherSubjectEntityById(String id);				// checks if a subject exists by id
		
	TeacherSubjectEntity findTeacherSubjectEntityById(String id);   // takes in subject_id and return a subject if exists
		
	
}
