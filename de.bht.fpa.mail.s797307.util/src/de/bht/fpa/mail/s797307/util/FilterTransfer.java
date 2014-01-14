package de.bht.fpa.mail.s797307.util;

import java.util.List;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;

public class FilterTransfer {
  protected FilterGroupType filterGroupType;
  protected List<FilterCombination> filterCombination;

  public FilterTransfer(FilterGroupType filterGroupType, List<FilterCombination> filterCombination) {
    this.filterGroupType = filterGroupType;
    this.filterCombination = filterCombination;
  }

  public FilterGroupType getFilterGroupType() {
    return filterGroupType;
  }

  public List<FilterCombination> getFilterCombination() {
    return filterCombination;
  }
}
