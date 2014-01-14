package de.bht.fpa.mail.s797307.util;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ReadFilter extends BasicFilter {
  private final boolean isRead;

  public ReadFilter(Boolean isRead) {
    this.isRead = isRead;
  }

  @Override
  public boolean filter(Message message) {
    return message.isRead() == isRead;
  }
}
