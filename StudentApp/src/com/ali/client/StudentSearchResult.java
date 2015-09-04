package com.ali.client;

import java.awt.GridLayout;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ali.client.AddStudent.BackButtonClickHandler;
import com.ali.shared.Student;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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
	VerticalPanel vpanel1 = new VerticalPanel();
	DataGrid<Student> studentgrid = new DataGrid<Student>();
	List<Student> result1;
	public StudentSearchResult(String name,String rollNo,String standard ) {
		initWidget(vpanel);

		studentService.searchStudent(name,rollNo,standard, new AsyncCallback<List<Student>>() {

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
				result1=result;
				HorizontalPanel hpanel=new HorizontalPanel();
				SubmitButton backbutton=new SubmitButton("Back");
				//backbutton.setWidth("1");
				backbutton.addClickHandler(new BackButtonClickHandler());
				hpanel.add(backbutton);
				vpanel.add(hpanel);
				
				
				studentgrid.setRowData(result);
				 Column<Student, Boolean> checkBoxColumn = new Column<Student, Boolean>(
				            new CheckboxCell(false, false)) {
						@Override
						public Boolean getValue(Student object) {
							// TODO Auto-generated method stub
							return null;
						}
				    };
				    
				    
					EditTextCell nameedit=new EditTextCell();
					Column<Student,String> namecolumn=new Column<Student,String>(nameedit) {
						
						@Override
						public String getValue(Student object) {
							// TODO Auto-generated method stub
							return object.getName();
						}
					};
					EditTextCell rnedit=new EditTextCell();
					Column<Student,String> rncolumn=new Column<Student,String>(rnedit) {
						
						@Override
						public String getValue(Student object) {
							// TODO Auto-generated method stub
							return Integer.toString(object.getRollNo());
						}
					};
				
					EditTextCell dobedit=new EditTextCell();
					Column<Student,String> dobcolumn=new Column<Student,String>(dobedit) {
						
						@Override
						public String getValue(Student object) {
							// TODO Auto-generated method stub
							return object.getDob().toString();
						}
					};
				
					EditTextCell classedit=new EditTextCell();
					Column<Student,String> classcolumn=new Column<Student,String>(classedit) {
						
						@Override
						public String getValue(Student object) {
							// TODO Auto-generated method stub
							return object.getClass_();
						}
					};
				studentgrid.addColumn(checkBoxColumn,"Select");
				studentgrid.addColumn(namecolumn, "Name");
				studentgrid.addColumn(rncolumn, "Roll No");
				studentgrid.addColumn(dobcolumn, "Date Of Birth");
				studentgrid.addColumn(classcolumn, "Standard");
				studentgrid.setSize("400px", "400px");
				
				HorizontalPanel h1=new HorizontalPanel();
				RootPanel.get().remove(vpanel1);
				vpanel1.add(studentgrid);
				vpanel1.setBorderWidth(1);
				vpanel.add(vpanel1);
				Button updatebutton=new Button("Update",new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						
						System.out.println(studentgrid.getColumn(0).getValue(result1.get(0)));
					}
				});
				Button deletebutton=new Button("Delete");
				h1.add(updatebutton);
				h1.add(deletebutton);
				vpanel.add(h1);
				
					/*for (Student student : result) {
						if(studentgrid.getRowContainer().getAbsoluteLeft()){
							
						}
					}*/
					
				updatebutton.addClickHandler(new UpdateClickHandler());
				deletebutton.addClickHandler(new DeleteClickHandler());
				vpanel.add(deletebutton);
				


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
	public class DeleteClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			
		}
	}
	public class UpdateClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			//MainPage mainpage=new MainPage();
			//vpanel.add(addStudentpage);
			
		}
	}

}
