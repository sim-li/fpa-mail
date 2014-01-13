package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.filter.IFilter;

public abstract class FilterWithList {
  protected LinkedList<IFilter> filterList = new LinkedList<IFilter>();

  public void addFilter(IFilter newFilter) {
    filterList.add(newFilter);
  }

  public void removeFilter(IFilter filter) {
    filterList.remove(filter);
  }
}
