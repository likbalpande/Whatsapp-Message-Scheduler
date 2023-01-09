package com.likhilesh.usecase.response.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="responses")
public class ResponseModel {
	
	@Id
	@Column(name = "ID")
	private long id;
	
//	@Column(name = "date", nullable = false)
	@Column(name = "date")
	private Date date;
	
//	@Column(name = "time", nullable = false)
	@Column(name = "time")
	private Time time;
	
//	@Column(name = "mobile_number", nullable = false)
	@Column(name = "mobile_number")
	private String mobile_number;
	
//	@Column(name = "message", nullable = false)
	@Column(name = "message")
	private String message;

//	@Column(name = "response", nullable = false)
	@Column(name = "response")
	private String response;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
