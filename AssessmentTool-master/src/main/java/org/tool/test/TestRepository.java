package org.tool.test;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, String> {

	
	List<TestEntity> findByTeacherUsername(String teacherUsername);
	
	TestEntity findByTestCode(String testCode);
	
	void deleteByTeacherUsernameAndTestCode(String teacherUsername, String testCode);
	
	List<TestEntity> findBySubjectCode(String subjectCode);
	
}
