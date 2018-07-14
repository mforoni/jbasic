package com.github.mforoni.jbasic.reflect;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.github.mforoni.jbasic.reflect.JMethods;
import com.github.mforoni.jbasic.util.JTest;

public class JMethodsTest {
  @Test
  public void testGetterNameClassOfQString() {
    final String getName = JMethods.getterName(JTest.class, "name");
    assertEquals("getName", getName);
    final String getStopwatch = JMethods.getterName(JTest.class, "stopwatch");
    assertEquals("getStopwatch", getStopwatch);
  }

  @Test
  public void testGetterNameStringClassOfQ() {
    // TODO
  }

  @Test
  public void testSetterName() {
    // TODO
  }

  @Test
  public void testFind() {
    // TODO
  }

  @Test
  public void testSetter() {
    // TODO
  }

  @Test
  public void testOptionalSetter() {
    // TODO
  }

  @Test
  public void testGetter() {
    // TODO
  }

  @Test
  public void testOptionalGetter() {
    // TODO
  }

  @Test
  public void testInvokeSetterClassOfTStringTObject() {
    // TODO
  }

  @Test
  public void testInvokeSetterMethodObjectObject() {
    // TODO
  }

  @Test
  public void testInvokeGetterClassOfTStringT() {
    // TODO
  }

  @Test
  public void testInvokeGetterMethodObject() {
    // TODO
  }
}
