package com.ali.client;

import java.util.List;

import com.ali.shared.Student;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("go")
public interface StudentService extends RemoteService {
	public void saveStudent(Student s);
	public List<Student> searchStudent(String name);

}
