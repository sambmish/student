package com.ali.client;

import java.util.List;

import com.ali.shared.Student;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
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
	TextBox srchNametb = new TextBox();
	TextBox srchRNtb = new TextBox();
	TextBox srchStrdtb = new TextBox();
	VerticalPanel headerPanel = new VerticalPanel();

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
		final DataGrid<Student> studentgrid = new DataGrid<Student>();
		HorizontalPanel hpanel = new HorizontalPanel();
		SubmitButton backbutton = new SubmitButton("Back");
		// backbutton.setWidth("1");
		backbutton.addClickHandler(new BackButtonClickHandler());
		hpanel.add(backbutton);
		vpanel.add(hpanel);

		studentgrid.setRowData(result);
		Column<Student, Boolean> checkBoxColumn = new Column<Student, Boolean>(
				new CheckboxCell(false, false)) {
			@Override
			public Boolean getValue(Student object) {
				return null;
			}
		};

		EditTextCell nameedit = new EditTextCell();
		Column<Student, String> namecolumn = new Column<Student, String>(
				nameedit) {

			@Override
			public String getValue(Student object) {
				return object.getName();
			}
		};
		EditTextCell rnedit = new EditTextCell();
		Column<Student, String> rncolumn = new Column<Student, String>(rnedit) {

			@Override
			public String getValue(Student object) {
				return Integer.toString(object.getRollNo());
			}
		};

		EditTextCell dobedit = new EditTextCell();
		Column<Student, String> dobcolumn = new Column<Student, String>(dobedit) {

			@Override
			public String getValue(Student object) {
				return object.getDob().toString();
			}
		};

		EditTextCell classedit = new EditTextCell();
		Column<Student, String> classcolumn = new Column<Student, String>(
				classedit) {

			@Override
			public String getValue(Student object) {
				return object.getClass_();
			}
		};
		studentgrid.addColumn(checkBoxColumn, "Select");
		studentgrid.addColumn(namecolumn, "Name");
		studentgrid.addColumn(rncolumn, "Roll No");
		studentgrid.addColumn(dobcolumn, "Date Of Birth");
		studentgrid.addColumn(classcolumn, "Standard");
		studentgrid.setSize("400px", "400px");

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

}
