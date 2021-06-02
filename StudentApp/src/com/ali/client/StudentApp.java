package com.ali.client;

import java.util.Date;

import com.ali.shared.Student;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StudentApp implements EntryPoint {

	VerticalPanel vpanel=new VerticalPanel();
	private final StudentServiceAsync studentService = GWT
			.create(StudentService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		MainPage mainpage=new MainPage();
		vpanel.add(mainpage);
		RootPanel.get().add(vpanel);
		
	}
	
	
}
