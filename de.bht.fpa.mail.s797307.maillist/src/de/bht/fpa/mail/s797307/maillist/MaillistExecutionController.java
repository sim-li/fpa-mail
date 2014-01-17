package de.bht.fpa.mail.s797307.maillist;

import java.util.List;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.TableViewer;
import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;
import de.bht.fpa.mail.s797307.util.filters.CombinationFilter;
import de.bht.fpa.mail.s797307.util.filters.FilterTransfer;
import de.bht.fpa.mail.s797307.util.filters.IntersectionFilter;
import de.bht.fpa.mail.s797307.util.filters.NullFilter;
import de.bht.fpa.mail.s797307.util.filters.TableFilter;
import de.bht.fpa.mail.s797307.util.filters.UnionFilter;
import de.bht.fpa.mail.s797307.util.parser.FilterGenerator;

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
    if (returnValue instanceof NullFilter) {
      tableViewer.resetFilters();
      return;
    }
    if (returnValue != null && returnValue instanceof FilterTransfer) {
      FilterTransfer transfer = (FilterTransfer) returnValue;
      List<FilterCombination> combinations = transfer.getFilterCombination();
      FilterGroupType groupType = transfer.getFilterGroupType();
      CombinationFilter combinationFilter;
      if (groupType.toString() == FilterGroupType.UNION.toString()) {
        combinationFilter = new UnionFilter();
      } else {
        combinationFilter = new IntersectionFilter();
      }
      BasicFilter filter = FilterGenerator.buildFilter(combinationFilter, combinations);
      TableFilter tableFilter = new TableFilter(filter);
      tableViewer.resetFilters();
      tableViewer.addFilter(tableFilter);
      tableViewer.refresh();
    }
  }

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {
    // TODO Auto-generated method stub

  }

}
