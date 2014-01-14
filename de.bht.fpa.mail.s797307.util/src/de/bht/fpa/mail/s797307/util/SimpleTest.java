package de.bht.fpa.mail.s797307.util;

import java.io.File;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.FileSystemTestDataProvider;

public class SimpleTest {
  public static void main(String[] args) {
    final String path = "/Users/funkjaymatada/Beuth/FPA/fpa-eclipse-macosx-64/BHT-FPA/mailer-common/de.bht.fpa.mail.common.tests/maildata";
    FileSystemTestDataProvider testData = new FileSystemTestDataProvider(new File(path));
    for (Message message : testData.getMessages()) {
      System.out.println(message.getSubject());
    }
  }
}
