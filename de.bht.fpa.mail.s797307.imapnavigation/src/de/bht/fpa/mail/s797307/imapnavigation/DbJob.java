package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Monitor;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.imapsync.SynchronizationException;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;

public class DbJob extends Job {
	private Account account;
	private Monitor monitor;

	 public DbJob(String name) {
		 super(name);
	 }
	 
	 public DbJob(String name, Account account) {
		 super(name);
		 this.account = account;
	 }
	 
	 public DbJob(String name, Account account, Monitor monitor) {
		 super(name);
		 this.account = account;
		 this.monitor = monitor;
	 }
	 
	 public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	protected IStatus run(IProgressMonitor monitor) {
		if (account == null) {
			System.err.println("No account specified, man.");
		}
    	try {
			ImapHelper.syncAllFoldersToAccount(account, monitor);
		} catch (SynchronizationException e) {
			System.out.println("Your moma made that?");
		}
        return Status.OK_STATUS;
      }
}
