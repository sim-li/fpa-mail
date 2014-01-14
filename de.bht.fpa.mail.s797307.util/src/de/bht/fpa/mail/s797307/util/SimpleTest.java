package de.bht.fpa.mail.s797307.util;

import java.io.File;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.FileSystemTestDataProvider;

public class SimpleTest {
  public static void printIfTrue(Message message, boolean value) {
    if (value) {
      System.out.println(message.getSubject() + " | " + message.isRead() + " | " + message.getImportance());
    }
  }

  public static void main(String[] args) {
    final String path = "/Users/funkjaymatada/Beuth/FPA/fpa-eclipse-macosx-64/BHT-FPA/mailer-common/de.bht.fpa.mail.common/maildata";
    FileSystemTestDataProvider testData = new FileSystemTestDataProvider(new File(path));
    UnionFilter union = new UnionFilter();
    IntersectionFilter intersection = new IntersectionFilter();
    ReadFilter read = new ReadFilter(true);
    ReadFilter unread = new ReadFilter(false);
    ImportanceFilter normalImportance = new ImportanceFilter(Importance.NORMAL);
    ImportanceFilter lowImportance = new ImportanceFilter(Importance.LOW);

    System.out.println("Basic Data");
    System.out.println("__________________________________");
    for (Message message : testData.getMessages()) {
      System.out.println(message.getSubject() + " | " + message.isRead() + " | " + message.getImportance());
    }

    System.out.println();
    System.out.println("Read-is-False");
    System.out.println("__________________________________");
    for (Message message : testData.getMessages()) {
      printIfTrue(message, unread.filter(message));
    }

    System.out.println();
    System.out.println("Read-is-False");
    System.out.println("__________________________________");
    for (Message message : testData.getMessages()) {
      printIfTrue(message, unread.filter(message));
    }

    // Union(Sender("me@this.com",contains),Recipient("foo@bar.de",is))l
    System.out.println();
    System.out.println("Union(Sender(me@this.com,contains),Recipient(foo@bar.de,is))");
    System.out.println("__________________________________");
    for (Message message : testData.getMessages()) {
      union.addFilters(new SenderFilter("me@this.com", FilterOperator.CONTAINS), new RecipientsFilter("foo@bar.de",
          FilterOperator.IS));
      printIfTrue(message, unread.filter(message));
    }

    // Intersection(Sender("mueller", startsWith), Read(true))
    System.out.println();
    System.out.println("Intersection(Sender(mueller, startsWith), Read(true))");
    System.out.println("__________________________________");
    for (Message message : testData.getMessages()) {
      intersection.addFilters(new SenderFilter("mueller", FilterOperator.STARTS_WITH), new ReadFilter(true));
      printIfTrue(message, unread.filter(message));
    }

    System.out.println();
    System.out.println("Union with unread and normalImportance");
    System.out.println("__________________________________");
    for (Message message : testData.getMessages()) {
      union.addFilters(unread, normalImportance);
      printIfTrue(message, union.filter(message));
    }
  }
}
