package de.bht.fpa.mail.s797307.util.filters;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;

public class ImportanceFilter extends Filter {
  Importance importance;

  public ImportanceFilter(IFilter filter, Importance importance) {
    super(filter);
    this.importance = importance;
  }

  public ImportanceFilter(Importance importance) {
    super(new NullFilter());
    this.importance = importance;
  }

  public static void main(String[] args) {
    RandomTestDataProvider tdp = new RandomTestDataProvider(30);
    ImportanceFilter iFilter = new ImportanceFilter(Importance.LOW);
    Iterable<Message> messages = iFilter.filter(tdp.getMessages());
    for (Message message : messages) {
      System.out.println(message.getImportance());
    }

  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Iterable<Message> messages = filterWithParent(messagesToFilter);
    Set<Message> filteredMessages = new TreeSet<Message>();
    for (Message m : messages) {
      if (m.getImportance().equals(importance)) {
        filteredMessages.add(m);
      }
    }
    return filteredMessages;
  }
}
