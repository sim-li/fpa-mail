package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.filter.IFilter;

public abstract class CombinationFilter implements de.bht.fpa.mail.s000000.common.filter.IFilter {
  private final LinkedList<IFilter> filters = new LinkedList<IFilter>();

  public void addFilter(IFilter newFilter) {
    filters.add(newFilter);
  }

  public void addFilters(IFilter... filters) {
    for (IFilter filter : filters) {
      this.filters.add(filter);
    }
  }

  public void removeFilters(IFilter... filters) {
    for (IFilter filter : filters) {
      this.filters.remove(filter);
    }
  }

  public void removeFilter(IFilter filter) {
    filters.remove(filter);
  }

  public LinkedList<IFilter> getFilters() {
    return filters;
  }
}
