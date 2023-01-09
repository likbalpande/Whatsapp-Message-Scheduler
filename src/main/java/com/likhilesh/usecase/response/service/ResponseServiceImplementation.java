package com.likhilesh.usecase.response.service;

import org.springframework.stereotype.Service;

import com.likhilesh.usecase.response.model.ResponseModel;
import com.likhilesh.usecase.response.repository.ResponseDAO;

@Service
public class ResponseServiceImplementation implements ResponseService {
	public ResponseDAO repo;
	
	public ResponseServiceImplementation(ResponseDAO repo) {
		super();
		this.repo = repo;
	}

	public ResponseModel saveResponse(ResponseModel response) {
		return repo.save(response);
	}
	
	public void deleteResponse(long id) {
		repo.deleteById(id);
	}
	
}
