package de.bht.fpa.mail.s797307.util.filters;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ReadFilter extends Filter {
  private final boolean isRead;

  public ReadFilter(IFilter filter, Boolean isRead) {
    super(filter);
    this.isRead = isRead;
  }

  public ReadFilter(Boolean isRead) {
    super(new NullFilter());
    this.isRead = isRead;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Iterable<Message> messages = filterWithParent(messagesToFilter);
    Set<Message> filteredMessages = new TreeSet<Message>();
    for (Message m : messages) {
      if (m.isRead() == isRead) {
        filteredMessages.add(m);
      }
    }
    return filteredMessages;
  }
}
