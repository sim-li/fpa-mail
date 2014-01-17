package de.bht.fpa.mail.s797307.util.parser;

import java.util.EnumMap;
import java.util.LinkedList;

public class SNode {
  protected String value;
  protected SNode parentNode;
  private String[] innerElements;
  private final LinkedList<String> parameters;
  private SNodeList childNodes;
  private SFilterType filterType;
  private SFilterName filterName;
  private EnumMap<SFilterName, SFilterType> filterTypes;

  public SNode(String value) {
    this.filterType = SFilterType.NULL;
    parameters = new LinkedList<String>();
    parse();
  }

  public SNode(SNode parentNode) {
    this.parentNode = parentNode;
    this.filterType = SFilterType.NULL;
    parameters = new LinkedList<String>();
    parse();
  }

  public void parse() {
    parseBraketContent();
    parseInnerElements();
    filterTypes = SEnumMapBuilder.buildFilterTypes();
    parseThisFilterTypeAndName();
    reproduce();
  }

  public void parseBraketContent() {
    String input = parentNode.getValue();
    final char BRAKET_OPEN = '(';
    final char BRAKET_CLOSE = ')';
    char[] seq = input.toCharArray();
    for (int iBegin = 0; iBegin < seq.length; iBegin++) {
      if (seq[iBegin] == BRAKET_OPEN) {
        for (int iEnd = seq.length; iEnd > 0; iEnd--) {
          if (seq[iEnd] == BRAKET_CLOSE) {
            value = input.substring(iBegin, iEnd);
          }
        }
      }
    }
    value = "";
  }

  // Expects braketContent
  public void parseInnerElements() {
    String input = value;
    final char BRAKET_OPEN = '(';
    final char BRAKET_CLOSE = ')';
    final char COMMA = ',';
    char[] seq = input.toCharArray();
    int braketsOpen = 0;
    LinkedList<Integer> commaPositions = new LinkedList<Integer>();
    LinkedList<String> parameters = new LinkedList<String>();
    for (int i = 0; i < seq.length; i++) {
      if (seq[i] == BRAKET_OPEN) {
        braketsOpen++;
      }
      if (seq[i] == BRAKET_CLOSE) {
        braketsOpen--;
      }
      if (braketsOpen == 0 && seq[i] == COMMA) {
        commaPositions.push(i);
      }
    }
    int lastIndex = 0;
    for (int commaIndex : commaPositions) {
      parameters.push(input.substring(lastIndex, commaIndex).trim());
    }
    innerElements = (String[]) parameters.toArray();
  }

  public void reproduce() {
    for (String el : innerElements) {
      if (getFilterName(el).equals(SFilterName.NULL)) {
        parameters.add(el);
      } else {
        childNodes.add(new SNode(el));
      }
    }
  }

  public String getValue() {
    return value;
  }

  public SFilterName getFilterName(String input) {
    for (SFilterName value : SFilterName.values()) {
      if (value.toString() == input.toUpperCase()) {
        return value;
      }
    }
    return SFilterName.NULL;
  }

  public SFilterType getFilterType(String input) {
    for (SFilterType value : SFilterType.values()) {
      if (value.toString() == input.toUpperCase()) {
        return value;
      }
    }
    return SFilterType.NULL;
  }

  private void parseThisFilterTypeAndName() {
    String[] split = value.split("(");
    if (split.length <= 1) {
      filterName = SFilterName.NULL;
      filterType = SFilterType.NULL;
    }
    String trimmed = split[0].trim();
    filterName = getFilterName(trimmed);
    SFilterType type = filterTypes.get(filterName);
    if (type == null) {
      filterType = SFilterType.NULL;
    } else {
      filterType = type;
    }

  }

  public boolean hasParameters() {
    return parameters.size() > 0;
  }

  public String[] getParameters() {
    return (String[]) parameters.toArray();
  }

  public boolean hasChildNodes() {
    return childNodes.size() > 0;
  }

  public SNodeList getChildNodes() {
    return childNodes;
  }

  public SNode getParentNode() {
    return parentNode;
  }

  public SNode getFirstChild() {
    return childNodes.getFirst();
  }

  public SNode getLastChild() {
    return childNodes.getLast();
  }
}
