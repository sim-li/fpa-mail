package de.bht.fpa.mail.s797307.util.parser;

import java.util.EnumMap;
import java.util.LinkedList;

public class SNode {
  protected final String input;
  protected String value;
  protected SNode parentNode;
  private String[] innerElements;
  private SFilterType filterType;
  private SFilterName filterName;
  private EnumMap<SFilterName, SFilterType> filterTypes;

  public SNode(String input) {
    this.filterType = SFilterType.NULL;
    this.input = input;
    parse();
  }

  public SNode(SNode parentNode) {
    this.parentNode = parentNode;
    this.filterType = SFilterType.NULL;
    input = parseBraketContent(parentNode.input);
    parse();
  }

  private void parse() {
    parseInnerElements();
    filterTypes = SEnumMapBuilder.buildFilterTypes();
    parseThisFilterTypeAndName();
  }

  public String parseBraketContent(String s) {
    String result;
    final char BRAKET_OPEN = '(';
    final char BRAKET_CLOSE = ')';
    char[] seq = s.toCharArray();
    for (int iBegin = 0; iBegin < seq.length; iBegin++) {
      if (seq[iBegin] == BRAKET_OPEN) {
        for (int iEnd = seq.length - 1; iEnd > 0; iEnd--) {
          if (seq[iEnd] == BRAKET_CLOSE) {
            result = s.substring(iBegin + 1, iEnd);
            return result;
          }
        }
      }
    }
    result = "";
    return result;
  }

  // Expects braketContent
  public void parseInnerElements() {
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
      if (braketsOpen == 1 && modified == true && seq[i] == COMMA) {
        commaPos = i;
        break;
      }
    }
    if (commaPos != -1) {
      parameters.add(input.substring(0, commaPos).trim());
      parameters.add(input.substring(commaPos + 1, seq.length).trim());
    }
    innerElements = parameters.toArray(new String[parameters.size()]);
  }

  public String getValue() {
    return value;
  }

  public SFilterName getFilterName() {
    return filterName;
  }

  public SFilterType getFilterType() {
    return filterType;
  }

  public SFilterName generateFilterName(String input) {
    for (SFilterName value : SFilterName.values()) {
      if (value.toString().equals(input.toUpperCase())) {
        return value;
      }
    }
    return SFilterName.NULL;
  }

  private void parseThisFilterTypeAndName() {
    String[] split = input.split("\\(");
    if (split.length <= 1) {
      filterName = SFilterName.NULL;
      filterType = SFilterType.NULL;
    }
    String trimmed = split[0].trim();
    filterName = generateFilterName(trimmed);
    SFilterType type = filterTypes.get(filterName);
    if (type == null) {
      filterType = SFilterType.NULL;
    } else {
      filterType = type;
    }

  }

  public boolean hasParameters() {
    // return parameters.size() > 0;
    return false;
  }

  public String[] getParameters() {
    LinkedList<String> parameters = new LinkedList<String>();
    for (String el : innerElements) {
      String[] prefix = el.split("\\(");
      if (generateFilterName(prefix[0]).equals(SFilterName.NULL)) {
        // parameters.add(el);
      }
    }
    return null;
  }

  public boolean hasChildNodes() {
    return false;
    // return childNodes.size() > 0;
  }

  public LinkedList<SNode> getChildNodes() {
    LinkedList<SNode> childNodes = new LinkedList<SNode>();
    for (String el : innerElements) {
      String[] prefix = el.split("\\(");
      if (!generateFilterName(prefix[0]).equals(SFilterName.NULL)) {
        childNodes.add(new SNode(this));
      }
    }
    return childNodes;
  }

  public String getInput() {
    return input;
  }

  public SNode getParentNode() {
    return parentNode;
  }

  public SNode getFirstChild() {
    return parentNode;
    // return childNodes.getFirst();
  }

  public SNode getLastChild() {
    return parentNode;
    // return childNodes.getLast();
  }
}
