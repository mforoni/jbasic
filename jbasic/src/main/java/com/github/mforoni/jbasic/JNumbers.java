package com.github.mforoni.jbasic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;

/**
 * Provides some {@code static} utility methods for dealing with {@code Number} objects, performing
 * operations like comparing and adding two numbers.
 * 
 * @author Foroni
 * @see Number
 */
public final class JNumbers {
  // Suppresses default constructor, ensuring non-instantiability.
  private JNumbers() {
    throw new AssertionError();
  }

  /**
   * {@link Comparator} for {@code Number} objects. Performs
   * {@link JNumbers#compare(Number, Number)}.
   */
  public static final Comparator<Number> COMPARATOR = new Comparator<Number>() {
    @Override
    public int compare(@Nonnull final Number o1, @Nonnull final Number o2) {
      return JNumbers.compare(o1, o2);
    }
  };

  /**
   * Compares the two given {@code Number} objects. The numbers are converted to {@code BigDecimal}
   * and then compared performing {@link BigDecimal#compareTo(BigDecimal)}.
   * 
   * @param n1 the first non-null {@code Number}
   * @param n2 the second non-null {@code Number}
   * @return -1, 0, or 1 as {@code n1} is numerically less than, equal to, or greater than
   *         {@code n2}.
   * @see Number
   * @see BigDecimal
   * @see BigDecimal#compareTo(BigDecimal)
   */
  public static int compare(@Nonnull final Number n1, @Nonnull final Number n2) {
    // http://stackoverflow.com/questions/3214448/comparing-numbers-in-java
    final BigDecimal b1 = new BigDecimal(n1.doubleValue());
    final BigDecimal b2 = new BigDecimal(n2.doubleValue());
    return b1.compareTo(b2);
  }

  /**
   * Returns <tt>true</tt> if the two specified {@code Number} objects are equals, otherwise returns
   * <tt>false</tt>.
   * 
   * @param n1 the first {@code Number}
   * @param n2 the second {@code Number}
   * @return <tt>true</tt> if the two specified {@code Number} objects are equals, otherwise returns
   *         <tt>false</tt>
   * @see Number
   * @see JNumbers#compare(Number, Number)
   */
  public static boolean equals(@Nullable final Number n1, @Nullable final Number n2) {
    if (n1 == null || n2 == null) {
      return n1 == null && n2 == null;
    }
    return compare(n1, n2) == 0;
  }

  @Beta
  @Nonnull
  public static Number add(@Nonnull final Number n1, @Nonnull final Number n2) {
    Preconditions.checkNotNull(n1);
    Preconditions.checkNotNull(n2);
    if (n1 instanceof Integer && n2 instanceof Integer) {
      return n1.intValue() + n2.intValue();
    } else if (n1 instanceof Long && n2 instanceof Long) {
      return n1.longValue() + n2.longValue();
    } else if (n1 instanceof Double && n2 instanceof Double) {
      return n1.doubleValue() + n2.doubleValue();
    } else if (n1 instanceof Float && n2 instanceof Float) {
      return n1.floatValue() + n2.floatValue();
    } else if (n1 instanceof Short && n2 instanceof Short) {
      return n1.shortValue() + n2.shortValue();
    } else if (n1 instanceof BigDecimal && n2 instanceof BigDecimal) {
      final BigDecimal b1 = (BigDecimal) n1;
      final BigDecimal b2 = (BigDecimal) n2;
      return b1.add(b2);
    } else if (n1 instanceof Byte && n2 instanceof Byte) {
      return n1.byteValue() + n2.byteValue();
    } else if (n1 instanceof BigInteger && n2 instanceof BigInteger) {
      final BigInteger b1 = (BigInteger) n1;
      final BigInteger b2 = (BigInteger) n2;
      return b1.add(b2);
    } else {
      throw new IllegalArgumentException(
          "The arguments n1 and n2 must have the same type and the type must be a subclass of Number");
    }
  }
}
