package com.github.mforoni.jbasic;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;

/**
 * Provides {@code static} utility methods for manipulating arrays. Operations like arrays
 * concatenation and searching are offered through the methods of this class.
 *
 * @author Foroni Marco
 */
public final class JArrays {
  // Suppresses default constructor, ensuring non-instantiability.
  private JArrays() {
    throw new AssertionError();
  }

  /**
   * Returns <tt>true</tt> if the specified object is an instance of an array class, <tt>false</tt>
   * otherwise.
   * 
   * @param obj the object to check
   * @return <tt>true</tt> if the specified object is an instance of an array class, <tt>false</tt>
   *         otherwise.
   * @see Class#isArray()
   */
  public static boolean isArray(@Nonnull final Object obj) {
    return obj.getClass().isArray();
  }

  /**
   * This method concatenates the three given array of the same generic type.
   * 
   * @param first the first array of generic type elements (cannot be <tt>null</tt>)
   * @param second the second array of generic type elements (cannot be <tt>null</tt>)
   * @param third the third array of generic type elements (cannot be <tt>null</tt>)
   * @param type the type of the array elements
   * @return a new array of generic type containing all the elements of {@code first} and
   *         subsequently all the elements of {@code second} and {@code third}
   * @see ObjectArrays#concat(Object[], Object[], Class)
   */
  @Nonnull
  public static <T> T[] concat(@Nonnull final T[] first, @Nonnull final T[] second,
      @Nonnull final T[] third, @Nonnull final Class<T> type) {
    Preconditions.checkNotNull(first);
    Preconditions.checkNotNull(second);
    Preconditions.checkNotNull(third);
    Preconditions.checkNotNull(type);
    final T[] concat = ObjectArrays.concat(second, third, type);
    return ObjectArrays.concat(first, concat, type);
  }

  /**
   * Returns <tt>true</tt> if {@code array} contains {@code key}, <tt>false</tt> otherwise.
   *
   * @param array an array of string
   * @param key a string
   * @return <tt>true</tt> iff the array contains the element {@code key}
   */
  public static boolean containsIgnoreCase(@Nonnull final String[] array,
      @Nullable final String key) {
    return indexOfIgnoreCase(array, key) != -1;
  }

  /**
   * Returns the index of the string {@code key} in the given {@code array}, if {@code key} never
   * occurs returns <tt>-1</tt>.
   *
   * @param array an array of string
   * @param key a string
   * @return the index of the string {@code key} in the given {@code array}, if {@code key} never
   *         occurs returns <tt>-1</tt>.
   */
  public static int indexOfIgnoreCase(@Nonnull final String[] array, @Nullable final String key) {
    return indexOfIgnoreCase(array, key, 0);
  }

