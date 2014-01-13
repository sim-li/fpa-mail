package de.bht.fpa.mail.s797307.util;

import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;

public final class FilterParser {

  public static boolean isOperator(String s) {
    // for (FilterOperator operator : FilterOperator.values()) {
    // if (operator.value().toString().equals(s)) {
    // return true;
    // }
    // }
    // return false;
    return false;
  }

  public static boolean isFilterName(String s) {
    for (FilterType type : FilterType.values()) {
      if (type.value().equals(s)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    String in = "Subject( " + "Union(Importance(Subject(\"Huhu\"), \"high\"), Read(true))," + " \"Mama\" )";
    // String in =
    // "Union(Sender(\"me@this.com\",contains),Recipient(\"foo@bar.de\",is))";
    LinkedList<ParsingList> pl = buildList(in);
    for (ParsingList pel : pl) {
      System.out.println("--");
      for (String s : pel.getList()) {
        System.out.println(s);
      }
    }
  }

  public static IFilter parse(LinkedList<ParsingList> results, IFilter filter) {
    ParsingList result = results.pop();
    String filterName = result.getFirst();
    if (!isFilterName(filterName)) {
      return new NullFilter();
    }
    while (result.size() > 1) {
      String expression = result.pop();
      if (isFilterName(expression)) {
        return new NullFilter();
      }
      if (!isOperator(expression)) {

      }
    }
    return filter;
  }

  public static LinkedList<ParsingList> buildList(String input) {
    String[] expressions = input.split("[(),]");
    // Build List.
    LinkedList<ParsingList> results = new LinkedList<ParsingList>();
    ParsingList parsingList = new ParsingList();
    for (int i = expressions.length - 1; i >= 0; i--) {
      String rawExpression = expressions[i];
      String expression = rawExpression.trim();
      if (expression.isEmpty()) {
        continue;
      }
      parsingList.push(expression);
      if (!isFilterName(expression)) {
        continue;
      }
      results.push(parsingList);
      parsingList = new ParsingList();
    }
    return results;
  }
}
