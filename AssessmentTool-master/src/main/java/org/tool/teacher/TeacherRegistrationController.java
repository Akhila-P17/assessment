package org.tool.teacher;

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

@RestController
@PermitAll
public class TeacherRegistrationController {

	@Autowired
	private TeacherRepository tRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ResponseMessage responseMessage;
	
	
	

	@GetMapping("/generate/teacher/otp/{email}")
	public ResponseMessage generateOtp(@PathVariable("email") String email, HttpServletRequest request) {

		if (!tRepo.existsTeacherEntityByEmail(email)) {
			String otp = RandomStringUtils.random(5, true, false);
			request.getSession().setAttribute("otp", otp);
			request.getSession().setAttribute("verified", false);
			MailService.send(email, "One Time Password ", " Your OTP is :  " + otp);

			responseMessage.setMessage("Please verify OTP that is sent to your mail");
		} else {
			responseMessage.setMessage("Email  already exists. Please Login");
		}
		return responseMessage;
	}
	
	
	

	@PermitAll
	@GetMapping("/verify/teacher/otp/{otp}")
	public ResponseMessage verifyOtp(@PathVariable("otp") String otp, HttpServletRequest request) {

		if (request.getSession().getAttribute("otp").equals(otp)) {
			request.getSession().removeAttribute("otp");
			request.getSession().setAttribute("verified", true);
			responseMessage.setMessage("true");
		} else {
			responseMessage.setMessage("false");
		}
		return responseMessage;
	}

	
	
	@PostMapping("/teacher/register")
	public ResponseMessage registerTeacher(@RequestBody TeacherEntity teacher, HttpServletRequest request) {

		// check if email already exists in db.
		if (!tRepo.existsTeacherEntityByEmail(teacher.getEmail()) && request.getSession().getAttribute("verified").equals(true)) {

			request.getSession().setAttribute("verified", false );
			
			teacher.setId(java.time.LocalDate.now().toString().replaceAll("-", "")
					+ java.time.LocalTime.now().toString().replaceAll(":", "").replaceAll("\\.", ""));

			// iterate through teacher subjects that we got in post request body
			for (TeacherSubjectEntity s : teacher.getSubjectList()) {
				// generate an id for each subject with prefix as subject name itself
				s.setId(s.getName().replaceAll("\\s", "").toLowerCase()
						+ java.time.LocalTime.now().toString().replaceAll(":", "").replaceAll("\\.", "").substring(4));
				s.setTeacher(teacher);
			}

			// saving teacher auth credentials in user table.
			User user = new User(teacher.getEmail(), new BCryptPasswordEncoder(11).encode(teacher.getPassword()),
					"ROLE_TEACHER", true, true, true, true);
			userRepo.save(user);

			// save the data to db
			tRepo.save(teacher);

			// send password to the mail id
			MailService.send(teacher.getEmail(), "Registration Successful ",
					" Your user_id : " + teacher.getEmail() + " password : " + teacher.getPassword());

			// set response message and return it as resposne
			responseMessage.setStatus("success");
			responseMessage.setMessage("Registration Successful. Your User ID and Password are sent to your mail.");
			return responseMessage;

		} else {

			// if email already exists in db then, send below message
			responseMessage.setStatus("failure");
			responseMessage.setMessage("Something went worng!! Please try again.");
			return responseMessage;
		}

	}

}
