package de.bht.fpa.mail.s797307.imapnavigation.handlers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.imapsync.SynchronizationException;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.builder.AccountBuilder;
import de.bht.fpa.mail.s000000.common.mail.model.builder.FolderBuilder;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s797307.imapnavigation.MailJob;

public class SynchronizeHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	  // Boiler-Plate
						  RandomTestDataProvider data = new RandomTestDataProvider(20);
						  Folder sent = FolderBuilder.newFolderBuilder()
								  .id(4711L)
								     .fullName("Sent").build();
						  
						  Folder in = FolderBuilder.newFolderBuilder()
								  .id(4718L)
								     .fullName("In").build();
						  	  
						  List <Folder> folders = new LinkedList <Folder>();
						  folders.add(sent);
						  folders.add(in);
	
	  final Account account = AccountBuilder.newAccountBuilder()
		      .id(4711L)
		      	.name("FPA Demo")
		      		.host("imap.a-studios.org")
		      		  .username("fpademo@a-studios.org")
		      		  	.password("fpademo")
		      		  		.build();
	  
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
