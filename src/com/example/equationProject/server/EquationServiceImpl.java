package com.example.equationProject.server;



import com.example.equationProject.client.EquationService;
import com.example.equationProject.shared.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EquationServiceImpl extends RemoteServiceServlet implements
		EquationService {

	public String equationServer(int a , int b , int c) throws IllegalArgumentException {
		// Verify that the parameters is valid. 
		

		Double delta= Math.pow( b , 2 ) - ( 4*a*c );
		 
		 Double answer1 = 0.0;
		 Double answer2 = 0.0;
		 
		 
		 
		 if( delta > 0 ){
			 
			answer1 = (-b + Math.sqrt(delta)) / ( 2 * a ); 
			
			answer2 = (-b - Math.sqrt(delta)) / ( 2 * a ); 
			 return "Answer1 = "+answer1+" , Answer2 = "+answer2;
			  
		 }
		 
		 else if ( delta == 0){
			 
			 answer1 = (-b ) / ( 2.0 * a );
			 
			 answer2 = (-b ) / ( 2.0 * a );
			 
			 return "Answer1 = "+answer1+" , Answer2 = "+answer2;
		 }
		 else{
		 	 
			 return "Your Equation is Implossible To Solve!!!";
		 }
		   
	 	

	
		

		
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
