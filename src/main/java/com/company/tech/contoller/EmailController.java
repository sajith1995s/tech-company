package com.company.tech.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.tech.request.EmailRequest;
import com.company.tech.response.TechResponse;

@RestController
@RequestMapping("/notification")
public class EmailController {
	
	 @Autowired
	 public JavaMailSender emailSender;
	 
	 @RequestMapping(method = RequestMethod.POST , value="/sendEmail")
	 public TechResponse sendEmail(@RequestBody EmailRequest emailRequest) {
		 
		 SimpleMailMessage message = new SimpleMailMessage();
		  message.setTo(emailRequest.getReceiverEmail());
	      message.setSubject("Test Simple Email");
	      message.setText("Hello, Im testing Simple Email");
	      
	      this.emailSender.send(message);
	      
	      TechResponse response = new TechResponse();
	      
	      response.setResponseCode("111");
	      response.setResponseObject("Email Send Succefully.");
		 
		 return response;
	 }
	 
//	 @RequestMapping(method = RequestMethod.GET , value="/getEmails" , params = { "type=cust_mer" })
//	 public TechResponse getEmail() {
//		 
//		 TechResponse response = new TechResponse();
//		 response.setResponseCode("111");
//		 response.setResponseObject("GET");
//		 
//		 return response;
//	 }
}
