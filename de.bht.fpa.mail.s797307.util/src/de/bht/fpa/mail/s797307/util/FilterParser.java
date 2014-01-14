package de.bht.fpa.mail.s797307.util;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.filter.IFilter;

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
    String in = "Subject( " + "Union(Importance(Subject(\"Huhu\"), \"high\"), Read(true))," + " \"Mama\" )";
    parse(in, new UnionFilter());
  }

  public static IFilter parse(String input, BasicFilter filter) {
    final char BRAKET_OPEN = '(';
    final char BRAKET_CLOSE = ')';
    char [] seq = input.toCharArray();
    for (int i = 0; i < seq.length; i++) {
      int seqEnd = seq.length - i;
      char braketOpen = seq[i];
      char braketClose = seq[seqEnd];
      int elementStart = -1;
      int elementEnd = -1;
      if (braketOpen == BRAKET_OPEN) {
        elementStart = i;
      }
      if (braketClose == BRAKET_CLOSE) {
        elementEnd = seqEnd;
      }
      
    }
    return filter;
    input.indexOf(ch);
    input.substring(beginIndex)
    // ParsingList result = results.pop();
    // String filterName = result.getFirst();
    // }
    // return filter;
  }
}
