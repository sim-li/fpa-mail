package de.bht.fpa.mail.s797307.imapnavigation.handlers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s797307.imapnavigation.NewMessageView;
import de.bht.fpa.mail.s797307.imapnavigation.SendMessage;

public class SendmailHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	    NewMessageView dialog = new  NewMessageView(window.getShell());
	    if (dialog.open() == Window.OK) {
	    	Account gmailSend = new Account();
	    	gmailSend.setHost("smtp.googlemail.com");
	    	gmailSend.setUsername("bhtfpa@googlemail.com");
	    	gmailSend.setPassword("B-BgxkT_anr2bubbyTLM");	
	    	SendMessage.send(gmailSend, dialog.getRecipient(), dialog.getSubject(), dialog.getMessage());
	    }
	    return null;
	}

}
