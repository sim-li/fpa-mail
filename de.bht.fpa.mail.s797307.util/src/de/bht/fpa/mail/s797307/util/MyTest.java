package de.bht.fpa.mail.s797307.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class MyTest {

  @Test
  public void testReadIsFalse() {
    Message message = new Message();
    message.setRead(false);
    ReadFilter readFilter = new ReadFilter(false);
    assertTrue("ReadFilter filters for false", readFilter.filter(message));
  }

  @Test
  public void testReadIsTrue() {
    Message message = new Message();
    message.setRead(true);
    ReadFilter readFilter = new ReadFilter(true);
    assertTrue("ReadFilter filters for true", readFilter.filter(message));
  }
}