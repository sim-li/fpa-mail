package de.bht.fpa.mail.s797307.util;

import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ImportanceFilter extends BasicFilter {
  private final Importance importance;

  public ImportanceFilter(Importance importance) {
    this.importance = importance;
  }

  @Override
  public boolean filter(Message message) {
    return message.getImportance().equals(importance) ? true : false;
  }
}
