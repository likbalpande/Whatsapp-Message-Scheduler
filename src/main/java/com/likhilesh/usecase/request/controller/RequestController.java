package com.likhilesh.usecase.request.controller;

//import java.sql.Date;
//import java.sql.Time;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhilesh.usecase.request.model.RequestModel;
import com.likhilesh.usecase.request.service.RequestService;


@RestController
@RequestMapping("/api")
public class RequestController {
	private RequestService service;

	public RequestController(RequestService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<String> scheduleMessage(@RequestHeader("authKey") String authKey, @RequestBody RequestModel obj){
		if(authKey.equals("Ax@1234")) {
			service.saveMessage(obj);
			System.out.println(obj);
			return new ResponseEntity<String>("Your message has been scheduled!",HttpStatus.CREATED);			
		}
		else {
			return new ResponseEntity<String>("Invalid Access!\nPlease enter a valid Authorization key.",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
	}
}

