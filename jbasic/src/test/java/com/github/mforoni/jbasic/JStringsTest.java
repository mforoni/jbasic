package com.github.mforoni.jbasic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import com.github.mforoni.jbasic.JStrings;
import com.google.common.collect.ImmutableSet;

/**
 * @author Foroni Marco
 */
public class JStringsTest {

	private static final String NOT_YET_IMPLEMENTED = "Not yet implemented!";

	@Test
	public void testNewArray() {
		assertArrayEquals(new String[] { "first", "second" }, JStrings.newArray("first", "second"));
		assertArrayEquals(new String[] { "first", "second", "others" }, JStrings.newArray("first", "second", "others"));
	}

	@Test
	public void testSimilarsStringCollectionOfString() {
		// TODO
	}

	@Test
	public void testSimilarsStringCollectionOfStringMetricStringDistance() {
		// TODO
	}

	@Test
	public void testNewImmutableSet() {
		ImmutableSet<String> set = JStrings.newImmutableSet("first", "Second");
		assertTrue(set.contains("first"));
		assertFalse(set.contains("second"));
		set = JStrings.newImmutableSet("first", "Second", "others");
		assertTrue(set.contains("first"));
		assertFalse(set.contains("second"));
		assertTrue(set.contains("others"));
	}

	@Test
	public void testMatches() {
		// do nothing: relies on java.util.regex.Matcher.matches(String)
	}

	@Test
	public void testMatch() {
		final String key = "key";
		assertFalse(JStrings.match("key", "first", "second", "others"));
		assertTrue(JStrings.match("key", "first", "second", "others", key));
	}

	@Test
	public void testMatchIgnoreCase() {
		final String key = "key";
		assertFalse(JStrings.matchIgnoreCase("key", "first", "second", "others"));
		assertTrue(JStrings.matchIgnoreCase("keY", "first", "second", key, "others", "KeY"));
	}

	@Test
	public void testSplitStringCharArray() {
		final String[] split = JStrings.split("A very long_text that need-to be split!", new char[] { ' ', '_', '-' });
		final String[] expecteds = { "A", "very", "long", "text", "that", "need", "to", "be", "split!" };
		assertArrayEquals(expecteds, split);
	}

	@Test
	public void testSplitStringCharCharCharArray() {
		// do nothing: relies on split(String, char[])
	}

	@Test
	public void testSplitEscapingQuotedStringChar() {
		// TODO
	}

	@Test
	public void testSplitEscapingQuotedString() {
		// TODO
	}

	@Test
	public void testConcatStringIntString() {
		assertEquals("name, name, name, name", JStrings.concat("name", 4, ", "));
	}

	@Test
	public void testConcatStringStringStringArray() {
		assertEquals("nameyear", JStrings.concat("name", "year"));
		assertEquals("lastyearwasgreat", JStrings.concat("last", "year", "was", "great"));
	}

	@Test
	public void testDecapitalizeStringInt() {
		assertEquals("falcO", JStrings.decapitalize("falCO", 3));
		assertEquals("Falco", JStrings.decapitalize("FAlco", 1));
		assertEquals("falco", JStrings.decapitalize("falco", 2));
	}

	@Test
	public void testDecapitalizeString() {
		// do nothing: relies on decapitalize(String, int)
	}

	@Test
	public void testCapitalizeStringInt() {
		assertEquals("falcO", JStrings.capitalize("falco", 4));
		assertEquals("Falco", JStrings.capitalize("falco", 0));
		assertEquals("faLco", JStrings.capitalize("faLco", 2));
	}

	@Test
	public void testCapitalizeString() {
		// do nothing: relies on capitalize(String, int)
	}

	@Test
	public void testIsUpperCase() {
		assertFalse(JStrings.isUpperCase("falco"));
		assertFalse(JStrings.isUpperCase("faLCO"));
		assertTrue(JStrings.isUpperCase("FALCO"));
		assertTrue(JStrings.isUpperCase("-.-://][FALCO1234"));
		assertFalse(JStrings.isUpperCase("FALCOàè"));
		assertTrue(JStrings.isUpperCase("FALCOÀÈ"));
	}

	@Test
	public void testContainsStringChar() {
		assertTrue(JStrings.contains(NOT_YET_IMPLEMENTED, 'e'));
		assertTrue(JStrings.contains(NOT_YET_IMPLEMENTED, 'm'));
		assertFalse(JStrings.contains(NOT_YET_IMPLEMENTED, 'M'));
	}

	@Test
	public void testContainsStringCharCharCharacterArray() {
		assertTrue(JStrings.contains(NOT_YET_IMPLEMENTED, 'a', 'e'));
		assertTrue(JStrings.contains(NOT_YET_IMPLEMENTED, 'z', 'f', 'm'));
		assertFalse(JStrings.contains(NOT_YET_IMPLEMENTED, 'M', 'z'));
	}

	@Test
	public void testLowerCamelCaseStringCaseFormat() {
		// TODO
	}

