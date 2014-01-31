package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s797307.util.MAccount;
import de.bht.fpa.mail.s797307.util.MAccountList;

public final class ImapNavigationView extends ViewPart {
	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		ImapViewLabelProvider l = new ImapViewLabelProvider();
		ImapContentProvider cp = new ImapContentProvider(this);
		viewer = new TreeViewer(parent);
		viewer.setLabelProvider(l);
		viewer.setContentProvider(cp);
		Account account = ImapHelper.getAccount("FPA Demo");
		
		MAccount accountNode = new MAccount(account);
	
		MAccount emptynode = new MAccount(new Account());
		
		Account testAccount = new Account();
		testAccount.setId(20L);
		testAccount.setName("My sherona");
		MAccount testNode = new MAccount(testAccount);
		
		
		MAccountList accounts = new MAccountList();
		accounts.add(accountNode);
		accounts.add(testNode);
	
		for (Object accountRetrieved : accounts.getChildren()) {
			MAccount shia = (MAccount) accountRetrieved;
			Account ac = (Account) shia.getElement();
			System.out.println(ac.getName());
		}
		
		viewer.setInput(accounts);
		
		
		initializeSync();
		getSite().setSelectionProvider(viewer);
		initalizeExecutionListener();
	}

	private void initializeSync() {
		Job.getJobManager().addJobChangeListener(new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
				final IJobChangeEvent myEvent = event;
				if (event.getJob().getName() == "MailJob") {
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							if (myEvent.getResult().isOK()) {
								viewer.setInput(AccountManager.getInput());
							} else {
								System.err
										.println("Hey man, I've got a problem retrieving your mails.");
							}
						}
					});

				}
			}
		});
	}

	public void initalizeExecutionListener() {
		ICommandService commandService = (ICommandService) PlatformUI
				.getWorkbench().getActiveWorkbenchWindow()
				.getService(ICommandService.class);
		commandService.addExecutionListener(new ExecutionController(viewer));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
