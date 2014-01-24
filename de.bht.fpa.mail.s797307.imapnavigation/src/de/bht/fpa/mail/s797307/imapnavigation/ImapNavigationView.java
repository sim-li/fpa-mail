package de.bht.fpa.mail.s797307.imapnavigation;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.builder.AccountBuilder;
import de.bht.fpa.mail.s000000.common.mail.model.builder.FolderBuilder;
import de.bht.fpa.mail.s000000.common.mail.model.builder.MessageBuilder;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s797307.util.FolderNode;

public final class ImapNavigationView extends ViewPart {
  private TreeViewer viewer;

  @Override
  public void createPartControl(Composite parent) {
    ImapViewLabelProvider l = new ImapViewLabelProvider();
    ImapContentProvider cp = new ImapContentProvider(this);
    viewer = new TreeViewer(parent);
    viewer.setLabelProvider(l);
    viewer.setContentProvider(cp);
    viewer.setInput(createModel());
    getSite().setSelectionProvider(viewer);
    // getViewSite().getPage().addSelectionListener(new
    // NavigationListener(this));
    initalizeExecutionListener();
  }

  private FolderNode createModel() {
	 
	  RandomTestDataProvider data = new RandomTestDataProvider(20);
	  
	 
	  

	  
	  
	  
	  Folder sent = FolderBuilder.newFolderBuilder()
			  .id(4711L)
			     .fullName("Sent").builtMessages(data.getMessages()).build();
	  
	  Folder in = FolderBuilder.newFolderBuilder()
			  .id(4718L)
			     .fullName("In").build();
	  in.setMessages(data.getMessages());
	  
	  List <Folder> folders = new LinkedList <Folder>();
	  folders.add(sent);
	  folders.add(in);
	  
	  Account account = AccountBuilder.newAccountBuilder()
		      .id(4711L)
		      	.name("Alice-IMAP")
		      		.host("googlemail.com").build();
	  account.setFolders(folders);
	  
	  
	  FolderNode baseFolder = new FolderNode();
	  baseFolder.addAccount(account);
	  
	 
	  return baseFolder;
  }

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
