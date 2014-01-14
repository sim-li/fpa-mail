package de.bht.fpa.mail.s797307.util;

import java.util.List;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;

public final class FilterGenerator {

  private static BasicFilter returnFilter(FilterType type, Object value) {
    return returnFilter(type, value, null);
  }

  private static BasicFilter returnFilter(FilterType type, Object value, FilterOperator operator) {
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

  public static BasicFilter buildFilter(CombinationFilter filter, List<FilterCombination> combinations) {
    for (FilterCombination combination : combinations) {
      FilterType type = combination.getFilterType();
      Object value = combination.getFilterValue();
      FilterOperator operator = combination.getFilterOperator();
      filter.addFilter(returnFilter(type, value, operator));
    }
    return filter;
  }
}
