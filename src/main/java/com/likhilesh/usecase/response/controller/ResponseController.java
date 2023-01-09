package com.likhilesh.usecase.response.controller;

import java.util.Timer;

import org.springframework.stereotype.Service;

import com.likhilesh.usecase.request.service.RequestService;
import com.likhilesh.usecase.response.service.ResponseService;

@Service
public class ResponseController {
	private RequestService msg_service;
	private ResponseService resp_service;
	public ResponseController(RequestService msg_service, ResponseService resp_service) {
		super();
		this.msg_service = msg_service;
		this.resp_service = resp_service;
		
		ResponseControllerImplementation task = new ResponseControllerImplementation(msg_service,resp_service);
		Timer t = new Timer(true);
		t.schedule(task,2000,15000);
	}
}
