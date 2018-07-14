package com.github.mforoni.jbasic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.github.mforoni.jbasic.JExceptions;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;

/**
 * Provides {@code static} utility methods for retrieving information about, and dynamic access to,
 * one or more fields of a class.
 * 
 * @author Foroni Marco
 * @see Field
 */
public final class JFields {
  // Suppresses default constructor, ensuring non-instantiability.
  private JFields() {
    throw new AssertionError();
  }

  /**
   * Transform a {@code Field} in a string containing the field's name.
   * 
   * @see Field
   */
  public static final Function<Field, String> GET_NAME = new Function<Field, String>() {
    @Nullable
    @Override
    public String apply(@Nullable final Field input) {
      return input == null ? null : input.getName();
    }
  };
  /**
   * {@code Predicate} to establish if a {@code Field} is {@code final}.
   * 
   * @see Field
   * @see Predicate
   */
  public static final Predicate<Field> IS_FINAL = new Predicate<Field>() {
    @Override
    public boolean apply(@Nonnull final Field input) {
      return isFinal(input);
    }
  };
  /**
   * {@code Predicate} to establish if a {@code Field} is {@code public}.
   * 
   * @see Field
   * @see Predicate
   */
  public static final Predicate<Field> IS_PUBLIC = new Predicate<Field>() {
    @Override
    public boolean apply(@Nonnull final Field input) {
      return isPublic(input);
    }
  };
  /**
   * {@code Predicate} to establish if a {@code Field} is {@code static}.
   * 
   * @see Field
   * @see Predicate
   */
  public static final Predicate<Field> IS_STATIC = new Predicate<Field>() {
    @Override
    public boolean apply(@Nonnull final Field input) {
      return isStatic(input);
    }
  };
  /**
   * {@code Predicate} to establish if a {@code Field} is not {@code static}.
   * 
   * @see Field
   * @see Predicate
   */
  public static final Predicate<Field> IS_NOT_STATIC = new Predicate<Field>() {
    @Override
    public boolean apply(@Nonnull final Field input) {
      return !isStatic(input);
    }
  };
  /**
   * {@code Predicate} to establish if a {@code Field} is {@code private}.
   * 
   * @see Field
   * @see Predicate
   */
  public static final Predicate<Field> IS_PRIVATE = new Predicate<Field>() {
    @Override
    public boolean apply(@Nullable final Field input) {
      return isPrivate(input);
    }
  };

  /**
   * Returns <tt>true</tt> if the specified {@code Field} is {@code final}, otherwise
   * <tt>false</tt>.
   * 
   * @param field the field to check
   * @return <tt>true</tt> if the specified {@code Field} is {@code final}, otherwise <tt>false</tt>
   */
  public static boolean isFinal(@Nonnull final Field field) {
    return Modifier.isFinal(field.getModifiers());
  }

  /**
   * Returns <tt>true</tt> if the specified {@code Field} is {@code public}, otherwise
   * <tt>false</tt>.
   * 
   * @param field the field to check
   * @return <tt>true</tt> if the specified {@code Field} is {@code public}, otherwise
   *         <tt>false</tt>
   */
  public static boolean isPublic(@Nonnull final Field field) {
    return Modifier.isPublic(field.getModifiers());
  }

  /**
   * Returns <tt>true</tt> if the specified {@code Field} is {@code static}, otherwise
   * <tt>false</tt>.
   * 
   * @param field the field to check
   * @return <tt>true</tt> if the specified {@code Field} is {@code static}, otherwise
   *         <tt>false</tt>
   */
  public static boolean isStatic(@Nonnull final Field field) {
    return Modifier.isStatic(field.getModifiers());
  }

  /**
   * Returns <tt>true</tt> if the specified {@code Field} is {@code private}, otherwise
   * <tt>false</tt>.
   * 
   * @param field the field to check
   * @return <tt>true</tt> if the specified {@code Field} is {@code private}, otherwise
   *         <tt>false</tt>
   */
  public static boolean isPrivate(@Nonnull final Field field) {
    return Modifier.isPrivate(field.getModifiers());
  }

