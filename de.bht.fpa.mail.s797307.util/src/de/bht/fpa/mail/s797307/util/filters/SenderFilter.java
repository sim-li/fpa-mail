package de.bht.fpa.mail.s797307.util.filters;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class SenderFilter extends BasicFilter {
  private final String searchString;
  private final FilterOperator operator;

  public SenderFilter(String searchString, FilterOperator operator) {
    this.searchString = searchString;
    this.operator = operator;
  }

  @Override
  public boolean filter(Message message) {
    return StringCompareHelper.matches(message.getSender().toString(), searchString, operator);
  }
}