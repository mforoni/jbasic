package com.github.mforoni.jbasic.time;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import com.github.mforoni.jbasic.JArrays;
import com.github.mforoni.jbasic.JStrings;

/**
 * Provides {@code static} utility methods for manipulating {@link LocalDate} objects.
 * 
 * @author Foroni Marco
 * @see LocalDate
 * @see Pattern
 * @see Patterns
 */
public final class JLocalDates {
  /**
   * @see Pattern#YYYY_MM_DD_HYPEN
   */
  public static final Pattern ISO_8601_PATTERN = Pattern.YYYY_MM_DD_SLASH;
  public static final String[] YEAR_MONTH_DAY_NUMERIC_DATE_FORMATS =
      Patterns.YEAR_MONTH_DAY_NUMERIC.getStrings();
  public static final String[] MONTH_DAY_YEAR_NUMERIC_DATE_FORMATS =
      Patterns.MONTH_DAY_YEAR_NUMERIC.getStrings();
  public static final String[] DAY_MONTH_YEAR_NUMERIC_DATE_FORMATS =
      Patterns.DAY_MONTH_YEAR_NUMERIC.getStrings();
  public static final String[] NUMERIC_DATE_FORMATS =
      JArrays.concat(YEAR_MONTH_DAY_NUMERIC_DATE_FORMATS, MONTH_DAY_YEAR_NUMERIC_DATE_FORMATS,
          DAY_MONTH_YEAR_NUMERIC_DATE_FORMATS, String.class);

  // Suppresses default constructor, ensuring non-instantiability.
  private JLocalDates() {
    throw new AssertionError();
  }

  /**
   * Returns today {@link LocalDate}
   * 
   * @return today {@link LocalDate}.
   * @see LocalDate
   */
  @Nonnull
  public static LocalDate today() {
    return new LocalDate();
  }

  /**
   * Returns yesterday {@link LocalDate}
   * 
   * @return yesterday {@link LocalDate}
   * @see LocalDate
   */
  @Nonnull
  public static LocalDate yesterday() {
    return new LocalDate().minusDays(1);
  }

  /**
   * Returns tomorrow {@link LocalDate}
   * 
   * @return tomorrow {@link LocalDate}
   * @see LocalDate
   */
  @Nonnull
  public static LocalDate tomorrow() {
    return new LocalDate().plusDays(1);
  }

  @Nonnull
  public static LocalDate parse(@Nonnull final String text, @Nonnull final Pattern pattern) {
    return parse(text, pattern.get());
  }

  /**
   * Returns a {@code LocalDate} if the given {@code text} can be parsed with the specified
   * {@code pattern}.
   * 
   * @param text the string date to parse
   * @param pattern the pattern used for parsing
   * @return a {@code LocalDate} if the given {@code text} can be parsed with the specified
   *         {@code pattern}.
   * @throws IllegalArgumentException if the text to parse is invalid
   * @see DateTimeParser
   * @see DateTimeFormatter
   */
  @Nonnull
  public static LocalDate parse(@Nonnull final String text, @Nonnull final String pattern) {
    final DateTimeParser parser = DateTimeFormat.forPattern(pattern).getParser();
    final DateTimeFormatter formatter = new DateTimeFormatter(null, parser);
    return formatter.parseLocalDate(text);
  }

  /**
   * Returns a {@code LocalDate} if the given {@code text} can be parsed with the one of the
   * specified patterns.
   * 
   * @param text the string date to parse
   * @param firstPattern the first pattern to be used for parsing
   * @param secondPattern the second pattern to be used for parsing
   * @param otherPatterns
   * @return a {@code LocalDate} if the given {@code text} can be parsed with the one of the
   *         specified patterns.
   * @throws IllegalArgumentException if the text to parse is invalid for all the specified patterns
   */
  @Nonnull
  public static LocalDate parse(@Nonnull final String text, @Nonnull final String firstPattern,
      @Nonnull final String secondPattern, final String... otherPatterns) {
    final String[] patterns = JStrings.newArray(firstPattern, secondPattern, otherPatterns);
    return parse(text, patterns);
  }

