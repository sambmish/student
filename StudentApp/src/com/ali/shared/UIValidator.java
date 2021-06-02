package com.ali.shared;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.validation.client.impl.Validation;

public class UIValidator extends Validation {

public boolean validateStudent(String name,String rollNo,Date dob){
	return validateName(name) && validateRollNo(rollNo) && validateDOB(dob);
}
public boolean validateName(String name){
	if(name==null || name.equals(""))
	{
		Window.alert("Please Enter Name.");
		return false;
	}
	if(!name.matches("^[a-zA-Z]{1,25}$"))
	{
		Window.alert("Invalid Name.Name should not contain space/specialCharecter/degits.");
		return false;
	}
	return true;
}
public boolean validateRollNo(String rollNo){
	if(rollNo.equals(""))
	{
		Window.alert("Please Enter Roll No.");
		return false;
	}
	if(!rollNo.matches("^[0-9]+"))
	{
		Window.alert("Invalid Roll No.Roll No should contain only numbers.");
		return false;
	}
	int rollNumber=rollNo.equals("")?0:Integer.parseInt(rollNo);
	if(rollNumber<1 || rollNumber>500)
	{
		Window.alert("Invalid Roll No.Roll No should be between 1-500");
		return false;	
	}
		
	return true;
}
public boolean validateDOB(Date dob){
	if(dob==null){
		Window.alert("Please Enter Date Of Birth.");
		return false;
	}
	else if(dob!=null && dob.after(new Date()))
	{
		Window.alert("Invalid date Of Birth.It cannot be a future date.");
		return false;
	}
	return true;
}

}
