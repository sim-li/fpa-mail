package de.bht.fpa.mail.s797307.imapnavigation;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.builder.AccountBuilder;
import de.bht.fpa.mail.s000000.common.mail.model.builder.FolderBuilder;
import de.bht.fpa.mail.s000000.common.mail.testdata.MessageTestDataProvider;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;

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
	  MessageTestDataProvider testMsgProvider = new MessageTestDataProvider();
	  RandomTestDataProvider randomTestDataProvider = new RandomTestDataProvider(30);
	  testMsgProvider.setTestDataProvider(randomTestDataProvider);
	  FolderBuilder folderBuilder = FolderBuilder.newFolderBuilder();
	  folderBuilder.builtMessages(testMsgProvider.getMessages());
	  AccountBuilder accountBuilder = AccountBuilder.newAccountBuilder();
	  accountBuilder.folder(folderBuilder);
	  accountBuilder.id(new Long(1));
	  accountBuilder.host("mysherona.com");
	  accountBuilder.name("Rudolf Frankelstein");
	  accountBuilder.password("Easy rider");
	  accountBuilder.username("SamuelRudolfson");
	  Account account = accountBuilder.build();
	  return new FolderNode(account);
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
