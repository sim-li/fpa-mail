package de.bht.fpa.mail.s797307.util.filters;

import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.filter.IFilter;

public abstract class CombinationFilter extends BasicFilter implements de.bht.fpa.mail.s000000.common.filter.IFilter {
  private final LinkedList<BasicFilter> filters = new LinkedList<BasicFilter>();

  public void addFilter(IFilter newFilter) {
    filters.add((BasicFilter) newFilter);
  }

  public void addFilter(BasicFilter newFilter) {
    filters.add(newFilter);
  }

  public void addFilters(BasicFilter... filters) {
    for (IFilter filter : filters) {
      this.filters.add((BasicFilter) filter);
    }
  }

  public void addFilters(IFilter... filters) {
    for (IFilter filter : filters) {
      this.filters.add((BasicFilter) filter);
    }
  }

  public void removeFilters(BasicFilter... filters) {
    for (IFilter filter : filters) {
      this.filters.remove(filter);
    }
  }

  public void removeFilters(IFilter... filters) {
    for (IFilter filter : filters) {
      this.filters.remove(filter);
    }
  }

  public void removeFilter(BasicFilter filter) {
    filters.remove(filter);
  }

  public void removeFilter(IFilter filter) {
    filters.remove(filter);
  }

  public LinkedList<BasicFilter> getFilters() {
    return filters;
  }
}
