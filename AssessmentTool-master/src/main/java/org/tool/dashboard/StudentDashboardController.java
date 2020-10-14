package org.tool.dashboard;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tool.auth.UserRepository;
import org.tool.question.collection.CollectionRepository;
import org.tool.question.collection.QuestionRepository;
import org.tool.reponse.ResponseMessage;
import org.tool.student.StudentEntity;
import org.tool.student.StudentRepository;
import org.tool.student.StudentSubjectEntity;
import org.tool.student.StudentSubjectRelationEntityRepository;
import org.tool.student.StudentSubjectRepository;
import org.tool.teacher.TeacherRepository;
import org.tool.teacher.TeacherSubjectEntity;
import org.tool.teacher.TeacherSubjectRepository;
import org.tool.test.TestEntity;
import org.tool.test.TestRepository;

@RestController
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentDashboardController {

	@Autowired
	private TeacherRepository teacherRepoS;

	@Autowired
	private TeacherSubjectRepository teacherSubjectRepoS;

	@Autowired
	private StudentRepository studentRepoS;

	@Autowired
	private StudentSubjectRepository studSubRepoS;

	@Autowired
	private UserRepository userRepoS;

	@Autowired
	private CollectionRepository collectionRepoS;

	@Autowired
	private QuestionRepository questionRepoS;

	@Autowired
	private TestRepository testRepoS;

	@Autowired
	private ResponseMessage responseMessageS;
	
	@Autowired
	private StudentSubjectRelationEntityRepository studSubRelRepoS;

	private boolean loggedInStudentHasThisSubject;

	

	
	
	
	@GetMapping("/student/details")
	public StudentEntity getStudentDetails(Principal principal) {
		
		StudentEntity student = studentRepoS.findByEmail(principal.getName());
		student.setPassword(null);
		student.getSubjectList().clear();
		
		return student;
	}
	

	
	// this method will show you how to tackle infinite references
	@GetMapping("/student/subject/list")
	public List<StudentSubjectEntity> getAllStudenSubjectEntities(Principal principal) {

		StudentEntity student = studentRepoS.findByEmail(principal.getName());
		for (int i = 0; i < student.getSubjectList().size(); i++) {
			student.getSubjectList().get(i).getStudentsList().clear();
		}
		return student.getSubjectList();
	}
	
	
	@PostMapping("/student/add/subject")
	public ResponseMessage addStudentSubject(@RequestBody StudentSubjectEntity studentSubject, Principal principal) {
		
		StudentEntity student = studentRepoS.findByEmail(principal.getName());
		TeacherSubjectEntity teacherSubject;
		if ( teacherSubjectRepoS.existsTeacherSubjectEntityById( studentSubject.getId()) ) {
			
			teacherSubject = teacherSubjectRepoS.findTeacherSubjectEntityById( studentSubject.getId() );
			studentSubject.setName( teacherSubject.getName() );
			studentSubject.getStudentsList().add(student);
			student.getSubjectList().add(studentSubject);
			studentRepoS.save(student);
			
			responseMessageS.setStatus("added");
		}else {
			responseMessageS.setStatus("failed");
		}
		
		return responseMessageS;
	}
	
	
	
	@Transactional
	@DeleteMapping("/student/delete/{subjectId}/{studentId}")
	public ResponseMessage removeStudentFromSubject(@PathVariable("studentId") String studentId,
			@PathVariable("subjectId") String subjectId, Principal principal) {
		
		studSubRelRepoS.removeByStudentIdAndSubjectId(subjectId, studentId);
	    responseMessageS.setStatus("removed");
		return responseMessageS;
		
	}
	
	
	
	
	@GetMapping("/student/subject/{subjectcode}/student/list")
	public StudentSubjectEntity getAllStudenSubjectEntities(@PathVariable("subjectcode") String id,
			Principal principal) {

		// first we need to check if the subject id matches with the current logged in user

		studentRepoS.findByEmail(principal.getName()).getSubjectList().forEach(eachSubject -> {
			if (eachSubject.getId().equalsIgnoreCase(id)) {
				loggedInStudentHasThisSubject = true;
			}
		});

		if (loggedInStudentHasThisSubject) {

			// just to be safe
			loggedInStudentHasThisSubject = false;
			StudentSubjectEntity subject = studSubRepoS.findStudentSubjectEntityById(id);

			for (int j = 0; j < subject.getStudentsList().size(); j++) {
				// clear subject list from each subject list property of student entity to avoid infinite references
				subject.getStudentsList().get(j).getSubjectList().clear();		//to tackle infinite references
				// unnecessary fields will be emptied or nullified so that JsonIgnore can ignore them in results.
				subject.getStudentsList().get(j).setEmail(null);
				subject.getStudentsList().get(j).setPassword(null);
				subject.getStudentsList().get(j).setPhone(null); // phone is BigInteger and not primitive so null is valid
			}
			return subject;
		} else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	@GetMapping("/student/test/list")
	public List<TestEntity>  getStudentSubjectTests(Principal principal){
		
		List<TestEntity> tests = new ArrayList<TestEntity>();
		
		studentRepoS.findByEmail(principal.getName()).getSubjectList().forEach(eachSubject -> {
			tests.addAll( testRepoS.findBySubjectCode( eachSubject.getId() ) ); 
				
		});
		
		return tests;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// profile update methods here
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
