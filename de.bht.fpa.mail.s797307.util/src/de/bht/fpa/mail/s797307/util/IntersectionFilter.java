package de.bht.fpa.mail.s797307.util;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class IntersectionFilter extends CombinationFilter implements IFilter {

  public IntersectionFilter(IFilter... filters) {
    for (IFilter filter : filters) {
      addFilter(filter);
    }
  }

  @Override
  public Set<Message> filter(Iterable<Message> messages) {
    Set<Message> results = new TreeSet<Message>();
    for (Message message : messages) {
      results.add(message);
    }
    for (IFilter filter : getFilters()) {
      results.retainAll(filter.filter(results));
    }
    return results;
  }
}
