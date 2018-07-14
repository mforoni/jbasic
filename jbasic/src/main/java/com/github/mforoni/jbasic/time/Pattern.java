package com.github.mforoni.jbasic.time;

import javax.annotation.Nonnull;
import com.google.common.annotations.Beta;

/**
 * Joda-Time pattern as an {@code Enum} type.
 * 
 * @author Foroni Marco
 * @see Enum
 */
@Beta
public enum Pattern {
  // TODO add non numeric patterns
  /**
   * yyyy-MM-dd
   */
  YYYY_MM_DD_HYPEN("YYYY-MM-DD"),
  /**
   * yyyy/MM/dd
   */
  YYYY_MM_DD_SLASH("yyyy/MM/dd"),
  /**
   * yyyyMMdd
   */
  YYYYMMDD("yyyyMMdd"),
  /**
   * MM-dd-yyyy
   */
  MM_DD_YYYY_HYPEN("MM-dd-yyyy"),
  /**
   * MM/dd/yyyy
   */
  MM_DD_YYYY_SLASH("MM/dd/yyyy"),
  /**
   * MMddyyyy
   */
  MMDDYYYY("MMddyyyy"),
  /**
   * dd-MM-yyyy
   */
  DD_MM_YYYY_HYPEN("dd-MM-yyyy"),
  /**
   * dd/MM/yyyy
   */
  DD_MM_YYYY_SLASH("dd/MM/yyyy"),
  /**
   * ddMMyyyy
   */
  DDMMYYYY("ddMMyyyy");
  private final String s;

  private Pattern(final String s) {
    this.s = s;
  }

  @Nonnull
  public String get() {
    return s;
  }
  // TODO public boolean isNumeric() {} ?
}