	@Test
	public void testLowerCamelCaseListOfString() {
		// TODO
	}

	@Test
	public void testLowerCamelCaseStringString() {
		// TODO
	}

	@Test
	public void testLowerCamelCaseStringCharCharArray() {
		// TODO
	}

	@Test
	public void testLowerCamelCaseString() {
		assertEquals("notYetImplemented!", JStrings.lowerCamelCase(NOT_YET_IMPLEMENTED));
		assertEquals("test", JStrings.lowerCamelCase("TesT"));
	}

	@Test
	public void testRemoveConsecutiveSpaces() {
		assertEquals("Not yet implemented!", JStrings.removeConsecutiveSpaces(NOT_YET_IMPLEMENTED));
		assertEquals("A very bad formatted text with too many spaces!",
				JStrings.removeConsecutiveSpaces("A   very bad formatted   text\t with too   many spaces!"));
	}

	@Test
	public void testIndexOfConsonantString() {
		// do nothing: relies on indexOfConsonant(String, int, int)
	}

	@Test
	public void testIndexOfConsonantStringInt() {
		// do nothing: relies on indexOfConsonant(String, int, int)
	}

	@Test
	public void testIndexOfConsonantStringIntInt() {
		assertEquals(0, JStrings.indexOfConsonant(NOT_YET_IMPLEMENTED, 0, 5));
		assertEquals(2, JStrings.indexOfConsonant(NOT_YET_IMPLEMENTED, 1, 5));
		assertEquals(2, JStrings.indexOfConsonant(NOT_YET_IMPLEMENTED, 1, 10));
		assertEquals(2, JStrings.indexOfConsonant(NOT_YET_IMPLEMENTED, 2, 10));
		assertEquals(4, JStrings.indexOfConsonant(NOT_YET_IMPLEMENTED, 3, 10));
	}

	@Test
	public void testIsNumeric() {
		assertFalse(JStrings.isISOLatinDigits("SGVCNSADsfhdsdddshfhs"));
		assertFalse(JStrings.isAlphabetic("S GV"));
		assertFalse(JStrings.isISOLatinDigits("asfS_"));
		assertFalse(JStrings.isISOLatinDigits("aRSA24FDF"));
		assertFalse(JStrings.isISOLatinDigits("235424!627"));
		assertTrue(JStrings.isISOLatinDigits("235424627"));
		assertFalse(JStrings.isISOLatinDigits("23à"));
	}

	@Test
	public void testIsAlphabetic() {
		assertTrue(JStrings.isAlphabetic("SGVCNSADsfhdsdààòùddshfhs"));
		assertFalse(JStrings.isAlphabetic("S GV"));
		assertFalse(JStrings.isAlphabetic("asfS_"));
		assertFalse(JStrings.isAlphabetic("aRSA24FDF"));
		assertFalse(JStrings.isAlphabetic("235424627"));
		assertFalse(JStrings.isISOLatinDigits("23à"));
	}

	@Test
	public void testIsAlphanumeric() {
		assertTrue(JStrings.isAlphanumeric("SGVCNSADsfhdsdddshfhs"));
		assertFalse(JStrings.isAlphanumeric("asfS_"));
		assertTrue(JStrings.isAlphanumeric("aRSA24FDF"));
		assertTrue(JStrings.isAlphanumeric("235424627"));
		assertFalse(JStrings.isAlphanumeric("23542 4627"));
	}

	@Test
	public void testToAlphanumeric() {
		// TODO
	}

	@Test
	public void testToAlphabetic() {
		// TODO
	}

	@Test
	public void testReplaceNumeric() {
		// TODO
	}

	@Test
	public void testReplaceNotAlphanumeric() {
		// TODO
	}

	@Test
	public void testOccurrencesStringChar() {
		final String str1 = "akjhdfsjkfsbs l346klàba V ew ew we rct3tr";
		assertEquals(2, JStrings.occurrences(str1, 'j'));
		assertEquals(3, JStrings.occurrences(str1, 'e'));
		final String str2 = "This is a very long string that doesn't make sense but it is useful for writing a junit test";
		assertEquals(3, JStrings.occurrences(str2, 'o'));
	}

	@Test
	public void testOccurrencesStringCharArray() {
		final String str = "akjhdfsjkfsbs l346klàba V ew ew we rct3tr";
		final char[] chars = new char[] { 'j', 'e', 'j' };
		final int[] occurences = JStrings.occurrences(str, chars);
		final int[] expected = new int[] { 2, 3, 2 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], occurences[i]);
		}
	}

	@Test
	public void testPadStart() {
		assertEquals("00032", JStrings.padStart("32", 5, '0'));
		assertEquals(" text", JStrings.padStart(" text", 5, '_'));
		try {
			assertEquals("00032", JStrings.padStart("some text", 5, '_'));
			fail("Exception is expected");
		} catch (final IllegalArgumentException e) {
			// do nothing
		}
	}

	@Test
	public void testPadEnd() {
		assertEquals("32000", JStrings.padEnd("32", 5, '0'));
	}
}
