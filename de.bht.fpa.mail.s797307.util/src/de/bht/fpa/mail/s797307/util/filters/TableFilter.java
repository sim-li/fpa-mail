package de.bht.fpa.mail.s797307.util.filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class TableFilter extends ViewerFilter {
  private BasicFilter filter;

  public TableFilter(BasicFilter filter) {
    this.filter = filter;
  }

  public void setFilter(BasicFilter filter) {
    this.filter = filter;
  }

  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    return filter.filter((Message) element);
  }

}
