package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s797307.util.MAccount;
import de.bht.fpa.mail.s797307.util.MTargetNode;

public final class ImapNavigationView extends ViewPart {
  private TreeViewer viewer;

  @Override
  public void createPartControl(Composite parent) {
    ImapViewLabelProvider l = new ImapViewLabelProvider();
    ImapContentProvider cp = new ImapContentProvider(this);
    viewer = new TreeViewer(parent);
    viewer.setLabelProvider(l);
    viewer.setContentProvider(cp);
//    viewer.setInput(createModel());
    initializeSync();
    getSite().setSelectionProvider(viewer);
    initalizeExecutionListener();
  }

//  private MTargetNode createModel() {
  private void initializeSync() {
	  RandomTestDataProvider data = new RandomTestDataProvider(20);
	  Job.getJobManager().addJobChangeListener(new JobChangeAdapter() {
		  public void done(IJobChangeEvent event) {
			  if (event.getJob().getName() == "MailJob") {
				  if (event.getResult().isOK()) {
					  MailJob job = (MailJob) event.getJob();
					  System.out.println(job.getAccount().getId());
//					  MTargetNode rootNode = new MAccount(job.getAccount());
//					  System.out.println(rootNode.getMessages().isEmpty());
//					  viewer.setInput(rootNode);
//					  viewer.refresh();
					  System.out.println("Job completed successfully, viewer fresh.");
				  } else {
					  System.out.println("Job did not complete successfully");
				  }
			  }
		  }
	  });	
  }
	  
////	  
////	  Folder sent = FolderBuilder.newFolderBuilder()
////			  .id(4711L)
////			     .fullName("Sent").builtMessages(data.getMessages()).build();
////	  
////	  Folder in = FolderBuilder.newFolderBuilder()
////			  .id(4718L)
////			     .fullName("In").build();
////	  in.setMessages(data.getMessages());
////	  
////	  List <Folder> folders = new LinkedList <Folder>();
////	  folders.add(sent);
////	  folders.add(in);
////	  
////	  Account account = AccountBuilder.newAccountBuilder()
////		      .id(4711L)
////		      	.name("Alice-IMAP")
////		      		.host("googlemail.com").build();
////	  account.setFolders(folders);
////	  
//	  
////	  MTargetNode rootNode = new MAccount(account);
//	  return rootNode;
//  }

  public void setInput(Object input) {
    viewer.setInput(input);
  }

  public void initalizeExecutionListener() {
    ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
        .getService(ICommandService.class);
    commandService.addExecutionListener(new ExecutionController(viewer));
  }

  @Override
  public void setFocus() {
    // TODO Auto-generated method stub

  }
}
