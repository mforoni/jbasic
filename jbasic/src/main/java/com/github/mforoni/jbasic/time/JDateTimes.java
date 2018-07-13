package com.github.mforoni.jbasic.time;

import javax.annotation.Nonnull;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeParser;

/**
 * Provides {@code static} utility methods for manipulating {@link DateTime} objects.
 *
 * @author Foroni Marco
 * @see DateTime
 */
public final class JDateTimes {

	// Suppresses default constructor, ensuring non-instantiability.
	private JDateTimes() {
		throw new AssertionError();
	}

	@Nonnull
	public static DateTime parse(@Nonnull final String text, @Nonnull final String pattern) {
		final DateTimeParser parser = DateTimeFormat.forPattern(pattern).getParser();
		final DateTimeFormatter formatter = new DateTimeFormatter(null, parser);
		return formatter.parseDateTime(text);
	}
}
