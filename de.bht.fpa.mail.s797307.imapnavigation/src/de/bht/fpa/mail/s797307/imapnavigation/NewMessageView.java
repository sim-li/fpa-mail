package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewMessageView extends Dialog { 
	 private Composite container;
	 private Text tfRecipient;
	 private Text tfSubject;
	 private static final int HEIGHT = 500;
	 private static final int WIDTH = 600;
	 private Text tfMessage;
	 private String recipient;
	 private String subject;
	 private String message;
	 private Label lblMessage;
	public NewMessageView(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE | SWT.APPLICATION_MODAL);
	}
	
	 @Override
	  protected Control createDialogArea(Composite parent) {
	    container = (Composite) super.createDialogArea(parent);
	    container.setLayout(new GridLayout(2, false));
	    new Label(container, SWT.NONE);
	    
	    Label lblTo = new Label(container, SWT.NONE);
	    lblTo.setText("Recipient:");
	    new Label(container, SWT.NONE);
	    
	    tfRecipient = new Text(container, SWT.BORDER);
	    tfRecipient.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    new Label(container, SWT.NONE);
	    
	    Label lblSubject = new Label(container, SWT.NONE);
	    lblSubject.setText("Subject:");
	    new Label(container, SWT.NONE);
	    
	    tfSubject = new Text(container, SWT.BORDER);
	    tfSubject.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    new Label(container, SWT.NONE);
	   
	    lblMessage = new Label(container, SWT.NONE);
	    lblMessage.setText("Message:");
	    new Label(container, SWT.NONE);
	    
	    tfMessage = new Text(container, SWT.BORDER | SWT.WRAP | SWT.MULTI);
	    GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
	    gd_text_2.heightHint = 292;
	    tfMessage.setLayoutData(gd_text_2);
	    getShell().setText("Write new e-mail");
	    return container;
	  }

	 @Override
	  protected void okPressed() {
		 System.out.println("I got it");
	    // Copy data from SWT widgets into fields on button press.
	    // Reading data from the widgets later will cause an SWT
	    // widget diposed exception.
	    recipient = tfRecipient.getText();
	    subject = tfSubject.getText();
		message = tfMessage.getText();
	    super.okPressed();
	  }
	 
	public String getRecipient() {
		return recipient;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getMessage() {
		return message;
	}
	
	protected Point getInitialSize() {
		return new Point(715, 489);
	}
}
