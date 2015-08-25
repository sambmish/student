package com.ali.shared;

import java.io.Serializable;
import java.util.Date;


public class Student implements Serializable {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getClass_() {
		return class_;
	}
	public void setClass_(String class_) {
		this.class_ = class_;
	}
	private Date dob;
	private String class_;
	private String name;
	public Student(){
		
	}
	public Student(String name,Date dob,String class_){
		this.name=name;
		this.dob=dob;
		this.class_=class_;
		
	}
	
	

}
