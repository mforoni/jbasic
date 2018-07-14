package com.github.mforoni.jbasic;

import java.lang.reflect.Array;
import java.util.Collection;
import javax.annotation.Nonnull;
import com.google.common.annotations.Beta;

/**
 * Provides {@code static} utility methods for manipulating {@link Collection} objects.
 * 
 * @author Foroni Marco
 * @see Collection
 */
@Beta
public final class JCollections {
  // Suppresses default constructor, ensuring non-instantiability.
  private JCollections() {
    throw new AssertionError();
  }

  /**
   * Convert the specified collection to a new array.
   * 
   * @param collection a {@link Collection}
   * @param type the generic type
   * @return a new array having the same elements of the specified collection
   */
  @Beta
  @Nonnull
  public static <T> T[] toArray(@Nonnull final Collection<T> collection,
      @Nonnull final Class<T> type) {
    // Use Array native method to create array of a type only known at run time
    @SuppressWarnings("unchecked")
    final T[] array = (T[]) Array.newInstance(type, collection.size());
    return collection.toArray(array);
  }
}
