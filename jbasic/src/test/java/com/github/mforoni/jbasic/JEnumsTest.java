package com.github.mforoni.jbasic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * @author Foroni Marco
 */
public class JEnumsTest {
  private static final String[] NAMES = {"FIRST", "SECOND", "THIRD", "FORTH", "FIFTH"};

  private enum TestEnum {
    FIRST, SECOND, THIRD, FORTH, FIFTH
  }
  private enum Weapon {
    DRAGONSTONE, STAFF, BOW, DAGGER, SWORD, LANCE, AXE, RED_TOME, BLUE_TOME, GREEN_TOME, BEAST_WEAPON
  }

  @Test
  public void testNewArray() {
    assertArrayEquals(new TestEnum[] {TestEnum.FIRST, TestEnum.THIRD, TestEnum.FORTH},
        JEnums.newArray(TestEnum.class, TestEnum.FIRST, TestEnum.THIRD, TestEnum.FORTH));
  }

  @Test
  public void testGetNames() {
    assertArrayEquals(NAMES, JEnums.names(TestEnum.class));
  }

  @Test
  public void testOptionalValueOf() {
    assertFalse(JEnums.optionalValueOf(TestEnum.class, "forth").isPresent());
    assertTrue(JEnums.optionalValueOf(TestEnum.class, "FORTH").isPresent());
  }

  @Test
  public void testConventionalValueOf() {
    try {
      JEnums.conventionalValueOf(TestEnum.class, "forth ");
      fail("Exception is expected");
    } catch (final IllegalArgumentException e) {
      // do nothing
    }
    assertEquals(TestEnum.FORTH, JEnums.conventionalValueOf(TestEnum.class, "forth."));
    assertEquals(Weapon.RED_TOME, JEnums.conventionalValueOf(Weapon.class, "RED TOME"));
    assertEquals(Weapon.RED_TOME, JEnums.conventionalValueOf(Weapon.class, "RED_TOME"));
  }
}
