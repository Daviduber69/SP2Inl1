package com.yrgo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_CALL")
public class Call {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Date timeAndDate;

	private String notes;
	private String customerId;

	public Call(String notes, String customerId){
		// this defaults to a timestamp of "now"
		this (notes, new java.util.Date(), customerId);
	}

	public Call(String notes, Date timeAndDate, String customerId) {
		this.timeAndDate = timeAndDate;
		this.notes = notes;
		this.customerId = customerId;
	}

	public String toString()	{
		return this.timeAndDate + " : " + this.notes;
	}

	public Date getTimeAndDate() {
		return timeAndDate;
	}

	public void setTimeAndDate(Date timeAndDate) {
		this.timeAndDate = timeAndDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCustomerId() {
		return customerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Call() {}
}
