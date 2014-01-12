package de.bht.fpa.mail.s797307.util.filters;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class IntersectionFilter implements IFilter {
  private final IFilter[] filters;

  public IntersectionFilter(IFilter... filters) {
    this.filters = filters;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> filterResults = new TreeSet<Message>();
    for (Message message : messagesToFilter) {
      filterResults.add(message);
    }

    for (IFilter filter : filters) {
      filterResults.retainAll(filter.filter(filterResults));
    }
    return filterResults;
  }
}
