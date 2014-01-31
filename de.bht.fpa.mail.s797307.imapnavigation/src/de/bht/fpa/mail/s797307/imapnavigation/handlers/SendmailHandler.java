package de.bht.fpa.mail.s797307.imapnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s797307.imapnavigation.NewMessageView;

public class SendmailHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	    NewMessageView dialog = new  NewMessageView(window.getShell());
	    if (dialog.open() == Window.OK) {
		    System.out.println(dialog.getRecipient());
		    System.out.println(dialog.getSubject());
		    System.out.println(dialog.getMessage());
	    }
	    return null;
	}

}
