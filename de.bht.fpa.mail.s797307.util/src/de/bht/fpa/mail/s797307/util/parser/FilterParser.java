package de.bht.fpa.mail.s797307.util.parser;

import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;
import de.bht.fpa.mail.s797307.util.filters.NullFilter;

public final class FilterParser {

  public static boolean isOperator(String s) {
    for (FilterOperator operator : FilterOperator.values()) {
      if (operator.name().toString().equals(s.toUpperCase())) {
        return true;
      }
    }
    return false;
  }

  public static boolean isFilterType(String s) {
    for (FilterType type : FilterType.values()) {
      if (type.name().equals(s.toUpperCase())) {
        return true;
      }
    }
    return false;
  }

  public static boolean isGroupFilter(String test) {
    for (FilterGroupType c : FilterGroupType.values()) {
      if (c.name().equals(test.toUpperCase())) {
        return true;
      }
    }
    return false;
  }

  public static boolean isSomeFilter(String test) {
    return isGroupFilter(test) || isFilterType(test);
  }

  public static boolean isFilter(String test) {
    for (FilterType c : FilterType.values()) {
      if (c.name().equals(test.toUpperCase())) {
        return true;
      }
    }
    return false;
  }

  public static BasicFilter buildCorrectFilter(String type, String operator, String value) {
    if (isGroupFilter(type)) {
      return FilterGenerator.buildFilter(FilterGroupType.valueOf(type));
    }
    if (isFilterType(type)) {
      return FilterGenerator.buildFilter(FilterType.valueOf(type), value);
    }
    if (isFilterType(type) && isOperator(operator)) {
      return FilterGenerator.buildFilter(FilterType.valueOf(type), value, FilterOperator.valueOf(operator));
    }
    return null;
  }

  public static void main(String[] args) {
    // String in = "Intersection(Sender(\"mueller\", startsWith), Read(true))";
    // parse(in, new UnionFilter());
    System.out.println(Character.getNumericValue(' '));
  }

  public static IFilter parse(String input, BasicFilter filter) {
    final char BRAKET_OPEN = '(';
    final char BRAKET_CLOSE = ')';
    char[] seq = input.toCharArray();
    for (int i = 0; i < seq.length; i++) {
      int seqEnd = seq.length - i - 1;
      char braketOpen = seq[i];
      char braketClose = seq[seqEnd];
      int elementStart = -1;
      int elementEnd = -1;
      if (braketOpen == BRAKET_OPEN) {
        elementStart = i;
        for (int i2 = seqEnd; i > 0; i--) {
        }
      }
      System.out.println(elementStart + " " + elementEnd);
      if (elementStart != -1 && elementEnd != -1) {
        String inner = input.substring(elementStart, elementEnd);
        String[] parameters = inner.split(",");
        String type = input.substring(0, elementStart);
        String value = parameters[0].trim();

        String operator = parameters[1].trim();
        BasicFilter innerFilter;
        // Not atomic
        System.out.println("Not entering, " + input.substring(elementStart, elementEnd));
        if (value.matches("*(*")) {
          innerFilter = buildCorrectFilter(type, operator, value);
          System.out.println("Entering the crowd, " + input.substring(elementStart, elementEnd));
          return parse(input.substring(elementStart, elementEnd), innerFilter);
        }
      }

    }
    return new NullFilter();
  }
}
