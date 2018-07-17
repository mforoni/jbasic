package com.github.mforoni.jbasic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;

/**
 * Provides {@code static} utility methods for creating and searching {@link List} objects.
 * 
 * @author Foroni Marco
 * @see List
 */
public final class JLists {
  // Suppresses default constructor, ensuring non-instantiability.
  private JLists() {
    throw new AssertionError();
  }

  /**
   * Returns <tt>true</tt> if the specified {@link List} contains a value that is equals ignoring
   * case to {@code key}, <tt>false</tt> otherwise.
   * 
   * @param list a {@link List} of strings
   * @param key a strings
   * @return a boolean primitive type
   */
  public static boolean containsIgnoreCase(@Nonnull final List<String> list,
      @Nonnull final String key) {
    return indexOfIgnoreCase(list, key, 0) != -1;
  }

  /**
   * Returns the index of the first case-insensitive occurrence of the string {@code key} in the
   * specified {@link List}. Returns {@code -1} if no match is found.
   * 
   * @param list a {@link List} of strings
   * @param key the string to search for
   * @return the index of the first case-insensitive occurrence of the specified element in this
   *         list, or {@code -1} if no match is found
   */
  public static int indexOfIgnoreCase(@Nonnull final List<String> list, @Nonnull final String key) {
    return indexOfIgnoreCase(list, key, 0);
  }

  /**
   * Returns the index of the first case-insensitive occurrence of the string {@code key} in the
   * specified {@link List} starting from the given index {@code fromIndex}. Returns {@code -1} if
   * no match is found.
   * 
   * @param list a {@link List} of strings
   * @param key the string to search for
   * @param fromIndex the starting index
   * @return the index of the first case-insensitive occurrence of the specified element in this
   *         list starting from the given index, or {@code -1} if no match is found.
   */
  public static int indexOfIgnoreCase(@Nonnull final List<String> list, @Nonnull final String key,
      final int fromIndex) {
    Preconditions.checkNotNull(list);
    Preconditions.checkNotNull(key);
    for (int i = fromIndex; i < list.size(); i++) {
      if (key.equalsIgnoreCase(list.get(i))) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns a new sorted {@link ArrayList} from the {@link Collection} obtained applying the
   * specified function to the given collection.
   * 
   * @param collection a {@link Collection}
   * @param function a {@link Function}
   * @return a new sorted {@link ArrayList} from the {@link Collection} obtained applying the
   *         specified function to the given collection.
   * @see ArrayList
   * @see Collections2#transform(Collection, Function)
   * @see Collections#sort(List, Comparator)
   */
  public static <F, T extends Comparable<? super T>> List<T> newSortedArrayList(
      final Collection<F> collection, final Function<? super F, ? extends T> function) {
    final List<T> transformed = new ArrayList<>(Collections2.transform(collection, function));
    Collections.sort(transformed);
    return transformed;
  }

  /**
   * Returns a new sorted {@link ArrayList} from the given {@link Collection}.
   * 
   * @param collection a {@link Collection}
   * @return a new sorted {@link ArrayList} from the given {@link Collection}
   * @see ArrayList
   * @see Collections#sort(List)
   */
  public static <T extends Comparable<? super T>> List<T> newSortedArrayList(
      final Collection<T> collection) {
    final List<T> list = new ArrayList<>(collection);
    Collections.sort(list);
    return list;
  }

  /**
   * Returns a new sorted {@link ArrayList} from the given {@link Collection}. The sort operation is
   * performed using the specified {@link Comparator}.
   * 
   * @param collection a {@link Collection}
   * @param comparator a {@link Comparator}
   * @return a new sorted {@link ArrayList} from the given {@link Collection}.
   * @see ArrayList
   * @see Collections#sort(List, Comparator)
   */
  public static <T> List<T> newSortedArrayList(final Collection<T> collection,
      final Comparator<? super T> comparator) {
    final List<T> list = new ArrayList<>(collection);
    Collections.sort(list, comparator);
    return list;
  }
}
