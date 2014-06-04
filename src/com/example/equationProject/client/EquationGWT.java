package com.example.equationProject.client;

import org.apache.tools.ant.taskdefs.Javadoc.Html;

import com.example.equationProject.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EquationGWT implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final EquationServiceAsync EquationService = GWT
			.create(EquationService.class);

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		sendButton.setText("Solve Equation!!");
		final TextBox aField = new TextBox();
		aField.setMaxLength(2);

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("aField");
		rootPanel.add(aField, 281, 291);
		aField.setSize("38px", "18px");
		RootPanel.get("sendButtonContainer").add(sendButton, 205, 456);
		sendButton.setSize("163px", "74px");

		// Focus the cursor on the name field when the app loads
		aField.setFocus(true);
		
		Label albl = new Label("Enter ( a ) parameter :");
		albl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		albl.setSize("244px", "29px");
		aField.selectAll();
		
		
		RootPanel.get("albl").add(albl, 10, 291);
		
		Label blbl = new Label("Enter ( b ) parameter :");
		blbl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rootPanel.add(blbl, 10, 345);
		blbl.setSize("244px", "29px");
		
		Label clbl = new Label("Enter ( c ) parameter :");
		clbl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rootPanel.add(clbl, 10, 397);
		clbl.setSize("244px", "29px");
		
		final TextBox bField = new TextBox();
		bField.setMaxLength(2);
		bField.setFocus(true);
		rootPanel.add(bField, 281, 345);
		bField.setSize("38px", "18px");
		
		final TextBox cField = new TextBox();
		cField.setMaxLength(2);
		cField.setFocus(true);
		rootPanel.add(cField, 281, 397);
		cField.setSize("38px", "18px");
		
     	final	Label erralbl = new Label("");
     	erralbl.setStyleName("gwt-Label-new");
		rootPanel.add(erralbl, 357, 291);
		erralbl.setSize("175px", "20px");
		
		final Label errblbl = new Label("");
		errblbl.setStyleName("gwt-Label-new");
		rootPanel.add(errblbl, 357, 354);
		errblbl.setSize("175px", "20px");
		
		final Label errclbl = new Label("");
		errclbl.setStyleName("gwt-Label-new");
		rootPanel.add(errclbl, 357, 406);
		errclbl.setSize("175px", "20px");
		


		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Squared Equation Results");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		//final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		
		dialogVPanel.add(new HTML("<br><b>Equation Results:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				solveEquation();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					solveEquation();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void solveEquation() {
				// First, we validate the input.
                erralbl.setText("");
                errblbl.setText("");
                errclbl.setText("");
                
				String aParameter = aField.getText();
				
				String bParameter = bField.getText();
				
				String cParameter = cField.getText();
				
				boolean isValid=true;
				
				if (aParameter.trim().equals("") || isNumber(aParameter) == false) {
					erralbl.setText("*Check (a) Parameter");
					isValid=false;
					
				}
				
				if (bParameter.trim().equals("") || isNumber(bParameter) == false) {
					errblbl.setText("*Check (b) Parameter");
					isValid=false;
					
				}
				
				if (cParameter.trim().equals("") || isNumber(cParameter) == false) {
					errclbl.setText("*Check (c) Parameter");
					isValid=false;
					
				}
				
				if(isValid == false){return;}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				
				serverResponseLabel.setText("");
				
				int a = Integer.parseInt(aParameter);
				int b = Integer.parseInt(bParameter);
				int c = Integer.parseInt(cParameter);
				
				EquationService.equationServer( a , b , c,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								

								
								dialogBox.setText("Equation Results");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
			
			public boolean isNumber(String parameter){
				
				boolean isnum=false;
				for(int i=0; i<parameter.length();i++){
				if(parameter.charAt(i) >= '0' && parameter.charAt(i) <= '9'){
				
					isnum=true;}
				else{
					
					return false;
				}
				}
				return isnum;
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		aField.addKeyUpHandler(handler);
	}
}
