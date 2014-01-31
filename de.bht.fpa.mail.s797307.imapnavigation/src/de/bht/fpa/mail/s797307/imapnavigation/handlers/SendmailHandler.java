package de.bht.fpa.mail.s797307.imapnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s000000.common.filter.FilterDialog;
import de.bht.fpa.mail.s797307.imapnavigation.NewMessageView;

public class SendmailHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	    NewMessageView sendMail = new  NewMessageView(window.getShell());
	    return sendMail.open();
	

	}

}
