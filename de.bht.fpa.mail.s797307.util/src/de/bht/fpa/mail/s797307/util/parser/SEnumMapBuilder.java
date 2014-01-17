package de.bht.fpa.mail.s797307.util.parser;

import java.util.EnumMap;

public final class SEnumMapBuilder {
  public static EnumMap<SFilterName, SFilterType> buildFilterTypes() {
    EnumMap<SFilterName, SFilterType> filterType = new EnumMap(SFilterName.class);
    filterType.put(SFilterName.IMPORTANCE, SFilterType.ATOMIC);
    filterType.put(SFilterName.INTERSECTION, SFilterType.GROUP);
    filterType.put(SFilterName.READ, SFilterType.ATOMIC);
    filterType.put(SFilterName.RECIPIENTS, SFilterType.COMPARISON);
    filterType.put(SFilterName.SENDER, SFilterType.COMPARISON);
    filterType.put(SFilterName.SUBJECT, SFilterType.COMPARISON);
    filterType.put(SFilterName.TEXT, SFilterType.COMPARISON);
    filterType.put(SFilterName.UNION, SFilterType.GROUP);
    return filterType;
  }
}
