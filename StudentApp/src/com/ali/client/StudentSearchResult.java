package com.ali.client;

import java.awt.GridLayout;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ali.shared.Student;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudentSearchResult extends Composite {
	/*
	 * public static Class Student{ private final String name; private final
	 * Date dbo; private final String _class; public Student(String name,Date
	 * dbo,String _class){ this.name=name; this.dbo=dbo; this._class=_class;
	 * 
	 * }};
	 */
	private final StudentServiceAsync studentService = GWT
			.create(StudentService.class);
	VerticalPanel vpanel = new VerticalPanel();

	public StudentSearchResult(String name) {
		initWidget(vpanel);

		studentService.searchStudent(name, new AsyncCallback<List<Student>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Student> result) {
				// TODO Auto-generated method stub
				addResult(result);

			}

			private void addResult(List<Student> result) {
				// TODO Auto-generated method stub
				System.out.println(result);
				int noOfStudents = result.size();

				DataGrid<Student> studentgrid1 = new DataGrid<Student>();
				studentgrid1.setRowData(result);
				studentgrid1
						.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				TextColumn name = new TextColumn<Student>() {

					@Override
					public String getValue(Student object) {
						// TODO Auto-generated method stub
						return object.getName();
					}
				};

				studentgrid1.addColumn(name, "Name");
				studentgrid1.setSize("400px", "400px");
				// studentgrid.addColumn(0, "");
				// GridLayout gl=new GridLayout(noOfStudents, 3);

				Grid studentgrid = new Grid(noOfStudents, 3);
				studentgrid.getColumnCount();

				int i = 0;
				for (Iterator iterator = result.iterator(); iterator.hasNext();) {
					Student student = (Student) iterator.next();
					studentgrid.setText(i, 0, student.getName());
					studentgrid.setText(i, 1, student.getDob() + "");
					studentgrid.setText(i, 2, student.getClass_());
					i++;
				}
				vpanel.add(studentgrid1);

				vpanel.setBorderWidth(1);

				RootPanel.get().add(vpanel);

			}

		});

	}

}
