package com.ali.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPage extends Composite {
	VerticalPanel vpanel = new VerticalPanel();
	HorizontalPanel hp = new HorizontalPanel();
	TextBox srchNametb;
	TextBox srchRNtb;
	TextBox srchStrdtb;

	TextBox searchtb;

	public MainPage() {
		initWidget(vpanel);

		Anchor addStudent = new Anchor("Add Student");
		Anchor searchStudent = new Anchor("Search/Modify Student");

		addStudent.addClickHandler(new AddStudentClickHandler());
		searchStudent.addClickHandler(new SearchStudentClickHandler());

		RootPanel.get().clear();
		vpanel.add(addStudent);
		vpanel.add(searchStudent);
		RootPanel.get().add(this);
	}

	public class AddStudentClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			RootPanel.get().add(new AddStudent());
		}
	}

	public class SearchStudentClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			RootPanel.get().add(new StudentSearchResult());
		}
	}
}
