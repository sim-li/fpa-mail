package de.bht.fpa.mail.s797307.util.filters;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class IntersectionFilter extends CombinationFilter implements IFilter {

  public IntersectionFilter(BasicFilter... filters) {
    for (IFilter filter : filters) {
      addFilter(filter);
    }
  }

  @Override
  public boolean filter(Message message) {
    boolean isAllowed = true;
    for (BasicFilter filter : getFilters()) {
      isAllowed = isAllowed && filter.filter(message);
    }
    return isAllowed;
  }
}
