package de.bht.fpa.mail.s797307.maillist;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.TableViewer;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s797307.util.FilterOperator;
import de.bht.fpa.mail.s797307.util.FilterWithList;
import de.bht.fpa.mail.s797307.util.ImportanceFilter;
import de.bht.fpa.mail.s797307.util.IntersectionFilter;
import de.bht.fpa.mail.s797307.util.ReadFilter;
import de.bht.fpa.mail.s797307.util.RecipientsFilter;
import de.bht.fpa.mail.s797307.util.SenderFilter;
import de.bht.fpa.mail.s797307.util.SubjectFilter;
import de.bht.fpa.mail.s797307.util.TextFilter;
import de.bht.fpa.mail.s797307.util.UnionFilter;

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
    if (returnValue != null && returnValue instanceof FilterTransfer) {
      FilterTransfer transfer = (FilterTransfer) returnValue;
      List<FilterCombination> combinations = transfer.getFilterCombination();
      FilterGroupType groupType = transfer.getFilterGroupType();
      FilterWithList filter = new IntersectionFilter();
      if (groupType.toString() == groupType.UNION.toString()) {
        filter = new UnionFilter();
      }
      for (FilterCombination combination : combinations) {
        FilterType filterType = combination.getFilterType();
        FilterOperator filterOperator = combination.getFilterOperator();
        FilterValue filterValue = combination.getFilterValue();
        switch (filterType) {
        case IMPORTANCE:
          if (!(combination.getFilterValue() instanceof Importance)) {
            break;
          }
          filter.addFilter(new ImportanceFilter((Importance) combination.getFilterValue()));
          break;
        case SENDER:
          if (!(combination.getFilterValue() instanceof String)) {
            break;
          }
          filter.addFilter(new SenderFilter((String) combination.getFilterValue(), combination.getFilterOperator()));
          break;
        case RECIPIENTS:
          if (!(combination.getFilterValue() instanceof String)) {
            break;
          }
          filter
              .addFilter(new RecipientsFilter((String) combination.getFilterValue(), combination.getFilterOperator()));
          break;
        case SUBJECT:
          if (!(combination.getFilterValue() instanceof String)) {
            break;
          }
          filter.addFilter(new SubjectFilter((String) combination.getFilterValue(), combination.getFilterOperator()));
          break;
        case TEXT:
          if (!(combination.getFilterValue() instanceof String)) {
            break;
          }
          filter.addFilter(new TextFilter((String) combination.getFilterValue(), combination.getFilterOperator()));
          break;
        case READ:
          if (!(combination.getFilterValue() instanceof String)) {
            break;
          }
          filter.addFilter(new ReadFilter(Boolean.parseBoolean((String) combination.getFilterValue())));
          break;
        }
      }
    }
    System.out.println(tableViewer.getInput().getClass());
  }

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {
    // TODO Auto-generated method stub

  }

}
