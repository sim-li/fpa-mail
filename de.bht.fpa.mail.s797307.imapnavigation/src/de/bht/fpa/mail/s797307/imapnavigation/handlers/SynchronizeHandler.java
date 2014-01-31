package de.bht.fpa.mail.s797307.imapnavigation.handlers;

import java.io.File;
import java.util.Collection;

import javax.xml.bind.JAXB;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.imapsync.SynchronizationException;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.builder.AccountBuilder;
import de.bht.fpa.mail.s797307.imapnavigation.MailJob;

public class SynchronizeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final Account accountx = AccountBuilder.newAccountBuilder().id(4711L)
				.name("FPA Demo").host("imap.a-studios.org")
				.username("fpademo@a-studios.org").password("fpademo").build();
		JAXB.marshal(accountx, new File("/Users/funkjaymatada/test.xml"));
		
		final Account account = JAXB.unmarshal(new File("/Users/funkjaymatada/test.xml"), Account.class);
		
		Job job = new MailJob(account);
		job.schedule();
		return null;
	}
	
	public static void printMsg(Collection <Message> c) {
		for (Message m : c) {
			System.out.println(m.getSubject());
		}
	}
	public static void syncMsg(Account account, IProgressMonitor monitor) {
		try {
			ImapHelper.syncAllFoldersToAccount(account, monitor);
		} catch (SynchronizationException e) {
			System.err.println("Sync error");
		}
	}
}
