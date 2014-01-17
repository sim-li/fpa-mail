package de.bht.fpa.mail.s797307.util.parser;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;
import de.bht.fpa.mail.s797307.util.filters.ImportanceFilter;
import de.bht.fpa.mail.s797307.util.filters.IntersectionFilter;
import de.bht.fpa.mail.s797307.util.filters.NullFilter;
import de.bht.fpa.mail.s797307.util.filters.ReadFilter;
import de.bht.fpa.mail.s797307.util.filters.RecipientsFilter;
import de.bht.fpa.mail.s797307.util.filters.SenderFilter;
import de.bht.fpa.mail.s797307.util.filters.SubjectFilter;
import de.bht.fpa.mail.s797307.util.filters.TextFilter;
import de.bht.fpa.mail.s797307.util.filters.UnionFilter;

public final class SFilterGenerator {

  public static BasicFilter buildFilter(SFilterName name, Object value, SOperator operator) {
    FilterOperator operatorConvert = FilterOperator.valueOf(operator.toString());
    switch (name) {
    case IMPORTANCE:
      return new ImportanceFilter((Importance) value);
    case READ:
      return new ReadFilter((Boolean) value);
    case UNION:
      return new UnionFilter();
    case INTERSECTION:
      return new IntersectionFilter();
    }
    if (operator != SOperator.NULL) {
      switch (name) {
      case SENDER:
        return new SenderFilter((String) value, operatorConvert);
      case RECIPIENTS:
        return new RecipientsFilter((String) value, operatorConvert);
      case SUBJECT:
        return new SubjectFilter((String) value, operatorConvert);
      case TEXT:
        new TextFilter((String) value, operatorConvert);
      }
    }
    return new NullFilter();
  }

  public static boolean isOperator(String s) {
    for (FilterOperator operator : FilterOperator.values()) {
      if (operator.name().toString().equals(s.toUpperCase())) {
        return true;
      }
    }
    return false;
  }
}
