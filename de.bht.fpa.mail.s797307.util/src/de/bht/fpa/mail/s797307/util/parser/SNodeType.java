package de.bht.fpa.mail.s797307.util.parser;

public class SNodeType {
  protected boolean isAtomicFilter;
  protected boolean isComparisonFilter;
  protected boolean hasChild;
  protected boolean isGroupFilter;
  protected boolean notDefined;

  public boolean isNotDefined() {
    return notDefined;
  }

  public void setNotDefined(boolean notDefined) {
    this.notDefined = notDefined;
  }

  public SNodeType() {
    ;
  }

  public boolean isAtomicFilter() {
    return isAtomicFilter;
  }

  public void setAtomicFilter(boolean isAtomicFilter) {
    this.isAtomicFilter = isAtomicFilter;
  }

  public boolean isComparisonFilter() {
    return isComparisonFilter;
  }

  public void setComparisonFilter(boolean isComparisonFilter) {
    this.isComparisonFilter = isComparisonFilter;
  }

  public boolean hasChild() {
    return hasChild;
  }

  public void setChild(boolean hasChild) {
    this.hasChild = hasChild;
  }

  public boolean isGroupFilter() {
    return isGroupFilter;
  }

  public void setGroupFilter(boolean isGroupFilter) {
    this.isGroupFilter = isGroupFilter;
  }

}
