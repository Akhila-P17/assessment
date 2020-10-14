package org.tool.teacher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;






@Repository
public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {
	
	boolean existsTeacherEntityByEmail(String email);
	
	TeacherEntity findTeacherEntityByEmail(String email);
	
	
	

}
