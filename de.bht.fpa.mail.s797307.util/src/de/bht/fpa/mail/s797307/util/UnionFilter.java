package de.bht.fpa.mail.s797307.util;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class UnionFilter extends CombinationFilter {

  public UnionFilter(IFilter... filters) {
    for (IFilter filter : filters) {
      addFilter(filter);
    }
  }

  @Override
  public Set<Message> filter(Iterable<Message> messages) {
    Set<Message> results = new TreeSet<Message>();
    for (IFilter filter : getFilters()) {
      results.addAll(filter.filter(messages));
    }
    return results;
  }
}