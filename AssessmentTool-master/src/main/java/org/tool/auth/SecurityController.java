package org.tool.auth;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.tool.reponse.ResponseMessage;

@RestController
@PermitAll
public class SecurityController {
	
	@Autowired
	private ResponseMessage respMessage;
	
	
	
	//if user is already logged in and goes to login page, then he is redirected his dashboard, else will remain on login page
	@GetMapping("/check/login")
	public ResponseMessage checkLogin(Principal principal) {
		if(principal == null) {
			respMessage.setStatus("false");
			return respMessage;
		}
		respMessage.setStatus("true");
		return respMessage;
	}
	


	// redirect to their respective dash boards according their roles.
	@GetMapping("/redirect")
	public RedirectView redirect(HttpServletRequest request) {

		RedirectView redirectView = new RedirectView();

		if (request.isUserInRole("ROLE_ADMIN")) {
			redirectView.setUrl("/admin.html");
			return redirectView;
		} else if (request.isUserInRole("ROLE_TEACHER")) {
			redirectView.setUrl("/tdashboard.html");
			return redirectView;
		} else {
			redirectView.setUrl("/sdashboard.html");
			return redirectView;
		}
	}
	

}
