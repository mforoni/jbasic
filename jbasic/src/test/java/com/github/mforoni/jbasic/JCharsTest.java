package com.github.mforoni.jbasic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.github.mforoni.jbasic.JChars;

/**
 * @author Foroni Marco
 */
public class JCharsTest {

	@Test
	public void testNewArray() {
		assertArrayEquals(new char[] { 'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd' },
				JChars.newArray('h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'));
	}

	@Test
	public void testIsVowel() {
		assertTrue(JChars.isVowel('a'));
		assertTrue(JChars.isVowel('e'));
		assertTrue(JChars.isVowel('i'));
		assertTrue(JChars.isVowel('o'));
		assertTrue(JChars.isVowel('u'));
		assertFalse(JChars.isVowel('x'));
		assertFalse(JChars.isVowel('1'));
	}

	@Test
	public void testIsConsonant() {
		assertFalse(JChars.isConsonant('o'));
		assertFalse(JChars.isConsonant('u'));
		assertTrue(JChars.isConsonant('x'));
		assertFalse(JChars.isConsonant('#'));
	}
}
