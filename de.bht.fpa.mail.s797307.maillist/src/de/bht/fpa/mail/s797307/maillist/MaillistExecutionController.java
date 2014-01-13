package de.bht.fpa.mail.s797307.maillist;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.TableViewer;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
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
        Object filterValue = combination.getFilterValue();
        switch (filterType) {
        case IMPORTANCE:
          filter.addFilter(new ImportanceFilter((Importance) filterValue));
          break;
        case SENDER:
          filter.addFilter(new SenderFilter((String) filterValue, filterOperator));
          break;
        case RECIPIENTS:
          filter.addFilter(new RecipientsFilter((String) filterValue, filterOperator));
          break;
        case SUBJECT:
          filter.addFilter(new SubjectFilter((String) filterValue, filterOperator));
          break;
        case TEXT:
          filter.addFilter(new TextFilter((String) filterValue, filterOperator));
          break;
        case READ:
          boolean filterValueAsBool = Boolean.parseBoolean((String) filterValue);
          filter.addFilter(new ReadFilter(filterValueAsBool));
          break;
        }
      }
    }
    // System.out.println(tableViewer.getInput().getClass());
  }

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {
    // TODO Auto-generated method stub

  }

}
