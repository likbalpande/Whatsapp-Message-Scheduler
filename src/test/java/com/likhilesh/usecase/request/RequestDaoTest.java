package com.likhilesh.usecase.request;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.likhilesh.usecase.request.model.RequestModel;
import com.likhilesh.usecase.request.service.RequestService;

@SpringBootTest
public class RequestDaoTest {
	@Autowired
	private RequestService msg_service;
	
	@Test
	void isMessageExists() {
		var message = new RequestModel();
		var now = new DateAndTime();
	    message.setDate(now.getSqlDate());
	    message.setTime(now.getSqlTime());
	    message.setMobile_number("919503985583");
	    message.setMessage("Test Message from JUNIT");
		msg_service.saveMessage(message);
		List<Long> list = msg_service.getMessageIDsToSend(now.getMysqlDateString(),now.getMysqlTimeString());
		assertNotNull(list);
	}
	
	@Test
	void pollMessagesValidnessOnDateandTime() {
		var now = new DateAndTime();
		List<Long> list = msg_service.getMessageIDsToSend(now.getMysqlDateString(),now.getMysqlTimeString());
		for(Long i:list) {
			RequestModel message = msg_service.getMessageById(i);
			int dateComparision = now.getMysqlDateString().compareTo(message.getDate().toString());
			assertNotEquals(dateComparision,-1);
			if(now.getMysqlDateString().compareTo(message.getDate().toString())==0) {
				int timeComparision = now.getMysqlTimeString().compareTo(message.getTime().toString());
				assertNotEquals(timeComparision,-1);
			}
		}
	}
}

class DateAndTime {
	java.sql.Date sqlDate;
	java.sql.Time sqlTime;
	String mysqlDateString;
	String mysqlTimeString;
	public DateAndTime() {
		java.util.Date now = new java.util.Date();
		this.sqlDate = new java.sql.Date(now.getTime());
		this.sqlTime = new java.sql.Time(now.getTime());
		String datePattern = "yyyy-MM-dd";
		String timePattern = "HH:mm:ss";
	    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	    SimpleDateFormat timeFormatter = new SimpleDateFormat(timePattern);
	    this.mysqlDateString = dateFormatter.format(now);
	    this.mysqlTimeString = timeFormatter.format(now);
	}
	public java.sql.Date getSqlDate() {
		return sqlDate;
	}
	public java.sql.Time getSqlTime() {
		return sqlTime;
	}
	public String getMysqlDateString() {
		return mysqlDateString;
	}
	public String getMysqlTimeString() {
		return mysqlTimeString;
	}
}

//java.util.Date now = new java.util.Date();
//java.sql.Date sqlDate = new java.sql.Date(now.getTime());
//java.sql.Time sqlTime = new java.sql.Time(now.getTime());
//String datePattern = "yyyy-MM-dd";
//String timePattern = "HH:mm:ss";
//SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
//SimpleDateFormat timeFormatter = new SimpleDateFormat(timePattern);
//String mysqlDateString = dateFormatter.format(now);
//String mysqlTimeString = timeFormatter.format(now);
