package de.bht.fpa.mail.s797307.maillist;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.TableViewer;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;

public class MaillistExecutionController implements IExecutionListener {
  private final TableViewer tableViewer;

  public MaillistExecutionController(TableViewer tableViewer) {
    this.tableViewer = tableViewer;
  }

  @Override
  public void notHandled(String commandId, NotHandledException exception) {
    // TODO Auto-generated method stub

  }

  @Override
  public void postExecuteFailure(String commandId, ExecutionException exception) {
    // TODO Auto-generated method stub

  }

  @Override
  public void postExecuteSuccess(String commandId, Object returnValue) {
    if (returnValue != null && returnValue instanceof List<?>) {
      List<FilterCombination> filterCombinations = (List<FilterCombination>) returnValue;
      for (FilterCombination combination : filterCombinations) {
        System.out.println("VAL: " + combination.getFilterValue() + " TYPE:" + combination.getFilterType() + " OP:"
            + combination.getFilterOperator());
        ;
      }
    }
  }

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {
    // TODO Auto-generated method stub

  }

}
