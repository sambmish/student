package com.sample.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sample.shared.Employee;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void doSomething(AsyncCallback<Void> callback);

	void getEmployeeInfo(int id, AsyncCallback<Employee> callback);
}
