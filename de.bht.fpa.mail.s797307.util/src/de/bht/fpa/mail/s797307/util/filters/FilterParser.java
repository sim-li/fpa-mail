package de.bht.fpa.mail.s797307.util.filters;

import java.util.Collection;
import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.filter.IFilter;

public final class FilterParser {
  // Read(false)
  // Union(Sender("me@this.com",contains),Recipient("foo@bar.de",is))
  // Intersection(Sender("mueller", startsWith), Read(true))

  // public static void parse (String s) {
  // String inner;
  // }
  //
  public static void main(String[] args) {
    String in = "Union(Sender(\"me@this.com\",contains),Recipient(\"foo@bar.de\",is))";
    // String[] sResult = in.split("\"");
    String[] sResult = in.split("[()]");
    LinkedList<String> filterTypes = new LinkedList();
    LinkedList<String> filterArguments = new LinkedList();

    for (String s : sResult) {
      s = s.replaceAll("^,", "");
      if (s.matches(".+,.+")) {
        filterArguments.add(s);
      } else {
        filterTypes.add(s);
      }
    }

    for (String s : filterTypes) {
      System.out.println(s);
    }
    for (String s : filterArguments) {
      System.out.println(s);
    }
    Collection<IFilter> filters = new LinkedList();
    int typeIndex = filterTypes.size();
    int argIndex = filterArguments.size();
    while (filterArguments.size() > 0) {
      typeIndex--;
      String argument = filterArguments.pop();
      String stringArgument = argument.replaceAll("(.*),(.*)", "$1");
      String filterParameter = argument.replaceAll("(.*),(.*)", "$2");

    }
  }
}
