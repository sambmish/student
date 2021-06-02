package com.sample.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Validator {

	public void validateAddStudent(String name, String dob, String standard) {
		if(name==null)
			
		Window.alert("wrong student name");
		VerticalPanel childPanel = new VerticalPanel();
		
		childPanel.add(new HTML("wrong user name"));
		Button b = new Button("accept");
		childPanel.add(b);
		
		PopupPanel panel = new PopupPanel(true);
		panel.setSize("300px", "350px");
		panel.center();
		panel.add(childPanel);
		panel.show();
	}
}
