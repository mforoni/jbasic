package com.github.mforoni.jbasic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import com.github.mforoni.jbasic.JBooleans;

/**
 * @author Foroni Marco
 */
public class JBooleansTest {
  @Test
  public final void testParseNullable() {
    assertNull(JBooleans.parseNullable(null));
  }

  @Test
  public final void testParse() {
    assertTrue(JBooleans.parse("yes"));
    assertTrue(JBooleans.parse("yeS"));
    assertTrue(JBooleans.parse("tRue"));
    assertFalse(JBooleans.parse("no"));
    assertFalse(JBooleans.parse("fALSe"));
    try {
      JBooleans.parse(" yes");
      fail();
    } catch (final IllegalArgumentException e) {
      assertNotNull(e);
    }
  }
}
