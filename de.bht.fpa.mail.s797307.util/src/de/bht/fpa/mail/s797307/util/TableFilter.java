package de.bht.fpa.mail.s797307.util;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class TableFilter extends ViewerFilter {
  private IFilter filter;

  public TableFilter(IFilter filter) {
    this.filter = filter;
  }

  public void setFilter(IFilter filter) {
    this.filter = filter;
  }

  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    Collection<Message> uselessCollection = new ArrayList<Message>();
    if (element instanceof Message) {
      uselessCollection.add((Message) element);
      uselessCollection = filter.filter(uselessCollection);
      if (uselessCollection.size() > 0) {
        return true;
      }
    }
    return false;
  }

}
