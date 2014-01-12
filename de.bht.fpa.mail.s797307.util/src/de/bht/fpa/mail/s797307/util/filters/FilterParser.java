package de.bht.fpa.mail.s797307.util.filters;

import de.bht.fpa.mail.s000000.common.filter.IFilter;

public final class FilterParser {
  /*
   * Regular Expressions: s.replaceAll("^,", ""); -- Remove leading comma
   * s.matches(".+,.+") -- Match comma-seperated arguments in.split("[()]"); --
   * in.split("[()]") -- Split with opening or closing bracket.
   */

  /*
   * EXAMPLE INPUT STRINGS Read(false)
   * Union(Sender("me@this.com",contains),Recipient("foo@bar.de",is))
   * Intersection(Sender("mueller", startsWith), Read(true))
   */

  public static boolean isOperator(String s) {
    /*
     * is: String ist identisch mit einem anderen String („NERD“ is „NERD“)
     * contains: String enthält einen anderen String („NERD“ contains „ER“).
     * contains not: String enthält nicht einen anderen String („NERD“ contains
     * not „FOO“). starts with: String startet mit einem anderen String („NERD“
     * starts with „NER“). ends with: String endet mit einem anderen String
     * („NERD“ ends with „RD“).
     */
    return false;
  }

  public static boolean isFilterName(String s) {
    /*
     * Sender: Gegeben ein String, filtere nach allen E-Mails, in denen die
     * E-Mail Adresse (Message.sender.email) oder der Name des Absenders
     * (Message.sender.personal) zu dem gegebenen String passt. Recipients:
     * Gegeben ein String, filtere nach allen E-Mails, in denen die E-Mail
     * Adresse (Message.recipients.email) oder Name der Empfänger
     * (Message.recipients.personal) zu dem gegebenen String passt. Subject:
     * Gegeben ein String, filtere nach allen E-Mails, deren Betreff
     * (Message.subject) zu dem gegebenen String passt. Text: Gegeben ein
     * String, filtere nach allen E-Mails, deren Text (Message.text) zu dem
     * gegebenen String passt. Importance: Gegeben ein Literal der Enumeration
     * de.bht.fpa.mail.s000000.common.mail.model.Importance, filtere nach alle
     * E-Mails, die diese Wichtigkeit/Priorität haben (Message.importance).
     * Read: Gegeben ein Boolean, filtere nach allen E-Mails, die gelesen sind
     * (Message.isRead() == true) oder nicht (Message.isRead() == false).
     * Intersection (Schnittmenge): Gegeben eine Liste von Filtern, wähle nur
     * die E-Mails, die in der Ergebnismenge aller Filter enthalten sind. Dieser
     * Filter kombiniert demnach andere Filter. Union (Vereinigung): G
     */
    return false;
  }

  public static boolean isSearchString(String s) {
    // HELLO@WORLD.COM, etc.
    return false;
  }

  public static void main(String[] args) {
    /*
     * public ImportanceFilter(IFilter filter, Importance importance) public
     * IntersectionFilter(IFilter... filters) public ReadFilter(IFilter filter,
     * Boolean isRead) public RecipientsFilter(IFilter filter, String
     * searchString, FilterOperator operator) public SenderFilter(IFilter
     * filter, String searchString, FilterOperator operator) public
     * SubjectFilter(IFilter filter, String searchString, FilterOperator
     * operator) public TextFilter(IFilter filter, String searchString,
     * FilterOperator operator) public UnionFilter(IFilter... filters)
     */
    String in = "Union(Sender(\"me@this.com\",contains),Recipient(\"foo@bar.de\",is))";
    String regex = "[(),]";
    String[] expressions = in.split(regex);
    IFilter filterChain;
    for (int i = expressions.length; i > 1; i--) {
      boolean isAtomic = i == expressions.length;
      boolean isStringComparisonFilter = isOperator(expressions[i]) && i - 2 >= 0 && isSearchString(expressions[i - 1])
          && isOperator(expressions[i - 2]);
      boolean isSimpleFilter = isSearchString(expressions[i]) && isOperator(expressions[i - 1]);
      boolean isCombiningFilter = isOperator(expressions[i]) && isOperator(expressions[i - 1]);
    }
  }
}
