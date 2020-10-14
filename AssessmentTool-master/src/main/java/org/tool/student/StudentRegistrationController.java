package org.tool.student;


import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tool.auth.User;
import org.tool.auth.UserRepository;
import org.tool.mail.service.MailService;
import org.tool.reponse.ResponseMessage;
import org.tool.teacher.TeacherSubjectEntity;
import org.tool.teacher.TeacherSubjectRepository;


@RestController
@PermitAll
public class StudentRegistrationController {
	
	
	
	@Autowired
	private StudentRepository studRepo;
	
	
	@Autowired
	private TeacherSubjectRepository teachSubRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseMessage responseMessage;
	

	
	
	
	@GetMapping("/generate/student/otp/{email}")
	public ResponseMessage verifyEmail(@PathVariable("email") String email, HttpServletRequest request ) {
		
		if(!studRepo.existsStudentEntityByEmail(email)) {
			
			String otp = RandomStringUtils.random(5, true, false);
			request.getSession().setAttribute("otp", otp );
			request.getSession().setAttribute("verified", false );
			MailService.send(email, "One Time Password  ", " Your OTP is :  " + otp);
			
			responseMessage.setMessage("Please verify OTP that is sent to your mail");
		}else {
			responseMessage.setMessage("Email  already exists. Please Login");
		}
		return responseMessage;
	}
	
	
	@GetMapping("/verify/student/otp/{otp}")
	public ResponseMessage verifyOtp(@PathVariable("otp") String otp, HttpServletRequest request ) {
		
		if ( request.getSession().getAttribute("otp").equals(otp) ) {
			request.getSession().removeAttribute("otp");
			request.getSession().setAttribute("verified", true );			
			responseMessage.setMessage("true");
		}else {
			responseMessage.setMessage("false");
		}
		return responseMessage;
	}
	
	
	
	
	
	
	
	
	@PostMapping("/student/register")
	public ResponseMessage registerStudent(@RequestBody StudentEntity student, HttpServletRequest request ) {
		
		
		//check if student email already exists in student table
		if(! studRepo.existsStudentEntityByEmail(student.getEmail())  && request.getSession().getAttribute("verified").equals(true) ) {
			
			request.getSession().setAttribute("verified", false );			
			
			student.setId(java.time.LocalDate.now().toString().replaceAll("-", "")+ java.time.LocalTime.now().toString().replaceAll(":", "").replaceAll("\\.", ""));


			//now check if subjects entered by student exist in db and update the subject list accordingly
			here :{
				
				int subjectListSize = student.getSubjectList().size();
				
				for (int i = 0; i < subjectListSize ; i++) {
					
					TeacherSubjectEntity teacherSubject;
					
					if ( teachSubRepo.existsTeacherSubjectEntityById( student.getSubjectList().get(i).getId()) ) {
						
						teacherSubject = teachSubRepo.findTeacherSubjectEntityById( student.getSubjectList().get(i).getId() );
						student.getSubjectList().get(i).setName( teacherSubject.getName() );
						
						student.getSubjectList().get(i).getStudentsList().add(student);
											
					}else {
						
						student.getSubjectList().remove(i);						
						break here;
						
					}
				}
				
			}
			
			// saving student auth credentials in user table.
			User user = new User( student.getEmail(),  new BCryptPasswordEncoder(11).encode( student.getPassword()) , "ROLE_STUDENT" , true, true, true, true);
			userRepo.save(user);

			//save student data to student table
			studRepo.save(student);

			MailService.send(student.getEmail(), "Registration Successful ", " Your user_id : " + student.getEmail() +  "  password : " + student.getPassword());
									
			responseMessage.setStatus("success");
			responseMessage.setMessage("Registration Successful. Your User ID and Password are sent to your mail.");
			return  responseMessage;
			
		}else {
			
			// if email already exists in db
			responseMessage.setStatus("failure");
			responseMessage.setMessage("Something went worng!! Please try again.");
			return responseMessage;
		}
	
		
		
	}
	
	
	 
	
	
	

}
