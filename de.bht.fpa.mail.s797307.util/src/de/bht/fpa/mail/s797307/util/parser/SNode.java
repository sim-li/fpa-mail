package de.bht.fpa.mail.s797307.util.parser;

import java.util.EnumMap;
import java.util.LinkedList;

public class SNode {
  private final String input;
  protected String value;
  protected SNode parentNode;
  private String[] innerElements;
  private final LinkedList<String> parameters;
  private final LinkedList<SNode> childNodes;
  private SFilterType filterType;
  private SFilterName filterName;
  private EnumMap<SFilterName, SFilterType> filterTypes;

  public SNode(String input) {
    this.filterType = SFilterType.NULL;
    parameters = new LinkedList<String>();
    childNodes = new LinkedList<SNode>();
    this.input = input;
    parse();
  }

  public SNode(SNode parentNode) {
    this.parentNode = parentNode;
    this.filterType = SFilterType.NULL;
    parameters = new LinkedList<String>();
    childNodes = new LinkedList<SNode>();
    input = parentNode.getValue();
    parse();
  }

  private void parse() {
    parseBraketContent();
    parseInnerElements();
    filterTypes = SEnumMapBuilder.buildFilterTypes();
    parseThisFilterTypeAndName();
    reproduce();
  }

  public void parseBraketContent() {
    final char BRAKET_OPEN = '(';
    final char BRAKET_CLOSE = ')';
    char[] seq = input.toCharArray();
    for (int iBegin = 0; iBegin < seq.length; iBegin++) {
      if (seq[iBegin] == BRAKET_OPEN) {
        for (int iEnd = seq.length - 1; iEnd > 0; iEnd--) {
          if (seq[iEnd] == BRAKET_CLOSE) {
            value = input.substring(iBegin + 1, iEnd);
            return;
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
    boolean modified = false;
    int commaPos = -1;
    LinkedList<String> parameters = new LinkedList<String>();
    for (int i = 0; i < seq.length; i++) {
      if (seq[i] == BRAKET_OPEN) {
        modified = true;
        braketsOpen++;
      }
      if (seq[i] == BRAKET_CLOSE) {
        braketsOpen--;
      }
      if (braketsOpen == 0 && modified == true && seq[i] == COMMA) {
        commaPos = i;
        break;
      }
    }
    if (commaPos == -1) {
      parameters.add(input.trim());
    } else {
      parameters.add(input.substring(0, commaPos - 1).trim());
      parameters.add(input.substring(commaPos + 1, seq.length).trim());
    }
    innerElements = parameters.toArray(new String[parameters.size()]);
  }

  public void reproduce() {
    for (String el : innerElements) {
      String[] prefix = el.split("\\(");

      if (getFilterName(prefix[0]).equals(SFilterName.NULL)) {
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
      if (value.toString().equals(input.toUpperCase())) {
        return value;
      }
    }
    return SFilterName.NULL;
  }

  public SFilterType getFilterType(String input) {
    for (SFilterType value : SFilterType.values()) {
      if (value.toString().equals(input.toUpperCase())) {
        return value;
      }
    }
    return SFilterType.NULL;
  }

  private void parseThisFilterTypeAndName() {
    String[] split = value.split("\\(");
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
    return parameters.toArray(new String[parameters.size()]);
  }

  public boolean hasChildNodes() {
    return childNodes.size() > 0;
  }

  public LinkedList<SNode> getChildNodes() {
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
