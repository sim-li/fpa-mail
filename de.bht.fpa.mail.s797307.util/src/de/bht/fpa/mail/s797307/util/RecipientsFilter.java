package de.bht.fpa.mail.s797307.util;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

public class RecipientsFilter extends BasicFilter {
  private final String searchString;
  private final FilterOperator operator;

  public RecipientsFilter(String searchString, FilterOperator operator) {
    this.searchString = searchString;
    this.operator = operator;
  }

  @Override
  public boolean filter(Message message) {
    StringBuilder recipients = new StringBuilder();
    for (Recipient recipient : message.getRecipients()) {
      recipients.append(recipient.getEmail());
      recipients.append(recipient.getPersonal());
    }
    return StringCompareHelper.matches(recipients.toString(), searchString, operator);
  }
}