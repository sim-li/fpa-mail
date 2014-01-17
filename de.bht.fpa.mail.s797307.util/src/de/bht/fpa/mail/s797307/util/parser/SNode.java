package de.bht.fpa.mail.s797307.util.parser;

import java.util.EnumMap;
import java.util.LinkedList;

import org.w3c.dom.Node;

import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;

public class SNode {
  protected String value;
  protected SNode parentNode;
  private String[] parameters;
  private SNodeList childNodes;
  private EnumMap<SFilterName, SFilterType> filterTypes;

  public SNode(String value) {
    this.value = parseBraketContent(value);
    parse();
  }

  public SNode(SNode parentNode) {
    this.parentNode = parentNode;
    parse();
  }

  public void parse() {
    value = parseBraketContent(parentNode.getValue());
    parameters = parseParameters(value);
    filterTypes = SEnumMapBuilder.buildFilterTypes();
  }

  public String parseBraketContent(String input) {
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
            return input.substring(iBegin, iEnd);
          }
        }
      }
    }
    return "";
  }

  // Expects braketContent
  public String[] parseParameters(String input) {
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
    return (String[]) parameters.toArray();
  }

  public String getValue() {
    return value;
  }

  public SFilterType getFilterType() {
    String[] valueSplit = value.split("(");
    if (valueSplit.length > 1) {
      String filterName = valueSplit[0].trim();
      return filterTypes.get(FilterType.valueOf(filterName.toUpperCase()));
    }
    return SFilterType.NULL;
  }

  // UNION("one"), INTERSECTION("all");
  // SENDER("Sender"), RECIPIENTS("Recipients"), SUBJECT("Subject"),
  // TEXT("Contents of EMail"), READ("Read"), IMPORTANCE("Importance");

  public SNode getParentNode() {
    return parentNode;
  }

  public boolean hasChildNodes() {
    // TODO Auto-generated method stub
    return false;
  }

  public SNodeList getChildNodes() {
    return childNodes;
  }

  public boolean hasParameters() {
    return false;
  }

  public String[] parseParameters() {
    return null;
  }

  public Node getFirstChild() {
    // TODO Auto-generated method stub
    return null;
  }

  public Node getLastChild() {
    // TODO Auto-generated method stub
    return null;
  }

  public Node getPreviousSibling() {
    // TODO Auto-generated method stub
    return null;
  }

  public Node getNextSibling() {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean hasNextSibling() {
    return false;
  }

  public boolean hasPreviousSibling() {
    return false;
  }

  public BasicFilter buildFilter() {
    return null;
    // TODO Auto-generated method stub
  }

}
