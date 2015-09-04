package com.ali.client;

import java.util.Date;

import com.ali.shared.Student;
import com.ali.shared.UIValidator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class AddStudent extends Composite {
	TextBox tb1;
	TextBox tb2;
	
	DateBox tb3;
	ListBox tb4;
	
	private final StudentServiceAsync studentService = GWT
			.create(StudentService.class);
	VerticalPanel vpanel = new VerticalPanel();
	
	public AddStudent(){
		initWidget(vpanel);
		vpanel.setBorderWidth(1);
		HorizontalPanel hp1 = new HorizontalPanel();
		Label name = new Label("Name:");
		tb1 = new TextBox();
		hp1.add(name);
		hp1.add(tb1);
		
		HorizontalPanel hp2 = new HorizontalPanel();
		Label rollNo = new Label("RollNo:");
		tb2 = new TextBox();
		hp2.add(rollNo);
		hp2.add(tb2);
		
		HorizontalPanel hp3 = new HorizontalPanel();
		Label dob = new Label("Date Of Birth:");
		tb3 = new DateBox();
		hp3.add(dob);
		hp3.add(tb3);

		HorizontalPanel hp4 = new HorizontalPanel();
		Label class_ = new Label("Class:");
		tb4 = new ListBox();
		hp4.add(class_);
		hp4.add(tb4);
		tb4.addItem("I", "I");
		tb4.addItem("II", "II");
		tb4.addItem("III", "III");
		tb4.addItem("IV", "IV");
		tb4.addItem("V", "V");
		tb4.addItem("VI", "VI");
		tb4.addItem("VII", "VII");
		tb4.addItem("VIII", "VIII");
		tb4.addItem("IX", "IX");
		tb4.addItem("X", "X");
		
		HorizontalPanel hpanel=new HorizontalPanel();
		SubmitButton backbutton=new SubmitButton("Back");
		backbutton.setWidth("1");
		backbutton.addClickHandler(new BackButtonClickHandler());
		hpanel.add(backbutton);
		vpanel.add(hpanel);
		
		vpanel.add(hp1);
		vpanel.add(hp2);
		vpanel.add(hp3);
		vpanel.add(hp4);
		SubmitButton save = new SubmitButton("Save", new ButtonClickHandler());
		vpanel.add(save);
		
		vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		RootPanel.get().clear();
		RootPanel.get().add(this);
		
	}
	public class ButtonClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			String name = tb1.getText();
			String rollNo=tb2.getText();
			Date dob = tb3.getValue();
			String _class = tb4.getItemText(tb4.getSelectedIndex());
			
			boolean isvalid=this.validateStudent(name,rollNo,dob);
			if(isvalid){
			int rollNumber=Integer.parseInt(rollNo);
			Student s = new Student(name,rollNumber, dob, _class);
			
			
			
			studentService.saveStudent(s, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub

					System.out.println("Success");

				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					System.out.println("Failure");

				}
			}
			);

		}}
		public boolean validateStudent(String name,String rollNo,Date dob) {
			UIValidator sv=new UIValidator();
			return sv.validateStudent(name, rollNo, dob);
		}
		
		
			
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


