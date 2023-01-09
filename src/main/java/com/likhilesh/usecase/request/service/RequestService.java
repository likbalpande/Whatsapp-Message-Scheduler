package com.likhilesh.usecase.request.service;

import java.util.List;

import com.likhilesh.usecase.request.model.RequestModel;

public interface RequestService {
	RequestModel saveMessage(RequestModel message);
	List<RequestModel> getAllMessages();
	List<Long> getMessageIDsToSend(String date,String time);
	RequestModel getMessageById(Long id);
	void deleteMessageById(Long id);
}
