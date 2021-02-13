package com.stackroute.newz.model;

import java.util.Date;

public class Reminder {

	/*
	 * This class should have two fields(reminderId,schedule).
	 * This class should also contain the getters and setters for the 
	 * fields along with the parameterized	constructor and toString method.
	 * The value of newssourceCreationDate should not be accepted from the user but should be
	 * always initialized with the system date.
	 */
	
	private String reminderId;
	
	private Date schedule;
	
	public Reminder() {}
	
	public Reminder(String reminderId) {
		this.reminderId = reminderId;
		this.schedule = new Date();
	}
	
	public String getReminderId() {
		return reminderId;
	}

	public void setReminderId(String reminderId) {
		this.reminderId = reminderId;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule() {
		this.schedule = new Date();
	}

	@Override
	public String toString() {
		return "Reminder [reminderId=" + reminderId + ", schedule=" + schedule + "]";
	}
	
}