  @Nonnull
  public static LocalDate parse(@Nonnull final String text, @Nonnull final Patterns patterns) {
    return parse(text, patterns.getStrings());
  }

  @Nonnull
  public static LocalDate parse(@Nonnull final String text, @Nonnull final String[] patterns) {
    final DateTimeParser[] parsers = new DateTimeParser[patterns.length];
    for (int i = 0; i < patterns.length; i++) {
      parsers[i] = DateTimeFormat.forPattern(patterns[i]).getParser();
    }
    final DateTimeFormatter formatter =
        new DateTimeFormatterBuilder().append(null, parsers).toFormatter();
    return formatter.parseLocalDate(text);
  }

  /**
   * Returns a {@code LocalDate} if the given {@code text} can be parsed with the pattern
   * {@link JLocalDates#ISO_8601_PATTERN}.
   * 
   * @param text the string date to parse
   * @return a {@code LocalDate} if the given {@code text} can be parsed with the pattern
   *         {@link JLocalDates#ISO_8601_PATTERN}.
   * @throws IllegalArgumentException if the text to parse is invalid
   */
  @Nonnull
  public static LocalDate parse(@Nonnull final String text) {
    return parse(text, ISO_8601_PATTERN.get());
  }

  /**
   * Returns a list of {@link InferredLocalDate} objects where each one contains the successful
   * parsing of the given {@code text} using one of the format pattern of the array
   * {@link JLocalDates#NUMERIC_DATE_FORMATS}. The parsing operation is performed with
   * {@link DateTimeFormatter#parseDateTime(String)}. If no parsing is successful returns an empty
   * list.
   * 
   * @param text
   * @return a list of {@code InferredLocalDate} objects where each one contains the successful
   *         parsing of the given {@code text}
   * @see InferredLocalDate
   */
  @Nonnull
  public static List<InferredLocalDate> inferredLocalDates(@Nonnull final String text) {
    return inferredLocalDates(text, NUMERIC_DATE_FORMATS);
  }

  @Nonnull
  public static List<InferredLocalDate> inferredLocalDates(@Nonnull final String text,
      @Nonnull final Patterns patterns) {
    return inferredLocalDates(text, patterns.getStrings());
  }

  @Nonnull
  public static List<InferredLocalDate> inferredLocalDates(@Nonnull final String text,
      @Nonnull final String[] patterns) {
    final List<InferredLocalDate> formats = new ArrayList<>();
    for (final String pattern : patterns) {
      try {
        final LocalDate localDate = parse(text, pattern);
        formats.add(new InferredLocalDate(localDate, pattern, text));
      } catch (final Throwable t) {
        // do nothing
      }
    }
    return formats;
  }

  public static boolean isParsable(@Nonnull final String text) {
    return inferredLocalDates(text).size() > 0;
  }

  public static boolean isParsable(@Nonnull final String text, @Nonnull final String[] patterns) {
    return inferredLocalDates(text, patterns).size() > 0;
  }

  /**
   * Represents a {@code LocalDate} obtained parsing the {@code originalText} using the pattern
   * {@code format} with JodaTime API.
   * 
   * @author Foroni
   * @see LocalDate
   */
  @Immutable
  public static class InferredLocalDate {
    private final LocalDate localDate;
    private final String format;
    private final String originalText;

    public InferredLocalDate(final LocalDate localDate, final String format,
        final String originalText) {
      super();
      this.localDate = localDate;
      this.format = format;
      this.originalText = originalText;
    }

    public LocalDate getLocalDate() {
      return localDate;
    }

    public String getFormat() {
      return format;
    }

    public String getOriginalText() {
      return originalText;
    }

    @Override
    public String toString() {
      return "InferredLocalDate [localDate=" + localDate + ", format=" + format + ", originalText="
          + originalText + "]";
    }
  }
}
