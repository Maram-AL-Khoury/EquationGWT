package com.example.equationProject.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EquationServiceAsync {
	void equationServer(int a , int b , int c, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
