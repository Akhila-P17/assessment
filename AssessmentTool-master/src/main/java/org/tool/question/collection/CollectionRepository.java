package org.tool.question.collection;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends CrudRepository<CollectionEntity, String> {
	
	List<CollectionEntity> findByTeacherUsername(String teacherUsername);
	
	void deleteByCollectionCodeAndTeacherUsername(String collectionCode,String teacherUsername);
}
