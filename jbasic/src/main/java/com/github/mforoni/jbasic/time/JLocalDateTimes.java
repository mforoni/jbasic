package com.github.mforoni.jbasic.time;

import javax.annotation.Nonnull;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeParser;

/**
 * Provides {@code static} utility methods for manipulating {@link LocalDateTime} objects.
 * 
 * @author Foroni Marco
 * @see LocalDateTime
 */
public final class JLocalDateTimes {
  // Suppresses default constructor, ensuring non-instantiability.
  private JLocalDateTimes() {
    throw new AssertionError();
  }

  @Nonnull
  public static LocalDateTime parse(@Nonnull final String text, @Nonnull final String pattern) {
    final DateTimeParser parser = DateTimeFormat.forPattern(pattern).getParser();
    final DateTimeFormatter formatter = new DateTimeFormatter(null, parser);
    return formatter.parseLocalDateTime(text);
  }
}
