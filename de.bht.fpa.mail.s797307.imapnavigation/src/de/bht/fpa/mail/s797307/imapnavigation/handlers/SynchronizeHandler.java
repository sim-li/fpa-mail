package de.bht.fpa.mail.s797307.imapnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

import de.bht.fpa.mail.s797307.imapnavigation.AccountManager;

public class SynchronizeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Job mailJob = new Job("Synchronizing Mail") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Doing fancy stuff.", 100);
				return AccountManager.syncAll(monitor);
			}

			
		};
		return null;
	}
}
