package com.github.mforoni.jbasic.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.github.mforoni.jbasic.reflect.JTypes;

/**
 * @author Foroni Marco
 */
public class JTypesTest {
  @Test
  public void testNewInstance() {
    // TODO
  }

  @Test
  public void testNoArgsConstructor() {
    // TODO
  }

  @Test
  public void testToPrimitiveWrapper() {
    // TODO
  }

  @Test
  public void testToPrimitive() {
    // TODO
  }

  @Test
  public void testIsPrimitiveOrPrimitiveWrapper() {
    // TODO
  }

  @Test
  public void testIsBoolean() {
    assertTrue(JTypes.isBoolean(Boolean.class));
    assertTrue(JTypes.isBoolean(Boolean.TYPE));
    final Boolean bool = new Boolean(false);
    assertTrue(JTypes.isBoolean(bool.getClass()));
    Object obj = new Boolean(false);
    assertTrue(JTypes.isBoolean(obj.getClass()));
    obj = new Object();
    assertFalse(JTypes.isBoolean(obj.getClass()));
  }

  @Test
  public void testIsDateOrLocalDate() {
    // TODO
  }
}
