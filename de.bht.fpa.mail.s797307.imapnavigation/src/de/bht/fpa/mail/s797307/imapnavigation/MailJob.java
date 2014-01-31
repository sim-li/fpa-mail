package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.imapsync.SynchronizationException;
import de.bht.fpa.mail.s000000.common.mail.model.Account;

public class MailJob extends Job {
	private Account account;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public MailJob (Account account) {
		super("MailJob");
		this.account = account;
		account.setId(12L);
	}
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			  monitor.beginTask("Doing fancy stuff.", 100);
			  ImapHelper.setDebug(true);
			  ImapHelper.syncAllFoldersToAccount(account, new SubProgressMonitor(monitor, 1));
			  if (monitor.isCanceled()) {
				  return Status.CANCEL_STATUS;
			  }
			  return Status.OK_STATUS;
		  }
		  catch (SynchronizationException e) {
			  System.err.println("Legend of the phoenix.");
			  return Status.CANCEL_STATUS;
		  } finally {
			  monitor.done();
		  }
	}

}
