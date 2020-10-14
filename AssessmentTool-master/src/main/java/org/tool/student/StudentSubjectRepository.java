package org.tool.student;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface StudentSubjectRepository extends CrudRepository<StudentSubjectEntity, String> {
	
	
	boolean existsStudentSubjectEntiyById(String id);
	
	StudentSubjectEntity findStudentSubjectEntityById(String id);
	

}
