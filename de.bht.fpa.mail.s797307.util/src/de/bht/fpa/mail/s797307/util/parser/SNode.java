package de.bht.fpa.mail.s797307.util.parser;

import java.util.EnumMap;
import org.w3c.dom.Node;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;

public class SNode {
  protected String value;
  protected SNode parentNode;
  private SNodeList childNodes;
  private EnumMap<SFilterName, SFilterType> filterTypes;

  public SNode(String value) {
    this.value = parseBraketContent(value);
  }

  public SNode(SNode parentNode) {
    this.parentNode = parentNode;
    this.value = parseBraketContent(parentNode.getValue());
    this.filterTypes = SEnumMapBuilder.buildFilterTypes();
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

  public String parseParameters(String input) {

  }

  public String getValue() {
    return value;
  }

  public SFilterType getFilterType() {
    String[] valueSplit = value.split("(");
    if (valueSplit.length > 1) {
      String filterName = valueSplit[0];
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

  public SNodeList getChildNodes() {
    return childNodes;
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

  public boolean hasChildNodes() {
    // TODO Auto-generated method stub
    return false;
  }

  public BasicFilter buildFilter() {
    return null;
    // TODO Auto-generated method stub
  }

}
