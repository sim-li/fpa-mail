package de.bht.fpa.mail.s797307.util.parser;

import java.util.EnumMap;
import java.util.HashSet;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s797307.util.filters.BasicFilter;

public class SNode {
  protected String phrase;
  protected SNode parent;
  protected HashSet<SNode> children;
  protected EnumMap<SFilterName, SFilterType> filterTypes;

  public SNode(String phrase) {
    this.phrase = phrase;

  }

  public SNode(SNode parent) {
    this.parent = parent;
    this.phrase = parent.getPhrase();
    this.filterTypes = SEnumMapBuilder.buildFilterTypes();
  }

  public void setNodeValue(String phrase) {
    this.phrase = phrase;
  }

  public SFilterType getFilterType() {
    String[] phraseSplit = phrase.split("(");
    if (phraseSplit.length > 1) {
      String filterName = phraseSplit[0];
      return filterTypes.get(FilterType.valueOf(filterName.toUpperCase()));
    }
    return SFilterType.NULL;
  }

  // UNION("one"), INTERSECTION("all");
  // SENDER("Sender"), RECIPIENTS("Recipients"), SUBJECT("Subject"),
  // TEXT("Contents of EMail"), READ("Read"), IMPORTANCE("Importance");

  public Node getParentNode() {
    // TODO Auto-generated method stub
    return null;
  }

  public NodeList getChildNodes() {
    // TODO Auto-generated method stub
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

  public boolean hasChildNodes() {
    // TODO Auto-generated method stub
    return false;
  }

  public BasicFilter buildFilter() {
    return null;
    // TODO Auto-generated method stub
  }

  public String getPhrase() {
    return phrase;
  }

}
