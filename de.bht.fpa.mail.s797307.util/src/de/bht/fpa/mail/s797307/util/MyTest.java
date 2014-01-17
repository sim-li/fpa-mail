package de.bht.fpa.mail.s797307.util;

import static org.junit.Assert.*;
import org.junit.Test;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797307.util.filters.IntersectionFilter;
import de.bht.fpa.mail.s797307.util.filters.ReadFilter;
import de.bht.fpa.mail.s797307.util.filters.SubjectFilter;

public class MyTest {
  @Test
  public void testReadIsFalse() {
    Message message = new Message();
    message.setRead(false);
    ReadFilter readFilter = new ReadFilter(false);
    assertTrue("ReadFilter filters for false", readFilter.filter(message));
  }

  @Test
  public void testReadIsTrue() {
    Message message = new Message();
    message.setRead(true);
    ReadFilter readFilter = new ReadFilter(true);
    assertTrue("ReadFilter filters for true", readFilter.filter(message));
  }

  @Test
  public void testSubjectRight() {
    Message message = new Message();
    message.setSubject("My pants are dancing");
    SubjectFilter subjectFilter = new SubjectFilter("My", FilterOperator.STARTS_WITH);
    assertTrue("ReadFilter filters correctly for right subject", subjectFilter.filter(message));
  }

  @Test
  public void testSubjectWrong() {
    Message message = new Message();
    message.setSubject("My pants are dancing");
    SubjectFilter subjectFilter = new SubjectFilter("aMyx", FilterOperator.STARTS_WITH);
    assertFalse("ReadFilter filters correctly for wrong subject", subjectFilter.filter(message));
  }

  public void testInterSectionWrong() {
    Message message = new Message();
    message.setSubject("My pants are dancing");
    message.setRead(true);
    IntersectionFilter intersection = new IntersectionFilter();
    SubjectFilter subjectFilter = new SubjectFilter("aMyx", FilterOperator.STARTS_WITH);
    ReadFilter readFilter = new ReadFilter(true);
    intersection.addFilters(subjectFilter, readFilter);
    assertFalse("Intersection filter fails with wrong subject", intersection.filter(message));
  }

  public void testInterSectionRight() {
    Message message = new Message();
    message.setSubject("My pants are dancing");
    message.setRead(true);
    IntersectionFilter intersection = new IntersectionFilter();
    SubjectFilter subjectFilter = new SubjectFilter("My", FilterOperator.STARTS_WITH);
    ReadFilter readFilter = new ReadFilter(true);
    intersection.addFilters(subjectFilter, readFilter);
    assertTrue("Intersection filter succeeds with right subject", intersection.filter(message));
  }

}