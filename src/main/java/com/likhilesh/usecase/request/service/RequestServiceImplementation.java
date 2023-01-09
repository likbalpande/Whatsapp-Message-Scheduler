package com.likhilesh.usecase.request.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likhilesh.usecase.request.model.RequestModel;
import com.likhilesh.usecase.request.repository.RequestDAO;

@Service
public class RequestServiceImplementation implements RequestService{

	private RequestDAO repo;
	
	
	public RequestServiceImplementation(RequestDAO repo) {
		super();
		this.repo = repo;
//		ScheduleTimer task = new ScheduleTimer();
//		Timer t = new Timer(true);
//		t.schedule(task,2000);
	}

	@Override
	public RequestModel saveMessage(RequestModel message) {
		return repo.save(message);
	}

	@Override
	public List<RequestModel> getAllMessages() {
		return repo.findAll();
	}

	@Override
	public List<Long> getMessageIDsToSend(String date, String time) {
		return repo.getMessageIDsToSend(date, time);
	}

	@Override
	public RequestModel getMessageById(Long id) {
		return repo.findById(id).orElseThrow(()->null);
	}

	@Override
	public void deleteMessageById(Long id) {
		RequestModel obj = repo.findById(id).orElseThrow(()->null);
		if(obj!=null) {
			repo.deleteById(id);			
		}
		
	}

}
