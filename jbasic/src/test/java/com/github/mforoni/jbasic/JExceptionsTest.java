package com.github.mforoni.jbasic;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.github.mforoni.jbasic.JExceptions;

public class JExceptionsTest {
  @Test
  public void testNewIllegalArgument() {
    assertEquals("Expected formatted text having one number: 13", JExceptions
        .newIllegalArgument("Expected formatted text having one number: %s", 13).getMessage());
    assertEquals("Expected formatted text having some numbers: 13 and 27",
        JExceptions
            .newIllegalArgument("Expected formatted text having some numbers: %s and %s", 13, 27)
            .getMessage());
  }

  @Test
  public void testNewIllegalState() {
    // same of newIllegalArgument(...)
  }

  @Test
  public void testToStringThrowable() {
    // TODO
  }
}
