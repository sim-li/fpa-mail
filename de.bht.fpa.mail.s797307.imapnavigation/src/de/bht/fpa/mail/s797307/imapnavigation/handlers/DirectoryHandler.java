package de.bht.fpa.mail.s797307.imapnavigation.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s797307.util.TFile;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class DirectoryHandler extends AbstractHandler {
  /**
   * The constructor.
   */
  public DirectoryHandler() {
  }

  /**
   * the command has been executed, so extract extract the needed information
   * from the application context.
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    DirectoryDialog openDialog = new DirectoryDialog(window.getShell());
    String filename = openDialog.open();
    if (filename == null) {
      return null;
    }
    TFile selection = new TFile(new File(filename));
//    HistoryManager.getInstance().add(selection.getFile());
//    HistoryManager.getInstance().saveHistory();
    return selection;
  }
}
