package de.bht.fpa.mail.s797307.util;

import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public abstract class Filter implements IFilter {
  public final IFilter parentFilter;

  public Filter(IFilter filter) {
    this.parentFilter = filter;
  }

  public Set<Message> filterWithParent(Iterable<Message> messagesToFilter) {
    return parentFilter.filter(messagesToFilter);
  }

  @Override
  public abstract Set<Message> filter(Iterable<Message> messagesToFilter);

  public IFilter getFilter() {
    return parentFilter;
  }

}
