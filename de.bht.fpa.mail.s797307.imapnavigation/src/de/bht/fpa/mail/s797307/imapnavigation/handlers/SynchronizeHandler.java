package de.bht.fpa.mail.s797307.imapnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import de.bht.fpa.mail.s797307.imapnavigation.AccountManager;

public class SynchronizeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		AccountManager manager = AccountManager.getInstance();
		manager.sync();
		return null;
	}
}
