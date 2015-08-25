package com.ali.client;

import java.util.Date;

import com.ali.shared.Student;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class AddStudent extends Composite {
	TextBox tb1;
	DateBox tb2;
	TextBox tb3;
	
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
		Label dob = new Label("Date Of Birth:");
		tb2 = new DateBox();
		hp2.add(dob);
		hp2.add(tb2);

		HorizontalPanel hp3 = new HorizontalPanel();
		Label class_ = new Label("Class:");
		tb3 = new TextBox();
		hp3.add(class_);
		hp3.add(tb3);
		
		HorizontalPanel hpanel=new HorizontalPanel();
		SubmitButton backbutton=new SubmitButton("Back");
		backbutton.setWidth("1");
		backbutton.addClickHandler(new BackButtonClickHandler());
		hpanel.add(backbutton);
		vpanel.add(hpanel);
		
		vpanel.add(hp1);
		vpanel.add(hp2);
		vpanel.add(hp3);
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
			Date dob = tb2.getValue();
			/*
			 * try { dob = new SimpleDateFormat().parse(tb2.getText()); }
			 * catch (ParseException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			String _class = tb3.getText();
			Student s = new Student(name, dob, _class);
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
			});

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


