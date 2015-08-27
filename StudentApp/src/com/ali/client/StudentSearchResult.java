package com.ali.client;

import java.awt.GridLayout;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ali.client.AddStudent.BackButtonClickHandler;
import com.ali.shared.Student;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
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
				HorizontalPanel hpanel=new HorizontalPanel();
				SubmitButton backbutton=new SubmitButton("Back");
				backbutton.setWidth("1");
				backbutton.addClickHandler(new BackButtonClickHandler());
				hpanel.add(backbutton);
				vpanel.add(hpanel);
				VerticalPanel vpanel1=new  VerticalPanel();
				int noOfStudents = result.size();
				for(int i=0;i<noOfStudents;i++){
					CheckBox cbox=new CheckBox(i+"");
					cbox.ensureDebugId(i+"");
					vpanel1.add(cbox);
				}

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
				TextColumn rollNo = new TextColumn<Student>() {

					@Override
					public String getValue(Student object) {
						// TODO Auto-generated method stub
						return Integer.toString(object.getRollNo());
					}
				};
				TextColumn dob = new TextColumn<Student>() {

					@Override
					public String getValue(Student object) {
						// TODO Auto-generated method stub
						return object.getDob().toString();
					}
				};
				TextColumn class_ = new TextColumn<Student>() {

					@Override
					public String getValue(Student object) {
						// TODO Auto-generated method stub
						return object.getClass_();
					}
				};
				studentgrid1.setRowCount(result.size());
				
				studentgrid1.addColumn(name, "Name");
				studentgrid1.addColumn(rollNo, "Roll No");
				studentgrid1.addColumn(dob, "Date Of Birth");
				studentgrid1.addColumn(class_, "Standard");
				studentgrid1.setSize("400px", "400px");
				
				HorizontalPanel h1=new HorizontalPanel();
				vpanel.add(studentgrid1);
				vpanel.setBorderWidth(1);
				h1.add(vpanel);
				h1.add(vpanel1);
				

				RootPanel.get().add(vpanel1);

			}

		});

	}
	public class BackButtonClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			MainPage mainpage=new MainPage();
			//vpanel.add(addStudentpage);
			
		}
	}

}
