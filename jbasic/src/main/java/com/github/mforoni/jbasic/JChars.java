package com.github.mforoni.jbasic;

import javax.annotation.Nonnull;

/**
 * This class consists of {@code static} utility methods for dealing with characters.
 *
 * @author Foroni Marco
 */
public final class JChars {
  /** The Constant VOWELS. */
  public static final String VOWELS = "aeiouAEIOU";
  /** The Constant CONSONANTS. */
  public static final String CONSONANTS = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";

  // Suppresses default constructor, ensuring non-instantiability.
  private JChars() {
    throw new AssertionError();
  }

  /**
   * Returns a new array from the given arguments.
   * 
   * @param first the first element of the array
   * @param second the second element of the array
   * @param others the others element of the array
   * @return a new array from the given arguments.
   */
  @Nonnull
  public static char[] newArray(final char first, final char second, final char... others) {
    final char[] array = new char[2 + others.length];
    array[0] = first;
    array[1] = second;
    System.arraycopy(others, 0, array, 2, others.length);
    return array;
  }

  /**
   * Checks if the character is a vowel.
   *
   * @param ch the character
   * @return <tt>true</tt> iff the character is a vowel.
   */
  public static boolean isVowel(final char ch) {
    return JStrings.contains(VOWELS, ch);
  }

  /**
   * Checks if the character is a consonant.
   *
   * @param ch the character
   * @return <tt>true</tt> iff the character is a consonant.
   */
  public static boolean isConsonant(final char ch) {
    return JStrings.contains(CONSONANTS, ch);
  }
}
