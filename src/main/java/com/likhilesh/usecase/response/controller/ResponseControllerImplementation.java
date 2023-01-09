package com.likhilesh.usecase.response.controller;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.likhilesh.usecase.request.model.RequestModel;
import com.likhilesh.usecase.request.service.RequestService;
import com.likhilesh.usecase.response.model.ResponseModel;
import com.likhilesh.usecase.response.service.ResponseService;

@Service
public class ResponseControllerImplementation extends TimerTask{

	private RequestService msg_service;
	private ResponseService resp_service;
	
	public ResponseControllerImplementation(RequestService msg_service, ResponseService resp_service) {
		super();
		this.msg_service = msg_service;
		this.resp_service = resp_service;
	}

	@Override
	public void run() {
		scheduler();
	}

	public void scheduler() {
		try 
		{
			System.out.println("Timer");
			List<Long> m = getCurrentMessages();
			for(Long i:m) {
				System.out.println(i);
				sendMessage(i);
				Thread.sleep(1 * 1000);
			}
		}
		catch (InterruptedException ie) 
		{
		    Thread.currentThread().interrupt();
		}
//		Can also be used if t.schedule has only 2 parameters given
//		Thread.sleep(60 * 1000);
//		sleepCheck();
	}
	
	private List<Long> getCurrentMessages(){
		Date now = new Date();
		String datePattern = "yyyy-MM-dd";
		String timePattern = "HH:mm:ss";
	    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	    SimpleDateFormat timeFormatter = new SimpleDateFormat(timePattern);
	    String mysqlDateString = dateFormatter.format(now);
	    String mysqlTimeString = timeFormatter.format(now);
		return msg_service.getMessageIDsToSend(mysqlDateString,mysqlTimeString);
	}
	
	private void sendMessage(Long i){
		RequestModel message = msg_service.getMessageById(i);
		ResponseModel response = new ResponseModel();
		response.setId(message.getId());
		response.setDate(message.getDate());
		response.setTime(message.getTime());
		response.setMessage(message.getMessage());
		response.setMobile_number(message.getMobile_number());
		//API CALL
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
			MultiValueMap<String, String> headerMap = new HttpHeaders();
			headerMap.add("apikey", "qjuw4upnhog80rdcrowywkebdo0v1u2x");
			headerMap.add("Content-Type", "application/x-www-form-urlencoded");
			MultiValueMap<String, String> bodyMap = new HttpHeaders();
			bodyMap.add("channel", "whatsapp");
			bodyMap.add("source", "917834811114");
			bodyMap.add("src.name", "UseCase1");
			bodyMap.add("destination", message.getMobile_number());
			String msgg = "{\"type\":\"text\",\"text\":"+"\""+message.getMessage()+"\"}";
			bodyMap.add("message", msgg);
			HttpEntity<MultiValueMap<String, String> > entity = new HttpEntity<MultiValueMap<String, String> >(bodyMap,headerMap);
			RestTemplate restemp = new RestTemplate();
			String result = restemp.exchange("https://api.gupshup.io/sm/api/v1/msg", HttpMethod.POST, entity, String.class).getBody();
			System.out.println(result);
		//API CALL
		response.setResponse(result);
		msg_service.deleteMessageById(i);
		resp_service.saveResponse(response);
//		System.out.println(msgg);
//		System.out.println(headerMap.toString());
//		System.out.println(bodyMap.toString());
	}

}

