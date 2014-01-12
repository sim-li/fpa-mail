package de.bht.fpa.mail.s797307.util.filters;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class UnionFilter implements de.bht.fpa.mail.s000000.common.filter.IFilter {
  private final IFilter[] filters;

  public UnionFilter(IFilter... filters) {
    this.filters = filters;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> filterResults = new TreeSet<Message>();
    for (IFilter filter : filters) {
      filterResults.addAll(filter.filter(messagesToFilter));
    }
    return filterResults;
  }
}