  /**
   * Returns the index of the string {@code key} in the given {@code array} if {@code key} occurs in
   * a position not lesser than {@code fromIndex}, otherwise returns <tt>-1</tt>.
   *
   * @param array an array of string
   * @param key a string
   * @param fromIndex a starting index
   * @return the index of the string {@code key} in the given {@code array} if {@code key} occurs in
   *         a position not lesser than {@code fromIndex}
   */
  public static int indexOfIgnoreCase(@Nonnull final String[] array, @Nullable final String key,
      final int fromIndex) {
    for (int i = fromIndex; i < array.length; i++) {
      if (Objects.equals(array[i], key)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the number of occurrences of the given {@code key} in the specified {@code array}.
   * 
   * @param array the array to search
   * @param key the key to count the occurrences
   * @return the number of occurrences of the given {@code key} in the specified {@code array}
   */
  public static <T> int occurrences(@Nonnull final T[] array, @Nullable final T key) {
    int counter = 0;
    for (final T obj : array) {
      if (Objects.equals(obj, key)) {
        counter += 1;
      }
    }
    return counter;
  }

  /**
   * Returns the number of occurrences of the given {@code key} in the specified {@code array} of
   * {@code Number} objects.
   * 
   * @param array the array to search
   * @param key the key to count the occurrences
   * @return the number of occurrences of the given {@code key} in the specified {@code array}
   */
  public static <T extends Number, U extends Number> int occurrences(@Nonnull final T[] array,
      @Nullable final U key) {
    int counter = 0;
    for (final T obj : array) {
      if (JNumbers.equals(obj, key)) {
        counter += 1;
      }
    }
    return counter;
  }

  /**
   * Returns <tt>true</tt> if the given {@code array} contains the specified {@code key},
   * <tt>false</tt> otherwise. The {@code key} is searched performing
   * {@link Objects#equals(Object, Object)}.
   *
   * @param array an array of generic type {@code T}
   * @param key an object of type {@code T}
   * @return <tt>true</tt> if the given {@code array} contains the specified {@code key}
   * @see Objects#equals(Object, Object)
   */
  public static <T> boolean contains(@Nonnull final T[] array, @Nullable final T key) {
    return indexOf(array, key) != -1;
  }

  /**
   * Returns the index of the specified {@code key} in the given generic array, if {@code key} never
   * occurs returns {@code -1}. The {@code key} is searched performing
   * {@link Objects#equals(Object, Object)}.
   *
   * @param array an array of generic type {@code T}
   * @param key an object of type {@code T}
   * @return the index of the {@code key} in the given generic array
   * @see Objects#equals(Object, Object)
   */
  public static <T> int indexOf(@Nonnull final T[] array, @Nullable final T key) {
    return indexOf(array, key, 0);
  }

  /**
   * Returns the index of the specified {@code key} in the given generic array, if {@code key}
   * occurs in a position not lesser than {@code fromIndex}, otherwise returns {@code -1}. The
   * {@code key} is searched performing {@link Objects#equals(Object, Object)}.
   *
   * @param array an array of generic type {@code T}
   * @param key an object of type {@code T}
   * @param fromIndex a starting index
   * @return the index of {@code key} in the given generic {@code array}
   * @see Objects#equals(Object, Object)
   */
  public static <T> int indexOf(@Nonnull final T[] array, @Nullable final T key,
      final int fromIndex) {
    for (int i = fromIndex; i < array.length; i++) {
      if (Objects.equals(key, array[i])) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns <tt>true</tt> if the given {@code array} of {@code Number} objects contains the
   * specified number {@code key}, otherwise returns <tt>false</tt>. The {@code key} is searched
   * performing {@link JNumbers#equals(Number, Number)}.
   *
   * @param array an array of {@code Number} objects
   * @param key a {@code Number}
   * @return <tt>true</tt> if the given {@code array} contains the specified number {@code key}
   * @see JNumbers#equals(Number, Number)
   */
  public static <T extends Number, U extends Number> boolean contains(@Nonnull final T[] array,
      @Nullable final U key) {
    return indexOf(array, key, 0) != -1;
  }

  /**
   * Returns the index of the specified {@code key} in the given array of {@code Number objects}, if
   * {@code key} never occurs returns {@code -1}. The {@code key} is searched performing
   * {@link JNumbers#equals(Number, Number)}.
   * 
   * @param array an array of {@code Number} objects
   * @param key a {@code Number}
   * @return the index of the specified {@code key} in the given array
   * @see JNumbers#equals(Number, Number)
   */
  public static <T extends Number, U extends Number> int indexOf(@Nonnull final T[] array,
      @Nullable final U key) {
    return indexOf(array, key, 0);
  }

  /**
   * Returns the index of the specified {@code key} in the given array of {@code Number} objects, if
   * {@code key} occurs in a position not lesser than {@code fromIndex}, otherwise returns
   * {@code -1}. The {@code key} is searched performing {@link JNumbers#equals(Number, Number)}.
   *
   * @param array an array of {@code Number} objects
   * @param key a {@code Number}
   * @param fromIndex a starting index
   * @return the index of the specified {@code key} in the given array
   * @see JNumbers#equals(Number, Number)
   */
  public static <T extends Number, U extends Number> int indexOf(@Nonnull final T[] array,
      @Nullable final U key, final int fromIndex) {
    for (int i = fromIndex; i < array.length; i++) {
      if (JNumbers.compare(key, array[i]) == 0) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns a string containing each {@code toString()} value of the array's elements using the
   * character {@code ','} as separator and the string {@code "<null>"} for eventually <tt>null</tt>
   * values.
   * 
   * @param array an array of generic type {@code T}
   * @return a string
   * @see Joiner
   */
  public static <T> String toString(@Nonnull final T[] array) {
    return Joiner.on(',').useForNull("<null>").join(array);
  }

  @Beta
  public static List<?> primitiveArrayToList(final Object array) {
    if (isArray(array)) {
      if (array instanceof boolean[]) {
        return Booleans.asList((boolean[]) array);
      } else if (array instanceof byte[]) {
        return Bytes.asList((byte[]) array);
      } else if (array instanceof short[]) {
        return Shorts.asList((short[]) array);
      } else if (array instanceof char[]) {
        return Chars.asList((char[]) array);
      } else if (array instanceof int[]) {
        return Ints.asList((int[]) array);
      } else if (array instanceof long[]) {
        return Longs.asList((long[]) array);
      } else if (array instanceof float[]) {
        return Floats.asList((float[]) array);
      } else if (array instanceof double[]) {
        return Doubles.asList((double[]) array);
      } else {
        throw new IllegalStateException();
      }
    } else {
      throw new IllegalArgumentException("obj must be an array of primitive type");
    }
  }
}
