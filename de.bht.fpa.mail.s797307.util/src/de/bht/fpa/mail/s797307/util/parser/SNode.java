package de.bht.fpa.mail.s797307.util.parser;

import java.util.EnumMap;
import java.util.LinkedList;

import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;

public class SNode {
  protected String value;
  protected SNode parentNode;
  private String[] innerElements;
  private final LinkedList<String> parameters;
  private SNodeList childNodes;
  private SFilterType filterType;
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
    parseThisFilterType();
    reproduce();
  }

  public void parseBraketContent() {
    String input = parentNode.getValue();
    final char BRAKET_OPEN = '(';
    final char BRAKET_CLOSE = ')';
    char[] seq = input.toCharArray();
    for (int iBegin = 0; iBegin < seq.length; iBegin++) {
      int elementStart = -1;
      int elementEnd = -1;
      if (seq[iBegin] == BRAKET_OPEN) {
        elementStart = iBegin;
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
      if (getFilterType(el).equals(SFilterType.NULL)) {
        parameters.add(el);
      } else {
        childNodes.add(new SNode(el));
      }
    }
  }

  public String getValue() {
    return value;
  }

  public SFilterType getFilterType(String input) {
    return filterTypes.get(FilterType.valueOf(input.toUpperCase()));
  }

  private void parseThisFilterType() {
    String[] valueSplit = value.split("(");
    if (valueSplit.length <= 1) {
      filterType = SFilterType.NULL;
    }
    String filterName = valueSplit[0].trim();
    filterType = getFilterType(filterName);
  }

  // UNION("one"), INTERSECTION("all");
  // SENDER("Sender"), RECIPIENTS("Recipients"), SUBJECT("Subject"),
  // TEXT("Contents of EMail"), READ("Read"), IMPORTANCE("Importance");

  public boolean hasParameters() {
    return false;
  }

  public String[] getParameters() {
    return null;
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

  public BasicFilter buildFilter() {
    return null;
    // TODO Auto-generated method stub
  }

}
