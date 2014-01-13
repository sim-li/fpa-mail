package de.bht.fpa.mail.s797307.maillist;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;

public class FilterTransfer {
  protected FilterGroupType filterGroupType;
  protected FilterCombination filterCombination;

  public FilterTransfer(FilterGroupType filterGroupType, FilterCombination filterCombination) {
    this.filterGroupType = filterGroupType;
    this.filterCombination = filterCombination;
  }

  public FilterGroupType getFilterGroupType() {
    return filterGroupType;
  }

  public FilterCombination getFilterCombination() {
    return filterCombination;
  }
}
