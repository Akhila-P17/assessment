package org.tool.student;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRelationEntityRepository extends CrudRepository<StudentSubjectRelationEntity, String> {
	
	
	//@Query("delete from StudentSubjectRalationEntity s where s.studentId = ?1 and s.subjectId = ?2")
	//void deleteStudentSubjectRalationEntityByStudentIdAndSubjectId(String studentId, String subjectId);
	
	
	@Modifying
	@Query("delete from StudentSubjectRelationEntity f where f.subjectId=:subjectId and f.studentId=:studentId")
	void removeByStudentIdAndSubjectId(@Param("subjectId") String subjectId, @Param("studentId") String studentId);

}
