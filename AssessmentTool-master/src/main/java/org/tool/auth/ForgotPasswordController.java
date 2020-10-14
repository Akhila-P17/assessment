package org.tool.auth;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tool.mail.service.MailService;
import org.tool.reponse.ResponseMessage;

@RestController
public class ForgotPasswordController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseMessage responseMessage;
	
	
	@PermitAll
	@GetMapping("/generate/otp/{email}")
	public ResponseMessage generateOtp(@PathVariable("email") String email, HttpServletRequest request) {
		System.out.println(userRepo.existsUserEntityByUsername(email));

		if (userRepo.existsUserEntityByUsername(email)) {
			String otp = RandomStringUtils.random(5, true, false);
			request.getSession().setAttribute("otp", otp);
			request.getSession().setAttribute("verified", false);
			
			MailService.send(email, "One Time Password ", " Your OTP is :  " + otp);
			responseMessage.setStatus("success");
			responseMessage.setMessage("Please verify OTP that is sent to your mail");
		} else {
			responseMessage.setMessage("Email doesn't exists. Please Enter correct Email");
		}
		return responseMessage;
	}
	

	@PermitAll
	@GetMapping("/verify/otp/{otp}")
	public ResponseMessage verifyOtp(@PathVariable("otp") String otp, HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("otp").equals(otp));
		if (request.getSession().getAttribute("otp").equals(otp)) {
			request.getSession().removeAttribute("otp");
			request.getSession().setAttribute("verified", true);
			responseMessage.setMessage("true");
		} else {
			responseMessage.setMessage("false");
		}
		return responseMessage;
	}
	



	

}
