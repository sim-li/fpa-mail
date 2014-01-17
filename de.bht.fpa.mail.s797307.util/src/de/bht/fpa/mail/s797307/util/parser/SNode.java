package de.bht.fpa.mail.s797307.util.parser;

import java.util.HashSet;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.bht.fpa.mail.s797307.util.filters.BasicFilter;

public class SNode {
  protected String phrase;
  protected SNode parent;
  protected HashSet<SNode> children;

  public SNode(String phrase) {
    this.phrase = phrase;
  }

  public SNode(SNode parent) {
    this.parent = parent;
    this.phrase = parent.getPhrase();
  }

  public SNode() {
    this("");
  }

  public boolean parse() {
    return false;
  }

  public void setNodeValue(String phrase) {
    this.phrase = phrase;
  }

  public SNodeTypes getNodeType() {
    // TODO Check for Type an return value
    return SNodeTypes.HELLO;
  }

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

  public String getPhrase() {
    return phrase;
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
