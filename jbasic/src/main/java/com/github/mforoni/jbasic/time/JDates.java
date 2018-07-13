package com.github.mforoni.jbasic.time;

import java.sql.Timestamp;
import javax.annotation.Nonnull;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import com.google.common.annotations.Beta;

/**
 * Provides {@code static} utility methods for handling dates and timestamps of native JDK objects.
 * 
 * @author Foroni
 */
@Beta
public final class JDates {

	// Suppresses default constructor, ensuring non-instantiability.
	private JDates() {
		throw new AssertionError();
	}

	/**
	 * Returns the current time as a {@link Timestamp} type.
	 * 
	 * @return the current time
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Returns <tt>true</tt> if the specified object is an instance of type {@link java.util.Date},
	 * {@link java.sql.Date}, {@link LocalDate}, {@link DateTime}, or {@link LocalDateTime}; otherwise returns
	 * <tt>false</tt>.
	 * 
	 * @param obj
	 * @return <tt>true</tt> if the specified object is an instance of type {@link java.util.Date},
	 *         {@link java.sql.Date}, {@link LocalDate}, {@link DateTime}, or {@link LocalDateTime}
	 */
	@Beta
	public static boolean isDateInstance(@Nonnull final Object obj) {
		return (obj instanceof java.util.Date || obj instanceof java.sql.Date || obj instanceof LocalDate || obj instanceof DateTime
				|| obj instanceof LocalDateTime);
	}
}
