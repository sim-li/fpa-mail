package de.bht.fpa.mail.s797307.util.parser;

public class test {
  public static void main(String[] args) {
    SNode node = new SNode("Union(Sender(me@this.com,contains),Recipients(foo@bar.de,is))");

    for (String s : node.getChildNodes().getFirst().getParameters()) {
      System.out.println(s);
    }
    // System.out.println(node.getChildNodes());
  }
}
