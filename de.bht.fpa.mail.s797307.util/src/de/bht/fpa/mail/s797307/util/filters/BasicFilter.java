package de.bht.fpa.mail.s797307.util.filters;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public abstract class BasicFilter implements IFilter {

  @Override
  public Set<Message> filter(Iterable<Message> messages) {
    Set<Message> allowed = new TreeSet<Message>();
    for (Message message : messages) {
      if (filter(message)) {
        allowed.add(message);
      }
    }
    return allowed;
  }

  public abstract boolean filter(Message message);
}
