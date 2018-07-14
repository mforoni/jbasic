package com.github.mforoni.jbasic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.github.mforoni.jbasic.JStrings;
import com.google.common.annotations.Beta;
import com.google.common.base.Optional;

/**
 * Provides {@code static} utility methods for retrieving information about, and access to, a single
 * method on a class or interface.
 * 
 * @author Foroni Marco
 * @see Method
 */
@Beta
public class JMethods {
  private JMethods() {
    throw new AssertionError();
  }

  /**
   * Returns the getter method name for the field having name {@code fieldName} declared in the
   * specified {@code Class} {@code type}.
   * 
   * @param type the {@code Class} containing the field
   * @param fieldName the name of the field
   * @return a {@code String} containing the getter method name
   */
  @Nonnull
  public static String getterName(@Nonnull final Class<?> type, @Nonnull final String fieldName) {
    final Field field = JFields.nonnull(type, fieldName);
    final Class<?> fieldType = field.getType();
    return JMethods.getterName(fieldName, fieldType);
  }

  @Nonnull
  public static String getterName(@Nonnull final String fieldName,
      @Nonnull final Class<?> fieldType) {
    final String start = JTypes.isBoolean(fieldType) ? "is" : "get";
    return start.concat(JStrings.capitalize(fieldName));
  }

  @Nonnull
  public static String setterName(@Nonnull final String fieldName) {
    return "set".concat(JStrings.capitalize(fieldName));
  }

  public static Optional<Method> optional(@Nonnull final Class<?> type,
      @Nonnull final String name) {
    return Optional.fromNullable(nullable(type, name));
  }

  @Nonnull
  public static Method nonnull(@Nonnull final Class<?> type, @Nonnull final String name)
      throws IllegalArgumentException {
    final Method method = nullable(type, name);
    if (method == null) {
      throw new IllegalArgumentException(
          "Cannot find method having name " + name + " in type " + type.getSimpleName());
    }
    return method;
  }

  @Nullable
  public static Method nullable(@Nonnull final Class<?> type, @Nonnull final String name) {
    Class<?> superType = type;
    Method m = null;
    do {
      final Method[] methods = superType.getDeclaredMethods();
      for (final Method method : methods) {
        if (method.getName().equals(name)) {
          m = method;
          break;
        }
      }
      superType = superType.getSuperclass();
    } while (superType != null && m == null);
    return m;
  }

  @Nonnull
  public static Method setter(final Class<?> type, final String fieldName)
      throws NoSuchMethodException, SecurityException {
    return type.getMethod(setterName(fieldName), JFields.nullable(type, fieldName).getType());
  }

  public static Optional<Method> optionalSetter(final Class<?> type, final String fieldName) {
    try {
      return Optional.of(setter(type, fieldName));
    } catch (NoSuchMethodException | SecurityException e) {
      return Optional.absent();
    }
  }

  @Nonnull
  public static Method getter(final Class<?> type, final String fieldName)
      throws NoSuchMethodException, SecurityException {
    return type.getMethod(getterName(type, fieldName));
  }

  @Nonnull
  public static Method nonnullGetter(final Class<?> type, final String fieldName)
      throws IllegalStateException {
    try {
      return getter(type, fieldName);
    } catch (NoSuchMethodException | SecurityException e) {
      throw new IllegalStateException(e);
    }
  }

  public static Optional<Method> optionalGetter(@Nonnull final Class<?> type,
      @Nonnull final String fieldName) {
    try {
      return Optional.of(getter(type, fieldName));
    } catch (NoSuchMethodException | SecurityException e) {
      return Optional.absent();
    }
  }

  public static <T> void invokeSetter(final Class<T> type, final String fieldName, final T instance,
      final Object value) throws IllegalStateException {
    final Optional<Method> method = optionalSetter(type, fieldName);
    if (method.isPresent()) {
      try {
        method.get().invoke(instance, value);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
          | SecurityException e) {
        throw new IllegalStateException(String.format(
            "Cannot invoke setter for field %s of type %s", fieldName, type.getSimpleName()), e);
      }
    } else {
      throw new IllegalStateException(String.format("Cannot find setter for field %s of type %s",
          fieldName, type.getSimpleName()));
    }
  }

  public static void invokeSetter(@Nonnull final Method setter, @Nonnull final Object instance,
      @Nonnull final Object value) throws IllegalStateException, IllegalArgumentException {
    try {
      setter.invoke(instance, value);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }

  @Nullable
  public static <T> Object invokeGetter(@Nonnull final Class<T> type,
      @Nonnull final String fieldName, @Nonnull final T instance) throws IllegalStateException {
    final Optional<Method> method = optionalGetter(type, fieldName);
    if (method.isPresent()) {
      try {
        return method.get().invoke(instance);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        throw new IllegalStateException(String.format(
            "Cannot invoke getter for field %s of type %s", fieldName, type.getSimpleName()), e);
      }
    } else {
      throw new IllegalStateException(String.format("Cannot find getter for field %s of type %s",
          fieldName, type.getSimpleName()));
    }
  }

  @Nullable
  public static Object invokeGetter(@Nonnull final Method getter, @Nonnull final Object instance)
      throws IllegalStateException, IllegalArgumentException {
    try {
      return getter.invoke(instance);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }
}
