package de.bht.fpa.mail.s797307.util.parser;

import java.util.List;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;
import de.bht.fpa.mail.s797307.util.filters.CombinationFilter;
import de.bht.fpa.mail.s797307.util.filters.ImportanceFilter;
import de.bht.fpa.mail.s797307.util.filters.IntersectionFilter;
import de.bht.fpa.mail.s797307.util.filters.NullFilter;
import de.bht.fpa.mail.s797307.util.filters.ReadFilter;
import de.bht.fpa.mail.s797307.util.filters.RecipientsFilter;
import de.bht.fpa.mail.s797307.util.filters.SenderFilter;
import de.bht.fpa.mail.s797307.util.filters.SubjectFilter;
import de.bht.fpa.mail.s797307.util.filters.TextFilter;
import de.bht.fpa.mail.s797307.util.filters.UnionFilter;

public final class FilterGenerator {

  public static BasicFilter buildFilter(FilterType type, Object value) {
    return buildFilter(type, value, null);
  }

  public static BasicFilter buildFilter(FilterGroupType type) {
    switch (type) {
    case UNION:
      return new UnionFilter();
    case INTERSECTION:
      return new IntersectionFilter();
    }
    return new NullFilter();
  }

  // / FOR OLD PROGRAM
  public static BasicFilter buildFilter(FilterType type, Object value, FilterOperator operator) {
    switch (type) {
    case IMPORTANCE:
      return new ImportanceFilter((Importance) value);
    case READ:
      return new ReadFilter((Boolean) value);
    }

    if (operator != null) {
      switch (type) {
      case SENDER:
        return new SenderFilter((String) value, operator);
      case RECIPIENTS:
        return new RecipientsFilter((String) value, operator);
      case SUBJECT:
        return new SubjectFilter((String) value, operator);
      case TEXT:
        new TextFilter((String) value, operator);
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

  public static BasicFilter buildFilter(CombinationFilter filter, List<FilterCombination> combinations) {
    for (FilterCombination combination : combinations) {
      FilterType type = combination.getFilterType();
      Object value = combination.getFilterValue();
      FilterOperator operator = combination.getFilterOperator();
      filter.addFilter(buildFilter(type, value, operator));
    }
    return filter;
  }
}
