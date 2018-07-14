package com.github.mforoni.jbasic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.joda.time.LocalDate;
import org.junit.Test;
import com.github.mforoni.jbasic.JArrays;

/**
 * @author Foroni Marco
 */
public class JArraysTest {
  private static final Long[] LONG_1_2_3 = {1L, 2L, 3L};
  private static final String[] STRING_1_2_3 = {"1", "2", "3"};
  private static final String[] STRING_1L_2L_3L = {"1L", "2L", "3L"};
  private static final String[] STRING_a_b_c = {"a", "b", "c"};

  @Test
  public void testIsArray() {
    assertTrue(JArrays.isArray(LONG_1_2_3));
    assertTrue(JArrays.isArray(STRING_1_2_3));
    assertTrue(JArrays.isArray(STRING_a_b_c));
    assertTrue(JArrays.isArray(new int[] {3, 494}));
    assertFalse(JArrays.isArray(new Boolean(false)));
    assertFalse(JArrays.isArray(new Integer(3)));
    final Object o = new double[] {27.4, 2490D};
    assertTrue(JArrays.isArray(o));
  }

  @Test
  public void testConcatTArrayTArrayTArray() {}

  @Test
  public void testConcat() {
    assertArrayEquals(new Long[] {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L},
        JArrays.concat(LONG_1_2_3, new Long[] {4L, 5L, 6L}, new Long[] {7L, 8L, 9L}, Long.class));
  }

  @Test
  public void testContainsIgnoreCase() {
    // relies on indexOf(String[], String, int)
  }

  @Test
  public void testIndexOfIgnoreCaseStringArrayString() {
    // relies on indexOf(String[], String, int)
  }

  @Test
  public void testIndexOfIgnoreCaseStringArrayStringInt() {
    assertEquals(1, JArrays.indexOfIgnoreCase(STRING_1_2_3, "2", 0));
    assertEquals(-1, JArrays.indexOfIgnoreCase(STRING_a_b_c, "a", 1));
  }

  @Test
  public void testOccurrencesTArrayT() {
    assertEquals(1, JArrays.occurrences(LONG_1_2_3, 2L));
    assertEquals(0, JArrays.occurrences(LONG_1_2_3, 4));
    assertEquals(1, JArrays.occurrences(STRING_1_2_3, "2"));
    assertEquals(0, JArrays.occurrences(STRING_1_2_3, ""));
    assertEquals(0, JArrays.occurrences(STRING_1_2_3, null));
  }

  @Test
  public void testOccurrencesTArrayU() {
    assertEquals(1, JArrays.occurrences(LONG_1_2_3, 2));
    assertEquals(1, JArrays.occurrences(LONG_1_2_3, 2D));
    assertEquals(1, JArrays.occurrences(LONG_1_2_3, new Float(2.0)));
  }

  @Test
  public void testContainsTArrayT() {
    // relies on indexOf(T[], T, int)
  }

  @Test
  public void testIndexOfTArrayT() {
    // relies on indexOf(T[], T, int)
  }

  @Test
  public void testIndexOfTArrayTInt() {
    assertEquals(1, JArrays.indexOf(LONG_1_2_3, 2L, 0));
    assertEquals(-1, JArrays.indexOf(LONG_1_2_3, 4L, 0));
    assertEquals(-1, JArrays.indexOf(STRING_1L_2L_3L, "1", 0));
    assertEquals(0, JArrays.indexOf(STRING_1L_2L_3L, "1L", 0));
  }

  @Test
  public void testContainsTArrayU() {
    // relies on indexOf(T[], U, int)
  }

  @Test
  public void testIndexOfTArrayU() {
    // relies on indexOf(T[], U, int)
  }

  @Test
  public void testIndexOfTArrayUInt() {
    assertEquals(1, JArrays.indexOf(LONG_1_2_3, 2));
  }

  @Test
  public void testToStringTArray() {
    assertEquals("1,<null>,4,57,80,1900,-2",
        JArrays.toString(new Integer[] {1, null, 4, 57, 80, 1900, -2}));
    assertEquals("yes,<null>,2001-01-01,33.0",
        JArrays.toString(new Object[] {"yes", null, new LocalDate(2001, 1, 1), new Double("33")}));
  }

  @Test
  public void testPrimitiveArrayToList() {
    final int[] array = {1, 4, 57, 80, 1900, -2};
    final List<?> list = JArrays.primitiveArrayToList(array);
    for (int i = 0; i < array.length; i++) {
      final int n = array[i];
      assertTrue(list.get(i) != null && list.get(i) instanceof Integer);
      final Integer value = (Integer) list.get(i);
      assertEquals(value.intValue(), n);
    }
  }
}
