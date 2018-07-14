package com.github.mforoni.jbasic;

import javax.annotation.Nullable;
import com.google.common.base.Function;
import com.google.common.primitives.Ints;

/**
 * Provides {@code static} utility members for converting objects between different types.
 * 
 * @author Foroni Marco
 * @see Function
 */
public final class JFunctions {
  // Suppresses default constructor, ensuring non-instantiability.
  private JFunctions() {
    throw new AssertionError();
  }

  /**
   * Converts an {@code Object} to a {@code String} performing {@link String#valueOf(Object)}.
   * 
   * @see Function
   */
  public static final Function<Object, String> OBJECT_TO_STRING = new Function<Object, String>() {
    @Nullable
    @Override
    public String apply(@Nullable final Object input) {
      return input != null ? String.valueOf(input) : null;
    }
  };
  /**
   * Converts a {@code String} to an {@code Integer} performing {@link Ints#tryParse(String)}.
   * 
   * @see Function
   */
  public static final Function<String, Integer> STRING_TO_INTEGER =
      new Function<String, Integer>() {
        @Nullable
        @Override
        public Integer apply(@Nullable final String input) {
          return input != null ? Ints.tryParse(input) : null;
        }
      };
  /**
   * Converts a {@code String} to a {@code Boolean} performing {@link Boolean#parseBoolean(String)}.
   * 
   * @see Function
   */
  public static final Function<String, Boolean> STRING_TO_BOOLEAN =
      new Function<String, Boolean>() {
        @Nullable
        @Override
        public Boolean apply(@Nullable final String input) {
          return input != null ? Boolean.parseBoolean(input) : null;
        }
      };
}
