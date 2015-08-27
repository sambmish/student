package com.ali.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.validation.client.impl.Validation;

public class StudentValidator extends Validation {

public List<String> validateStudent(String name,String rollNo,Date dob){
	List<String> msglist=new ArrayList<String>();
	
	String namevmsg=this.validateName(name);
	String rollNovmsg=this.validateRollNo(rollNo);
	String dobvmsg=this.validateDOB(dob);
	if(namevmsg!="" && namevmsg!=null)
		msglist.add(namevmsg);
	if(rollNovmsg!="" && rollNovmsg!=null)
		msglist.add(rollNovmsg);

	if(dobvmsg!="" && dobvmsg!=null)
		msglist.add(dobvmsg);


	return msglist;
}
public String validateName(String name){
	String msg = "";
	int specials = 0, digits = 0, letters = 0, spaces = 0;
	for (int i = 0; i < name.length(); ++i) {
	   char ch = name.charAt(i);
	   if (!Character.isDigit(ch) && !Character.isLetter(ch) && !Character.isSpace(ch)) {
	      ++specials;
	   } else if (Character.isDigit(ch)) {
	      ++digits;
	   } else if (Character.isSpace(ch)) {
	      ++spaces;
	   } else {
	      ++letters;
	   }
	}
	if(specials>0)
		msg="Invalid Name Entered.Name should not contain Special Charecters";
	if(digits>0)
		msg="Invalid Name Entered.Name should not contain digits";
	if(spaces>0)
		msg="Invalid Name Entered.Name should not contain spaces";
	return msg;
}
public String validateRollNo(String rollNo){
	int rollNumber=rollNo.equals("")?0:Integer.parseInt(rollNo);
	String msg="";
	boolean flag=true;
	for (int i = 0; i < rollNo.length(); i++) {
		if(Character.isDigit(rollNo.charAt(i)))
		{
			break;
		}
		else
		{
			msg="Invalid Roll No.Please Enter a Number";
			flag=false;
		}
	}
	if(!(flag && rollNumber<=500 && rollNumber>=100))
		msg="Invalid Roll No.Please Enter a no between 100-500";
	return msg;
}
public String validateDOB(Date dob){
	String msg="";
	if(dob==null){
		msg="Please Enter Date Of Birth";
	}
	else if(dob!=null && dob.after(new Date()))
	{
		msg="Date Of Birth can't be future Date";
	}
	return msg;
}

}
