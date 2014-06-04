package com.example.equationProject.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("equation")
public interface EquationService extends RemoteService {
	String equationServer(int a , int b , int c) throws IllegalArgumentException;
}
