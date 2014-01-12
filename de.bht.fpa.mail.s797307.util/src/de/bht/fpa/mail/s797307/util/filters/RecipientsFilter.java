package de.bht.fpa.mail.s797307.util.filters;

import java.util.Set;
import java.util.TreeSet;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

public class RecipientsFilter extends Filter {
  String searchString;
  FilterOperator operator;

  public RecipientsFilter(IFilter filter, String searchString, FilterOperator operator) {
    super(filter);
    this.searchString = searchString;
    this.operator = operator;
  }

  public RecipientsFilter(String searchString) {
    super(new NullFilter());
    this.searchString = searchString;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Iterable<Message> messages = filterWithParent(messagesToFilter);
    Set<Message> filteredMessages = new TreeSet<Message>();
    for (Message m : messages) {
      StringBuilder recipients = new StringBuilder();
      for (Recipient recipient : m.getRecipients()) {
        recipients.append(recipient.getEmail());
        recipients.append(recipient.getPersonal());
      }
      if (StringCompareHelper.matches(recipients.toString(), searchString, operator)) {
        filteredMessages.add(m);
      }
    }
    return filteredMessages;
  }
}