package de.bht.fpa.mail.s797307.maillist.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterDialog;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s797307.util.FilterTransfer;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class FilterConfigHandler extends AbstractHandler {
  /**
   * The constructor.
   */
  public FilterConfigHandler() {
  }

  /**
   * the command has been executed, so extract extract the needed information
   * from the application context.
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    FilterDialog filterDialog = new FilterDialog(window.getShell());
    Object returnCode = filterDialog.open();
    FilterGroupType filterGroupType = filterDialog.getFilterGroupType();
    List<FilterCombination> filterCombinations = filterDialog.getFilterCombinations();
    if ((Integer) returnCode != Window.OK || filterCombinations == null) {
      return null;
    }
    return new FilterTransfer(filterGroupType, filterCombinations);
  }
}
