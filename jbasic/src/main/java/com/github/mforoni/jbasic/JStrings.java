package com.github.mforoni.jbasic;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import com.google.common.annotations.Beta;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Chars;
import info.debatty.java.stringsimilarity.Levenshtein;
import info.debatty.java.stringsimilarity.interfaces.MetricStringDistance;
import ru.lanwen.verbalregex.VerbalExpression;

/**
 * Provides {@code static} utility methods for manipulating {@code String} objects.
 *
 * @author Foroni Marco
 * @see String
 * @see Strings
 */
public final class JStrings {
  public static final String EMPTY_STRING = "";
  public static final String NEWLINE = System.getProperty("line.separator");
  public static final String ISO_LATIN_1_DIGITS_REGEX = "[0-9]+";
  public static final String ISO_BASIC_LATIN_ALPHABET_REGEX = "[A-Za-z]+";
  public static final String NOT_ISO_LATIN_ALPHABET_OR_DIGITS_REGEX = "[^A-Za-z0-9]";
  public static final String CONSECUTIVE_SPACES_REGEX = "\\s+";

  // Suppresses default constructor, ensuring non-instantiability.
  private JStrings() {
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
  public static String[] newArray(final String first, final String second, final String... others) {
    final String[] array = new String[2 + others.length];
    array[0] = first;
    array[1] = second;
    System.arraycopy(others, 0, array, 2, others.length);
    return array;
  }

  /**
   * Finds the similar strings in the specified {@link Collection} having minimum
   * {@link Levenshtein} distance from the given {@code key}.
   * 
   * @param key the string to search in the {@code Collection}
   * @param collection the {@code Collection} of strings
   * @return a {@code Set} of strings having minimum {@link Levenshtein} distance from the given
   *         {@code key}
   * @see Levenshtein
   */
  @Nonnull
  public static Set<String> similars(final String key, final Collection<String> collection) {
    return similars(key, collection, new Levenshtein());
  }

  /**
   * Finds the similar strings in the specified {@link Collection} having minimum
   * {@link MetricStringDistance} distance from the given {@code key}.
   * 
   * @param key the string to search in the {@code collection}
   * @param collection the {@code Collection} of strings
   * @return a {@code Set} of strings having minimum {@link MetricStringDistance} distance from the
   *         given {@code key}
   * @see MetricStringDistance
   */
  @Nonnull
  public static Set<String> similars(final String key, final Collection<String> collection,
      final MetricStringDistance metricStringDistance) {
    final Set<String> similars = new HashSet<>();
    double min = Double.MAX_VALUE;
    for (final String s : collection) {
      final double distance = metricStringDistance.distance(s, key);
      if (distance < min) {
        min = distance;
        similars.clear();
        similars.add(s);
      } else if (distance == min) {
        similars.add(s);
      }
    }
    return similars;
  }

  /**
   * Returns a new {@link ImmutableSet} from the specified strings.
   * 
   * @param first the first string
   * @param second the second string
   * @param others the other strins
   * @return a new {@link ImmutableSet} from the specified strings.
   */
  @Nonnull
  public static ImmutableSet<String> newImmutableSet(@Nonnull final String first,
      @Nonnull final String second, final String... others) {
    return ImmutableSet.<String>builder().add(first).add(second).add(others).build();
  }

  /**
   * Returns <tt>true</tt> if the given {@code text} matches the specified {@code pattern},
   * <tt>false</tt> otherwise.
   * 
   * @param text the text to match
   * @param pattern the pattern to be used
   * @return <tt>true</tt> if the given {@code text} matches the specified {@code pattern},
   *         <tt>false</tt> otherwise.
   * @see Pattern
   */
  public static boolean matches(@Nonnull final String text, @Nonnull final Pattern pattern) {
    final Matcher matcher = pattern.matcher(text);
    return matcher.matches();
  }

  /**
   * Returns <tt>true</tt> if and only if {@code key} is equals to {@code first}, or {@code second},
   * or one of the strings in {@code others}, otherwise returns <tt>false</tt>.
   * 
   * @param key the key to search
   * @param first the first string
   * @param second the second string
   * @param others the other strings
   * @return <tt>true</tt> if and only if {@code key} is equals to {@code first}, or {@code second},
   *         or {@code others}
   * @see String#equals(Object)
   */
  @Beta
  public static boolean match(@Nonnull final String key, @Nonnull final String first,
      @Nonnull final String second, final String... others) {
    return newImmutableSet(first, second, others).contains(key);
  }

  @Beta
  public static boolean matchIgnoreCase(@Nonnull final String key, @Nonnull final String first,
      @Nonnull final String second, final String... others) {
    final Set<String> all = new HashSet<>();
    all.add(first.toLowerCase());
    all.add(second.toLowerCase());
    for (final String s : others) {
      all.add(s.toLowerCase());
    }
    return all.contains(key.toLowerCase());
  }

  @Beta
  public static String[] split(final String s, final char[] separators) {
    final VerbalExpression regex = VerbalExpression.regex().anyOf(new String(separators)).build();
    return s.split(regex.toString());
  }

  @Beta
  public static String[] split(final String s, final char firstSeparator,
      final char secondSeparator, final char... otherSeparators) {
    final char[] separators = JChars.newArray(firstSeparator, secondSeparator, otherSeparators);
    return split(s, separators);
  }

  @Beta
  public static String[] splitEscapingQuoted(@Nonnull final String input, final char separator) {
    return input.split(String.valueOf(separator).concat("(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"), -1);
  }

  @Beta
  public static String[] splitEscapingQuoted(@Nonnull final String input) {
    return splitEscapingQuoted(input, ',');
  }

  /**
   * Returns a new string obtained by concatenating the given string {@code s} with itself the given
   * number of {@code times}, using the specified string as {@code separator}.
   * 
   * @param s the string to concatenate
   * @param times the times the concatenation need to be executed
   * @param separator the separator used
   * @return the concatenation of the given string {@code s} with itself the given number of
   *         {@code times}, using the specified string as {@code separator}.
   */
  @Nonnull
  public static String concat(@Nonnull final String s, final int times,
      @Nonnull final String separator) {
    final StringBuilder sb = new StringBuilder(s);
    for (int i = 1; i < times; i++) {
      sb.append(separator).append(s);
    }
    return sb.toString();
  }

  /**
   * Returns a new string obtained by the concatenation of the given strings.
   * 
   * @param first the first string to concatenate
   * @param second the second string to concatenate
   * @param others the other strings to concatenate
   * @return a new string obtained by the concatenation of the given strings
   */
  @Nonnull
  public static String concat(@Nonnull final String first, @Nonnull final String second,
      final String... others) {
    final StringBuilder sb = new StringBuilder(first).append(second);
    for (final String s : others) {
      sb.append(s);
    }
    return sb.toString();
  }

  /**
   * Returns the string {@code s} with the character at position {@code index} converted in lower
   * case.
   *
   * @param s the string
   * @param index the index of the character to convert
   * @return the string {@code s} with the character at position {@code index} converted in lower
   *         case.
   */
  @Nonnull
  public static String decapitalize(@Nonnull final String s, final int index) {
    if (index < 0 || index > s.length()) {
      throw new StringIndexOutOfBoundsException(index);
    }
    final char c = Character.toLowerCase(s.charAt(index));
    return s.substring(0, index) + c + s.substring(index + 1);
  }

  /**
   * Returns the string {@code s} with the character at first position converted in lower case.
   *
   * @param s the string
   * @return the string {@code s} with the character at first position converted in lower case.
   */
  @Nonnull
  public static String decapitalize(@Nonnull final String s) {
    return decapitalize(s, 0);
  }

  /**
   * Returns the string {@code s} with the character at position {@code index} converted in upper
   * case.
   *
   * @param s the string
   * @param index the index of the character to convert
   * @return the string {@code s} with the character at position {@code index} converted in upper
   *         case.
   */
  @Nonnull
  public static String capitalize(@Nonnull final String s, final int index) {
    if (index < 0 || index > s.length()) {
      throw new StringIndexOutOfBoundsException(index);
    }
    final char c = Character.toUpperCase(s.charAt(index));
    return s.substring(0, index) + c + s.substring(index + 1);
  }

  /**
   * Returns the string {@code s} with the character at first position converted in upper case.
   *
   * @param s the string
   * @return the string {@code s} with the character at first position converted in upper case.
   */
  @Nonnull
  public static String capitalize(@Nonnull final String s) {
    return capitalize(s, 0);
  }

  @Beta
  @Nonnull
  public static boolean isUpperCase(@Nonnull final String s) {
    return s.equals(s.toUpperCase());
  }

  /**
   * Returns <tt>true</tt> if the string {@code s} contains the character {@code c}, <tt>false</tt>
   * otherwise.
   *
   * @param s the string to check
   * @param c the character
   * @return <tt>true</tt> if the string {@code s} contains the character {@code c}
   */
  public static boolean contains(@Nonnull final String s, final char c) {
    return s.indexOf(c) != -1 ? true : false;
  }

  /**
   * Returns <tt>true</tt> if the string {@code s} contains one of the specified characters,
   * <tt>false</tt> otherwise.
   * 
   * @param s the string to check
   * @param first the first character
   * @param second the second character
   * @param others the other characters
   * @return <tt>true</tt> if the string {@code s} contains one of the specified characters
   */
  public static boolean contains(@Nonnull final String s, final char first, final char second,
      final Character... others) {
    final ImmutableSet<Character> set =
        ImmutableSet.<Character>builder().add(first).add(second).add(others).build();
    for (int i = 0; i < s.length(); i++) {
      if (set.contains(s.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a new string as the lower <a href= "https://en.wikipedia.org/wiki/CamelCase">Camel
   * Case</a> version of the specified {@code text} having the given {@link CaseFormat}.
   * 
   * @param text the text to convert
   * @return a new string as the lower camel case version of the specified text {@code s}
   * @see CaseFormat
   */
  @Nonnull
  public static String lowerCamelCase(@Nonnull final String text,
      @Nonnull final CaseFormat caseFormat) {
    return CaseFormat.LOWER_CAMEL.to(caseFormat, text);
  }

  /**
   * Returns the lower <a href= "https://en.wikipedia.org/wiki/CamelCase">Camel Case</a> version of
   * the given phrase represented by the given list of strings where each string is treated as a
   * word.
   * 
   * @param strings a {@code List} of {@code String} objects
   * @return a {@code String} containing the lower Camel Case version of the given input
   */
  @Beta
  @Nonnull
  public static String lowerCamelCase(@Nonnull final List<String> strings) {
    Preconditions.checkArgument(strings.size() > 0);
    final StringBuilder sb = new StringBuilder(strings.get(0).toLowerCase());
    for (int i = 1; i < strings.size(); i++) {
      sb.append(JStrings.capitalize(strings.get(i).toLowerCase()));
    }
    return sb.toString();
  }

  @Beta
  public static String lowerCamelCase(@Nonnull final String text, final String regex) {
    final String[] split = text.split(regex);
    return lowerCamelCase(Arrays.asList(split));
  }

  @Beta
  public static String lowerCamelCase(@Nonnull final String text, final char separator,
      final char... others) {
    final char[] all = Chars.concat(new char[] {separator}, others);
    final String regex = VerbalExpression.regex().anyOf(new String(all)).build().toString();
    return lowerCamelCase(text, regex);
  }

  @Beta
  public static String lowerCamelCase(@Nonnull final String text) {
    return lowerCamelCase(text, ' ', '_', '-');
  }

  @Beta
  @Nonnull
  public static String removeConsecutiveSpaces(@Nonnull final String s) {
    return s.replaceAll("\\s+", " ");
  }

  /**
   * Returns the string {@code s} truncated after the first consonant found after the specified
   * {@code index}.
   *
   * @param s the string
   * @param index the index to start the searching
   * @return the string {@code s} truncated after the first consonant found after the index
   *         {@code index}.
   */
  @Beta
  private static String truncateAtFirstConsonant(final String s, final int index) {
    int indexTruncate = indexOfConsonant(s, index);
    if (indexTruncate == -1) {
      return s;
    }
    indexTruncate = indexTruncate == s.length() - 1 ? indexTruncate : indexTruncate + 1;
    return s.substring(0, indexTruncate);
  }

  /**
   * Returns the index of the first consonant in the string {@code s}. Returns <tt>-1</tt> if no
   * consonant is found.
   *
   * @param s the string
   * @return the index of the first consonant in the string {@code s}. Returns <tt>-1</tt> if no
   *         consonant is found.
   * @see #indexOfConsonant(String, int, int)
   */
  public static int indexOfConsonant(final String s) {
    return indexOfConsonant(s, 0, s.length());
  }

  /**
   * Returns the index of the first consonant in the string {@code s} starting the search from the
   * index {@code fromIndex}, inclusive. Returns <tt>-1</tt> if no consonant is found.
   *
   * @param s the string
   * @param fromIndex the starting index to check
   * @return the index of the first consonant in the string {@code s} starting the search from the
   *         index {@code fromIndex}, inclusive. Returns <tt>-1</tt> if no consonant is found.
   * @see #indexOfConsonant(String, int, int)
   */
  public static int indexOfConsonant(final String s, final int fromIndex) {
    return indexOfConsonant(s, fromIndex, s.length());
  }

  /**
   * Returns the index of the first consonant in the string {@code s} in the range from the index
   * {@code fromIndex}, inclusive, to the index {@code toIndex}, inclusive. Returns <tt>-1</tt> if
   * no consonant is found.
   *
   * @param s the string
   * @param fromIndex the starting index to check
   * @param toIndex the final index to check
   * @return the index of the first consonant in the string {@code s} in the range from the index
   *         {@code fromIndex}, inclusive, to the index {@code toIndex}, inclusive. Returns
   *         <tt>-1</tt> if no consonant is found.
   * @throws IndexOutOfBoundsException if {@code fromIndex < 0} or {@code toIndex > a.length}
   * @throws IllegalArgumentException if {@code fromIndex > toIndex}
   */
  public static int indexOfConsonant(final String s, final int fromIndex, final int toIndex) {
    if (fromIndex < 0 || toIndex > s.length()) {
      throw new IndexOutOfBoundsException();
    }
    if (fromIndex > toIndex) {
      throw new IllegalArgumentException("fromIndex must be not greater than toIndex");
    }
    for (int i = fromIndex; i < toIndex; i++) {
      if (JChars.isConsonant(s.charAt(i))) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns <tt>true</tt> if the string {@code s} is numeric, i.e. composed by just ISO Latin 1
   * digits, otherwise returns <tt>false</tt>.
   * <p>
   * Executes the regular expression {@link JStrings#ISO_LATIN_1_DIGITS_REGEX} on the input string
   * {@code s}.
   *
   * @param s the string to check
   * @return <tt>true</tt> if the input string is numeric
   */
  @Beta
  public static boolean isISOLatinDigits(@Nonnull final String s) {
    return s.matches(ISO_LATIN_1_DIGITS_REGEX) ? true : false;
  }

  public static boolean isDigits(@Nonnull final String s) {
    for (int i = 0; i < s.length(); i++) {
      final char c = s.charAt(i);
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns <tt>true</tt> if the string {@code s} is alphabetic, i.e. composed by just letters,
   * otherwise returns <tt>false</tt>.
   * <p>
   * Does not use regular expressions.
   * 
   * @param s the string to check
   * @return <tt>true</tt> if the string {@code s} is alphabetic
   * @see Character#isLetter(char)
   */
  public static boolean isAlphabetic(@Nonnull final String s) {
    for (final char c : s.toCharArray()) {
      if (!Character.isLetter(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns <tt>true</tt> if the string {@code s} contains only digits and letters, <tt>false</tt>
   * otherwise.
   * <p>
   * Does not use regular expressions.
   *
   * @param s the string
   * @return <tt>true</tt> if the string {@code s} contains only digits and letters, <tt>false</tt>
   *         otherwise
   */
  public static boolean isAlphanumeric(@Nonnull final String s) {
    for (int i = 0; i < s.length(); i++) {
      final char c = s.charAt(i);
      if (!Character.isDigit(c) && !Character.isLetter(c)) {
        return false;
      }
      // if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a) {
      // return false;
      // }
    }
    return true;
  }

  /**
   * Returns <tt>true</tt> if the specified string {@code s} is composed only of characters from the
   * <a href="https://en.wikipedia.org/wiki/ISO_basic_Latin_alphabet"> ISO basic Latin alphabet</a>
   * i.e. the same characters of the English alphabet.
   * 
   * @param s the string to check
   * @return <tt>true</tt> if the specified string is composed only of characters from the ISO basic
   *         Latin alphabet
   */
  public static boolean isISOBasicLatinAlphabet(@Nonnull final String s) {
    return s.matches(ISO_BASIC_LATIN_ALPHABET_REGEX);
  }

  @Nonnull
  public static String replaceNotAlphanumeric(@Nonnull final String s,
      @Nonnull final String replacement) {
    return s.replaceAll(NOT_ISO_LATIN_ALPHABET_OR_DIGITS_REGEX, replacement);
  }

  /**
   * Returns the number of occurrences of the character {@code c} in the string {@code s}.
   *
   * @param s the string to check
   * @param c the character to count
   * @return the number of occurrences of the character {@code c} in the string {@code s}.
   */
  public static int occurrences(@Nonnull final String s, final char c) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == c) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns the number of occurrences of the characters {@code chars} in the string {@code ss}.
   *
   * @param s the string to check
   * @param chars the characters to count
   * @return an array of {@code int} where array[i] = occurrences of the character {@code chars[i]}
   */
  public static int[] occurrences(@Nonnull final String s, final char[] chars) {
    final int[] count = new int[chars.length];
    Arrays.fill(count, 0);
    for (int i = 0; i < s.length(); i++) {
      for (int j = 0; j < chars.length; j++) {
        if (s.charAt(i) == chars[j]) {
          count[j]++;
        }
      }
    }
    return count;
  }

  /**
   * Returns a new string from the given string {@code s}, eventually filled up at the beginning
   * with the character {@code c} in order to reach the requested dimension {@code size}.
   *
   * @param s a string
   * @param size an <tt>int</tt> representing the size to reach
   * @param c a <tt>char</tt> to use for fill the string to return
   * @return a new string from the given string {@code s} eventually left filled up with the
   *         character {@code c} in order to reach the requested dimension {@code size}
   * @throws IllegalArgumentException if the given {@code size} is lesser than the string length
   */
  @Nonnull
  public static String padStart(@Nonnull final String s, final int size, final char c) {
    if (size < s.length()) {
      throw new IllegalArgumentException(
          "The specified size must be at least equals to the string length");
    } else if (size == s.length()) {
      return s;
    } else {
      final char[] pad = new char[size];
      final int limit = size - s.length();
      for (int i = 0; i < size; i++) {
        pad[i] = i < limit ? c : s.charAt(i - limit);
      }
      return new String(pad);
    }
  }

  /**
   * Returns a new string from the given string {@code s}, eventually filled up at the end with the
   * character {@code c} in order to reach the requested dimension {@code size}.
   *
   * @param s a string
   * @param size an <tt>int</tt> representing the size to reach
   * @param c a <tt>char</tt> to use for fill the string to return
   * @return a new string from the given string {@code s}, eventually filled up at the end with the
   *         character {@code c} in order to reach the requested dimension {@code size}
   * @throws IllegalArgumentException if the given {@code size} is lesser than the string length
   */
  @Nonnull
  public static String padEnd(@Nonnull final String s, final int size, final char c) {
    if (size < s.length()) {
      throw new IllegalArgumentException(
          "The specified size must be at least equals to the string length");
    } else if (size == s.length()) {
      return s;
    } else {
      final char[] pad = new char[size];
      for (int i = 0; i < size; i++) {
        pad[i] = i < s.length() ? s.charAt(i) : c;
      }
      return new String(pad);
    }
  }
}
