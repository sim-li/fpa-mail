package de.bht.fpa.mail.s797307.util.parser;

import java.util.LinkedList;

public class ParsingList {
  private final LinkedList<String> c;

  public ParsingList() {
    c = new LinkedList<String>();
  }

  public void push(String s) {
    c.push(s);
  }

  public void clear() {
    c.clear();
  }

  public String getFirst() {
    return c.getFirst();
  }

  public LinkedList<String> getList() {
    return c;
  }

  public int size() {
    return c.size();
  }

  public String pop() {
    return c.pop();
  }
}
