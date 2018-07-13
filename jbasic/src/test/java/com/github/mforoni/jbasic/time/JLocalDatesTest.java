package com.github.mforoni.jbasic.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.junit.Test;
import com.github.mforoni.jbasic.time.JLocalDates;
import com.github.mforoni.jbasic.time.Pattern;

public class JLocalDatesTest {

	private static final String D99_08_2013_SLASH = "99/08/2013"; // not valid
	private static final String D3_8_2016_SLASH = "3/8/2016"; // valid
	private static final String D03_08_16_SLASH = "03/08/16"; // valid
	private static final String D2_13_2016_HYPEN = "2-13-2016"; // valid
	private static final String D02_13_2016_HYPEN = "02-13-2016"; // valid
	private static final String D29_02_2018_SLASH = "29/02/2018"; // not valid
	private static final String D02_29_2018_SLASH = "02/29/2018"; // not valid
	private static final String D01_02_09_SLASH = "01/02/09"; // valid but muliple valid formats
	private static final LocalDate MARCH_8_2016 = new LocalDate(2016, 3, 8);

	@Test
	public final void testToday() {
		final LocalDate today = JLocalDates.today();
		assertNotNull(today);
		assertTrue(today.isAfter(JLocalDates.yesterday()));
		assertTrue(today.isBefore(JLocalDates.tomorrow()));
	}

	@Test
	public final void testYesterday() {
		assertNotNull(JLocalDates.yesterday());
	}

	@Test
	public final void testTomorrow() {
		assertNotNull(JLocalDates.tomorrow());
	}

	@Test
	public final void testParseStringPattern() {
		// 99/08/2013
		try {
			JLocalDates.parse(D99_08_2013_SLASH, Pattern.MM_DD_YYYY_SLASH);
			fail("Exception is expected");
		} catch (final Throwable t) {
			assertEquals(IllegalFieldValueException.class, t.getClass());
		}
		// 3/8/2016
		try {
			JLocalDates.parse(D3_8_2016_SLASH, Pattern.MM_DD_YYYY_HYPEN);
			fail("Exception is expected");
		} catch (final Throwable t) {
			assertEquals(IllegalArgumentException.class, t.getClass());
		}
		assertEquals(MARCH_8_2016, JLocalDates.parse(D3_8_2016_SLASH, Pattern.MM_DD_YYYY_SLASH));
		try {
			JLocalDates.parse(D3_8_2016_SLASH, Pattern.MMDDYYYY);
			fail("Exception is expected");
		} catch (final Throwable t) {
			assertEquals(IllegalArgumentException.class, t.getClass());
		}
		// 03/08/16
		assertNotEquals(MARCH_8_2016, JLocalDates.parse(D03_08_16_SLASH, Pattern.MM_DD_YYYY_SLASH));
		assertEquals(new LocalDate(16, 3, 8), JLocalDates.parse(D03_08_16_SLASH, Pattern.MM_DD_YYYY_SLASH));
	}

	@Test
	public final void testParseStringString() {
		// do nothing: relies on parse(String, pattern)
	}

	@Test
	public final void testParseStringStringStringStringArray() {
		// do nothing: relies on parse(String, String[])
	}

	@Test
	public final void testParseStringStringArray() {
		assertEquals(MARCH_8_2016, JLocalDates.parse(D3_8_2016_SLASH, JLocalDates.MONTH_DAY_YEAR_NUMERIC_DATE_FORMATS));
	}

	@Test
	public final void testParseString() {
		// do nothing: relies on parse(String, )
	}

	@Test
	public final void testInferredLocalDatesString() {
		// do nothing: relies on inferLocalDates(String, String[])
	}

	@Test
	public final void testInferredLocalDatesStringStringArray() {
		assertTrue(JLocalDates.inferredLocalDates(D99_08_2013_SLASH, JLocalDates.NUMERIC_DATE_FORMATS).size() == 0);
		final LocalDate expected = new LocalDate(2016, 8, 30);
		// assertEquals(expected, JDateTimes.inferLocalDate(D3_8_2016));
		// assertEquals(expected, JDateTimes.inferLocalDate(D03_08_2016));
		// expected = new LocalDate(2016, 2, 13);
		// assertEquals(expected, JDateTimes.inferLocalDate(D2_13_2016_HYPEN));
		// assertEquals(expected, JDateTimes.inferLocalDate(D02_13_2016_HYPEN));
		assertTrue(JLocalDates.inferredLocalDates(D29_02_2018_SLASH, JLocalDates.NUMERIC_DATE_FORMATS).size() == 0);
		assertTrue(JLocalDates.inferredLocalDates(D02_29_2018_SLASH, JLocalDates.NUMERIC_DATE_FORMATS).size() == 0);
	}

	@Test
	public final void testIsParsableString() {
		// do nothing: relies on isParsable(String, String[])
	}

	@Test
	public final void testIsParsableStringStringArray() {
		assertFalse(JLocalDates.isParsable(D99_08_2013_SLASH, JLocalDates.NUMERIC_DATE_FORMATS));
		assertTrue(JLocalDates.isParsable(D3_8_2016_SLASH, JLocalDates.MONTH_DAY_YEAR_NUMERIC_DATE_FORMATS));
		assertTrue(JLocalDates.isParsable(D03_08_16_SLASH, JLocalDates.NUMERIC_DATE_FORMATS));
		assertTrue(JLocalDates.isParsable(D2_13_2016_HYPEN, JLocalDates.NUMERIC_DATE_FORMATS));
		assertFalse(JLocalDates.isParsable(D02_13_2016_HYPEN, JLocalDates.DAY_MONTH_YEAR_NUMERIC_DATE_FORMATS));
		assertFalse(JLocalDates.isParsable(D29_02_2018_SLASH, JLocalDates.NUMERIC_DATE_FORMATS));
		assertFalse(JLocalDates.isParsable(D02_29_2018_SLASH, JLocalDates.NUMERIC_DATE_FORMATS));
	}
}
