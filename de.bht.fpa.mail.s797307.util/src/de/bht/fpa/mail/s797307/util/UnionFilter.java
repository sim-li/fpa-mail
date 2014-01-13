package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class UnionFilter extends FilterWithList {

  public UnionFilter(IFilter... filters) {
    for (IFilter filter : filters) {
      filterList.add(filter);
    }
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> filterResults = new TreeSet<Message>();
    for (IFilter filter : filterList) {
      filterResults.addAll(filter.filter(messagesToFilter));
    }
    return filterResults;
  }
}