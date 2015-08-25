package com.ali.client;

import java_cup.lalr_item;
import sun.font.TextLabel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.impl.PopupImpl;

public class MainPage extends Composite {
	VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hp=new HorizontalPanel();
	TextBox txt;
	public MainPage(){
		initWidget(vpanel);
		
		Anchor addStudent=new Anchor("Add Student");
		Anchor searchStudent=new Anchor("Search Student");
		hp.add(searchStudent);
		Anchor updateStudent=new Anchor("Update Student");
		Anchor DeleteStudent=new Anchor("Delete Student");
		addStudent.addClickHandler(new AddStudentClickHandler());
		searchStudent.addClickHandler(new SearchStudentClickHandler());
		
		//RootPanel.get().setWidgetPosition(vpanel, 10, 10);
		
		RootPanel.get().clear();
		
		vpanel.add(addStudent);
		vpanel.add(searchStudent);
		vpanel.add(updateStudent);
		vpanel.add(DeleteStudent);
		RootPanel.get().add(this);
	}
	public class AddStudentClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			AddStudent addStudentpage=new AddStudent();
			
			//vpanel.add(addStudentpage);
			
		}
	}
	public class SearchStudentClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			RootPanel.get().clear();
			PopupPanel p= new PopupPanel();
			p.setSize("50pxl", "1em");
			p.setPopupPosition(100, 100);
			HorizontalPanel hpanel=new HorizontalPanel();
			Label l = new Label("Enter Name:");
			 txt=new TextBox();
			Button go=new Button("Go");
			go.addClickHandler(new GoBttonHandler());
			hp.add(l);
			hp.add(txt);
			hp.add(go);
			p.add(hp);
			txt.setName("Enter Name");
			RootPanel.get().add(p);
			
			//vpanel.add(addStudentpage);
			
		}
	}
	public class GoBttonHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
					String name=txt.getText();
					StudentSearchResult searcResult=new StudentSearchResult(name);
					RootPanel.get().clear();
					RootPanel.get().add(searcResult);
					
				}
			
			//vpanel.add(addStudentpage);
			
		}
	

}
