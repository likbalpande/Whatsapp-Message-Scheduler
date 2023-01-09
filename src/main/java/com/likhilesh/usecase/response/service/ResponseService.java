package com.likhilesh.usecase.response.service;

import org.springframework.stereotype.Service;

import com.likhilesh.usecase.response.model.ResponseModel;

@Service
public interface ResponseService {
	
	ResponseModel saveResponse(ResponseModel response);
	void deleteResponse(long id);
	
}