  /**
   * Returns the fields from the specified class {@code type} having name equals to the given names.
   * A non {@code private} field declared in a superclass of {@code type} is returned if the
   * previous requirements are satisfied.
   * 
   * @param type the {@code Class} to retrieve fields from
   * @param fieldName a field name
   * @param otherFieldNames other field names
   * @return a list of {@code Field} objects
   * @see Field
   */
  @Nonnull
  public static List<Field> fromType(@Nonnull final Class<?> type, final String fieldName,
      final String... otherFieldNames) {
    final ImmutableSet<String> names =
        ImmutableSet.<String>builder().add(fieldName).add(otherFieldNames).build();
    final Predicate<Field> matchNames = new Predicate<Field>() {
      @Override
      public boolean apply(@Nonnull final Field input) {
        return names.contains(input.getName());
      }
    };
    return fromType(type, matchNames);
  }

  /**
   * Returns the fields declared in the given type. All non {@code private} field declared in a
   * superclass of {@code type} is also returned.
   * 
   * @param type the {@code Class} to retrieve fields from
   * @return a list of {@code Field} objects
   * @see Field
   */
  @Nonnull
  public static List<Field> fromType(@Nonnull final Class<?> type) {
    return fromType(type, Optional.<Predicate<Field>>absent());
  }

  /**
   * Returns the fields declared in the given type that satisfies the specified predicate. A non
   * {@code private} field declared in a superclass of {@code type} is returned if the previous
   * requirements are satisfied. If no predicate is specified no filter is applied.
   * 
   * @param type the {@code Class} to retrieve fields from
   * @param predicate the predicate that the returned fields must satisfy (can be <tt>null</tt>)
   * @return a list of {@code Field} objects
   * @see Field
   * @see Predicate
   */
  @Nonnull
  public static List<Field> fromType(@Nonnull final Class<?> type,
      @Nullable final Predicate<Field> predicate) {
    return fromType(type, Optional.fromNullable(predicate));
  }

  @Nonnull
  private static List<Field> fromType(@Nonnull final Class<?> type,
      @Nonnull final Optional<Predicate<Field>> predicate) {
    final List<Field> fields = new ArrayList<>();
    Class<?> superType = type;
    while (superType != null) {
      final List<Field> superTypeFields = Arrays.asList(superType.getDeclaredFields());
      fields.addAll(predicate.isPresent() ? Collections2.filter(superTypeFields, predicate.get())
          : superTypeFields);
      superType = superType.getSuperclass();
    }
    return fields;
  }

  /**
   * Returns an {@code Optional} object containing the field of the specified {@code type} having
   * the given {@code name} when present. A non {@code private} field declared in a superclass of
   * {@code type} is returned if the previous requirements are satisfied.
   * 
   * @param type the {@code Class} to retrieve the field from
   * @param name the field name
   * @return an {@code Optional} object containing the field of the specified {@code type} having
   *         the given {@code name} when present
   * @see Optional
   * @see Field
   */
  public static Optional<Field> optional(@Nonnull final Class<?> type, @Nonnull final String name) {
    return Optional.fromNullable(nullable(type, name));
  }

  /**
   * Returns the field of the specified {@code type} having the given {@code name}. A non
   * {@code private} field declared in a superclass of {@code type} is returned if the previous
   * requirements are satisfied.
   * 
   * @param type the {@code Class} to retrieve the field from
   * @param name the field name
   * @return the field of the specified {@code type} having the given {@code name}
   * @throws IllegalArgumentException if no field having the specified name is found
   * @see Field
   */
  @Nonnull
  public static Field nonnull(@Nonnull final Class<?> type, @Nonnull final String name)
      throws IllegalArgumentException {
    final Field field = nullable(type, name);
    if (field == null) {
      throw JExceptions.newIllegalArgument("Cannot find field having name %s in type %s", name,
          type.getSimpleName());
    }
    return field;
  }

  /**
   * Returns the field of the specified {@code type} having the given {@code name}. A non
   * {@code private} field declared in a superclass of {@code type} is returned if the previous
   * requirements are satisfied.
   * 
   * @param type the {@code Class} to retrieve the field from
   * @param name the field name
   * @return the field of the specified {@code type} having the given {@code name}
   * @see Field
   */
  @Nullable
  public static Field nullable(@Nonnull final Class<?> type, @Nonnull final String name) {
    Class<?> superType = type;
    Field field = null;
    do {
      try {
        field = superType.getDeclaredField(name);
      } catch (final NoSuchFieldException ex) {
        // ignored
      }
      superType = superType.getSuperclass();
    } while (superType != null && field == null);
    return field;
  }
}
