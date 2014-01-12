package de.bht.fpa.mail.s797307.util.filters;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class SubjectFilter extends Filter {
  String searchString;
  FilterOperator operator;

  public SubjectFilter(IFilter filter, String searchString, FilterOperator operator) {
    super(filter);
    this.searchString = searchString;
    this.operator = operator;
  }

  public SubjectFilter(String searchString) {
    super(new NullFilter());
    this.searchString = searchString;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Iterable<Message> messages = filterWithParent(messagesToFilter);
    Set<Message> filteredMessages = new TreeSet<Message>();
    for (Message m : messages) {
      if (StringCompareHelper.matches(m.getSubject(), searchString, operator)) {
        filteredMessages.add(m);
      }
    }
    return filteredMessages;
  }
}