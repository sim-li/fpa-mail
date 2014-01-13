package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.filter.IFilter;

public abstract class FilterWithList {
  protected LinkedList<IFilter> filterList;

  public void addFilter(IFilter newFilter) {
    this.filterList.add(newFilter);
  }

  public void removeFilter(IFilter filter) {
    this.filterList.remove(filter);
  }
}
