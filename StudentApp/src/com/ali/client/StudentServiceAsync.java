package com.ali.client;

import java.util.List;

import com.ali.shared.Student;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StudentServiceAsync {

	public void saveStudent(Student s, AsyncCallback<Void> callback);

	public void searchStudent(String name,String rollNo,String standard, AsyncCallback<List<Student>> callback);

}
