package com.github.mforoni.jbasic.time;

import javax.annotation.Nonnull;
import com.github.mforoni.jbasic.JEnums;
import com.google.common.base.Function;
import javax.annotation.Nullable;

/**
 * Represents a set of {@code Pattern}. Some of them are provided as {@code public} {@code static}
 * {@code final} members.
 * 
 * @author Foroni Marco
 * @see Pattern
 */
public class Patterns {
  public static final Patterns YEAR_MONTH_DAY_NUMERIC =
      new Patterns(Pattern.YYYY_MM_DD_HYPEN, Pattern.YYYY_MM_DD_SLASH, Pattern.YYYYMMDD);
  public static final Patterns MONTH_DAY_YEAR_NUMERIC =
      new Patterns(Pattern.MM_DD_YYYY_HYPEN, Pattern.MM_DD_YYYY_SLASH, Pattern.MMDDYYYY);
  public static final Patterns DAY_MONTH_YEAR_NUMERIC =
      new Patterns(Pattern.DD_MM_YYYY_HYPEN, Pattern.DD_MM_YYYY_SLASH, Pattern.DDMMYYYY);
  public static final Function<Pattern, String> TO_DATE_FORMAT = new Function<Pattern, String>() {
    @Nullable
    @Override
    public String apply(@Nullable final Pattern pattern) {
      return pattern != null ? pattern.get() : null;
    }
  };
  private final Pattern[] patterns;

  public Patterns(@Nonnull final Pattern first, @Nonnull final Pattern second,
      final Pattern... others) {
    this.patterns = JEnums.newArray(Pattern.class, first, second, others);
  }

  public Pattern[] get() {
    return patterns;
  }

  public String[] getStrings() {
    final String[] formats = new String[patterns.length];
    for (int i = 0; i < formats.length; i++) {
      formats[i] = patterns[i].get();
    }
    return formats;
  }
}
