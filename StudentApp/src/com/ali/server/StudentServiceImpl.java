package com.ali.server;

import java.util.Date;
import java.util.List;

import com.ali.shared.Student;
import com.ali.client.StudentService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StudentServiceImpl extends RemoteServiceServlet implements 
StudentService{
	private ServiceImpl simpl=new ServiceImpl();
	@Override
	
	public void saveStudent(Student s) {
		// TODO Auto-generated method stub
		simpl.saveStudent(s);
		//System.out.println(s.getName()+""+s.getDob()+""+s.getClass_());
	}

	@Override
	public List<Student> searchStudent(String name,String rollNo,String standard) {
		// TODO Auto-generated method stub
		List<Student> list=simpl.searchStudent(name,rollNo,standard);
		return list;
	}

	@Override
	public void deleteStudent(Student s) {
		// TODO Auto-generated method stub
		simpl.deleteStudent(s);
	}
}
