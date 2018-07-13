package com.github.mforoni.jbasic.time;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Timestamp;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import com.github.mforoni.jbasic.time.JDates;

public class JDatesTest {

	@Test
	public final void testCurrentTimestamp() {
		final Timestamp timestamp = JDates.currentTimestamp();
		assertNotNull(timestamp);
		final DateTime dateTime = new DateTime();
		final Date dateFromDateTime = dateTime.toDate();
		assertTrue(timestamp.before(dateFromDateTime));
		assertTrue(dateTime.withTimeAtStartOfDay().isEqual(new DateTime(timestamp.getTime()).withTimeAtStartOfDay()));
		// final Date dateFromTimestamp = new Date(timestamp.getTime());
		// assertEquals(dateFromDateTime, dateFromTimestamp);
		// assertTrue(dateFromDateTime.compareTo(dateFromTimestamp) == 0);
	}

	@Test
	public final void testIsDateInstance() {
		assertTrue(JDates.isDateInstance(new java.util.Date()));
		assertTrue(JDates.isDateInstance(new java.sql.Date(System.currentTimeMillis())));
		assertTrue(JDates.isDateInstance(new LocalDate()));
		assertTrue(JDates.isDateInstance(new DateTime()));
		assertTrue(JDates.isDateInstance(new LocalDateTime()));
		assertTrue(JDates.isDateInstance(new java.sql.Timestamp(System.currentTimeMillis())));
	}
}
