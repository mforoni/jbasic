package com.github.mforoni.jbasic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import org.junit.Test;
import com.github.mforoni.jbasic.JNumbers;

/**
 * @author Foroni Marco
 */
public class JNumbersTest {
  @Test
  public void testCompare() {
    assertEquals(0, JNumbers.compare(11_333L, 11_333));
    assertEquals(-1, JNumbers.compare(1_333L, 2_333));
    assertEquals(1, JNumbers.compare(10_000L, 9_999));
  }

  @Test
  public void testEqualsNumberNumber() {
    assertTrue(JNumbers.equals(11_333L, 11_333));
    assertTrue(JNumbers.equals(11_333D, 11_333));
    assertFalse(JNumbers.equals(1.5, 1));
  }

  @Test
  public void testAdd() {
    assertEquals(2_226L, JNumbers.add(2_126L, 100L));
    assertEquals(new BigDecimal(4_527), JNumbers.add(new BigDecimal(4_000), new BigDecimal(527)));
  }
}
