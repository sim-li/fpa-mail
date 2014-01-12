package de.bht.fpa.mail.s797307.util.filters;

import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s797307.util.FilterOperator;
import de.bht.fpa.mail.s797307.util.FilterType;

public final class FilterParser {

  public static boolean isOperator(String s) {
    for (FilterOperator operator : FilterOperator.values()) {
      if (operator.value().toString().equals(s)) {
        return true;
      }
    }
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
    String in = "Subject( Union( Importance(Subject(\"Huhu\")), Read(true) ), \"Mama\" )";
    LinkedList<ParsingList> pl = buildList(in);
    for (ParsingList pel : pl) {
      System.out.println("--");
      for (String s : pel.getList()) {
        System.out.println(s);
      }
    }
  }

  public static IFilter parse(LinkedList<ParsingList> results, IFilter filter) {

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
