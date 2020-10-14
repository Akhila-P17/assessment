package org.tool.reponse;

import org.springframework.stereotype.Component;




@Component
public class ResponseMessage {
	
	private String Status;
	
	private String message;
	
	
	
	
	public ResponseMessage() {
	}



	public ResponseMessage(String status, String message) {
		Status = status;
		this.message = message;
	}
	
	

	public String getStatus() {
		return Status;
	}



	public void setStatus(String status) {
		Status = status;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	
	
	

}
