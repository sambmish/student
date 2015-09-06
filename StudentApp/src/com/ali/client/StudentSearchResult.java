package com.ali.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ali.shared.Student;
import com.ali.shared.UIValidator;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.sun.java.swing.plaf.windows.resources.windows;

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
	TextBox srchNametb = new TextBox();
	TextBox srchRNtb = new TextBox();
	TextBox srchStrdtb = new TextBox();
	VerticalPanel headerPanel = new VerticalPanel();
	DataGrid<Student> studentgrid = new DataGrid<Student>();

	public class GoBttonHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			String name = srchNametb.getValue();
			String rollNo = srchRNtb.getValue();
			String strd = srchStrdtb.getValue();
			search(name, rollNo, strd);
		}
	}

	public void initializePage() {
		HorizontalPanel srchNamehp = new HorizontalPanel();
		HorizontalPanel srchRNhp = new HorizontalPanel();
		HorizontalPanel srchStrdhp = new HorizontalPanel();

		Label srchNameLbl = new Label("Name:");
		Label srchRNLbl = new Label("Roll No:");
		Label srchStrdLbl = new Label("Standard:");

		srchNamehp.add(srchNameLbl);
		srchNamehp.add(srchNametb);
		srchRNhp.add(srchRNLbl);
		srchRNhp.add(srchRNtb);
		srchStrdhp.add(srchStrdLbl);
		srchStrdhp.add(srchStrdtb);

		Button search = new Button("Search");
		search.addClickHandler(new GoBttonHandler());

		headerPanel.add(srchNamehp);
		headerPanel.add(srchRNhp);
		headerPanel.add(srchStrdhp);

		headerPanel.add(search);

		headerPanel.setSize("300px", "100px");
		vpanel.add(headerPanel);
	}

	public StudentSearchResult() {
		initWidget(vpanel);
		initializePage();
	}

	private void search(String name, String rollNo, String standard) {
		studentService.searchStudent(name, rollNo, standard,
				new AsyncCallback<List<Student>>() {

					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(List<Student> result) {
							
						addResult(result);
						if(result.size()==0)
							Window.alert("No student exists for this search criteria");

					}

				});
	}

	public class BackButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			MainPage mainpage = new MainPage();
			// vpanel.add(addStudentpage);

		}
	}

	public class DeleteClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			
			
			List<Student> visibleItems = studentgrid.getVisibleItems();
			Student s=new Student();
			for (Student student : visibleItems) {
				if(student.isSelected()){
					boolean doDelete=Window.confirm("Do You really want to delete the student");
					if(doDelete){
				s.setRollNo(student.getRollNo());
				s.setClass_(student.getClass_());
				studentService.deleteStudent(s, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Problem deleting the student");
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						
						StudentSearchResult.this.reloadStudentgrid();
						Window.alert("Student deleted successfully.");
						
					}
				});
				}
			}}
		}
	}

	public class UpdateClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {

		}
	}

	private void addResult(final List<Student> result) {
		vpanel.clear();
		VerticalPanel vpanel1 = new VerticalPanel();
		HorizontalPanel hpanel = new HorizontalPanel();
		SubmitButton backbutton = new SubmitButton("Back");
		// backbutton.setWidth("1");
		backbutton.addClickHandler(new BackButtonClickHandler());
		hpanel.add(backbutton);
		vpanel.add(hpanel);
		studentgrid = new DataGrid<Student>();
		studentgrid.setRowData(result);
		createGridColumns();
		studentgrid.setSize("400px", "400px");
		SingleSelectionModel<Student> selectionModel = new SingleSelectionModel<Student>();
		studentgrid.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(getSelectionChangeListener());
		
		HorizontalPanel h1 = new HorizontalPanel();
		vpanel.remove(vpanel1);
		vpanel.add(headerPanel);
		vpanel1 = new VerticalPanel();
		vpanel1.add(studentgrid);
		vpanel1.setBorderWidth(1);
		vpanel.add(vpanel1);
		Button updatebutton = new Button("Update", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				System.out.println(studentgrid.getColumn(0).getValue(
						result.get(0)));
			}
		});
		Button deletebutton = new Button("Delete");
		h1.add(updatebutton);
		h1.add(deletebutton);
		vpanel.add(h1);

		/*
		 * for (Student student : result) {
		 * if(studentgrid.getRowContainer().getAbsoluteLeft()){
		 * 
		 * } }
		 */

		updatebutton.addClickHandler(new UpdateClickHandler());
		deletebutton.addClickHandler(new DeleteClickHandler());
		vpanel.add(deletebutton);

	}
	
	private void reloadStudentgrid(){
		String name = srchNametb.getValue();
		String rollNo = srchRNtb.getValue();
		String strd = srchStrdtb.getValue();
		search(name, rollNo, strd);
	}

	private Handler getSelectionChangeListener() {
		Handler handler = new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				System.out.println(event.getSource());
				int keyboardSelectedColumn = studentgrid.getKeyboardSelectedColumn();
				int keyboardSelectedRow = studentgrid.getKeyboardSelectedRow();
				System.out.println(keyboardSelectedColumn);
				System.out.println(keyboardSelectedRow);
			}
		};
		return handler ;
	}

	private void createGridColumns() {
		final Column<Student, Boolean> checkBoxColumn = new Column<Student, Boolean>(
				new CheckboxCell(false, false)) {
			@Override
			public Boolean getValue(final Student object) {
				return object.isSelected();
			}
		};
		
		checkBoxColumn.setFieldUpdater(new FieldUpdater<Student, Boolean>() {

			@Override
			public void update(int index, Student object, Boolean value) {
				object.setSelected(value);
			}
		});
		
		
		final EditTextCell nameedit = new EditTextCell();
		final Column<Student, String> namecolumn = new Column<Student, String>(
				nameedit) {

			@Override
			public String getValue(final Student object) {
				return object.getName();
			}
		};
		
		namecolumn.setFieldUpdater(new FieldUpdater<Student, String>() {

			@Override
			public void update(int index, Student object, String value) {
				object.setName(value);
			}
		});
		
		final EditTextCell rnedit = new EditTextCell();
		final Column<Student, String> rncolumn = new Column<Student, String>(
				rnedit) {

			@Override
			public String getValue(final Student object) {
				return Integer.toString(object.getRollNo());
			}
		};
		rncolumn.setFieldUpdater(new FieldUpdater<Student, String>() {

			@Override
			public void update(int index, Student object, String value) {
				// TODO Auto-generated method stub
				UIValidator validator=new UIValidator();
				if(validator.validateRollNo(value))
				object.setRollNo(Integer.parseInt(value));
			}
		});

		final DatePickerCell dobedit = new DatePickerCell();
		final Column<Student, Date> dobcolumn = new Column<Student, Date>(
				dobedit) {

			@Override
			public Date getValue(final Student object) {
				return object.getDob();
			}
		};
		dobcolumn.setFieldUpdater(new FieldUpdater<Student, Date>() {

			@Override
			public void update(int index, Student object, Date value) {
				// TODO Auto-generated method stub
				object.setDob(value);
			}
		});

		List<String> standardList=getStandardList();
		SelectionCell classedit=new SelectionCell(standardList);
		
		final Column<Student, String> classcolumn = new Column<Student, String>(
				classedit) {

			@Override
			public String getValue(final Student object) {
				return object.getClass_();
			}
		};
		classcolumn.setFieldUpdater(new FieldUpdater<Student, String>() {

			@Override
			public void update(int index, Student object, String value) {
				// TODO Auto-generated method stub
				object.setClass_(value);
			}
		});
		studentgrid.addColumn(checkBoxColumn, "Select");
		studentgrid.addColumn(namecolumn, "Name");
		studentgrid.addColumn(rncolumn, "Roll No");
		studentgrid.addColumn(dobcolumn, "Date Of Birth");
		studentgrid.addColumn(classcolumn, "Standard");
	}
	private static List<String> getStandardList(){
		List<String> standardList=new ArrayList<String>();
		standardList.add(0, "I");
		standardList.add(0, "II");
		standardList.add(0, "III");
		standardList.add(0, "IV");
		standardList.add(0, "V");
		standardList.add(0, "VI");
		standardList.add(0, "VII");
		standardList.add(0, "VIII");
		standardList.add(0, "IX");
		standardList.add(0, "X");
		return standardList;
	}

}
