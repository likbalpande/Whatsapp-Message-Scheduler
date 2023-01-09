package com.likhilesh.usecase.response;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.likhilesh.usecase.response.model.ResponseModel;
import com.likhilesh.usecase.response.repository.ResponseDAO;
import com.likhilesh.usecase.response.service.ResponseService;

@SpringBootTest
public class ResponseDaoTest {
	@Autowired
	public ResponseDAO repo;
	@Autowired
	private ResponseService responseService;
	
	@Test
	void isMessageExists() {
		Long id = 0L;
//		repo.deleteById(id);
		var response = new ResponseModel();
		var now = new DateAndTime();
		response.setDate(now.getSqlDate());
		response.setTime(now.getSqlTime());
		response.setMobile_number("919503985583");
		response.setMessage("Test Message from JUNIT");
		response.setResponse("JUNIT Test");
		responseService.saveResponse(response);
//		repo.save(response);
		assertNotNull(repo.getReferenceById(id));
		responseService.deleteResponse(id);
	}
	
	@Test
	void savedResponsesValidnessOnDateandTime() {
//			long id = 0;
//			var ref = repo.getReferenceById(id);
//			if(ref!=null)repo.deleteById(id); HELP!!
		var now = new DateAndTime();
		List<ResponseModel> list = repo.findAll();
		for(ResponseModel resp:list) {
			int dateComparision = now.getMysqlDateString().compareTo(resp.getDate().toString());
			assertNotEquals(dateComparision,-1);
			if(dateComparision==0) {
				int timeComparision = now.getMysqlTimeString().compareTo(resp.getTime().toString());
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
