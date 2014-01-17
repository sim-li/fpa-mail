package de.bht.fpa.mail.s797307.util.filters;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class UnionFilter extends CombinationFilter {

  public UnionFilter(IFilter... filters) {
    for (IFilter filter : filters) {
      addFilter(filter);
    }
  }

  @Override
  public boolean filter(Message message) {
    if (getFilters().size() == 0) {
      return true;
    }
    boolean isAllowed = false;
    for (BasicFilter filter : getFilters()) {
      isAllowed = isAllowed || filter.filter(message);
    }
    return isAllowed;
  }
}