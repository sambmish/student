package com.ali.client;

import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.thirdparty.guava.common.collect.Table;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sun.corba.se.impl.interceptors.PICurrent;

public class MainPage extends Composite {
	VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hp=new HorizontalPanel();
	TextBox srchNametb;
	TextBox srchRNtb;
	TextBox srchStrdtb;
	
	TextBox searchtb;
	public MainPage(){
		initWidget(vpanel);
		
		Anchor addStudent=new Anchor("Add Student");
		Anchor searchStudent=new Anchor("Search/Modify Student");
	
		addStudent.addClickHandler(new AddStudentClickHandler());
		searchStudent.addClickHandler(new SearchStudentClickHandler());
		
		RootPanel.get().clear();
		vpanel.add(addStudent);
		vpanel.add(searchStudent);
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
			VerticalPanel vp=new VerticalPanel();
			 
			
			HorizontalPanel srchNamehp=new HorizontalPanel();
			HorizontalPanel srchRNhp=new HorizontalPanel();
			HorizontalPanel srchStrdhp=new HorizontalPanel();
			
			Label srchNameLbl = new Label("Name:");
			Label srchRNLbl = new Label("Roll No:");
			Label srchStrdLbl = new Label("Standard:");
			
			srchNametb=new TextBox();
			srchRNtb=new TextBox();
			srchStrdtb=new TextBox();
			
			srchNamehp.add(srchNameLbl);
			srchNamehp.add(srchNametb);
			srchRNhp.add(srchRNLbl);
			srchRNhp.add(srchRNtb);
			srchStrdhp.add(srchStrdLbl);
			srchStrdhp.add(srchStrdtb);
			
			Button search=new Button("Search");
			search.addClickHandler(new GoBttonHandler());
			
			vp.add(srchNamehp);
			vp.add(srchRNhp);
			vp.add(srchStrdhp);
			
			vp.add(search);
			
			vp.setSize("300px", "100px");
			
			
			RootPanel.get().add(vp);
			
			//vpanel.add(addStudentpage);
			
		}
	}
	public class GoBttonHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
					
					String name=srchNametb.getValue();
					String rollNo=srchRNtb.getValue();
					String strd=srchStrdtb.getValue();
					
					StudentSearchResult searcResult=new StudentSearchResult(name,rollNo,strd);
					RootPanel.get().add(searcResult);
					
				}
			
			//vpanel.add(addStudentpage);
			
		}
	

}
