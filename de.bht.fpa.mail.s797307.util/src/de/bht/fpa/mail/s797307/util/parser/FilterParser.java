package de.bht.fpa.mail.s797307.util.parser;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;
import de.bht.fpa.mail.s797307.util.filters.NullFilter;

public final class FilterParser {

  public static void main(String[] args) {
    // String in = "Intersection(Sender(\"mueller\", startsWith), Read(true))";
    // parse(in, new UnionFilter());
  }

  public static IFilter parse(String input, BasicFilter filter) {
    return new NullFilter();
  }
}
