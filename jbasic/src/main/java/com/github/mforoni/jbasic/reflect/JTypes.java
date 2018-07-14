package com.github.mforoni.jbasic.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.joda.time.LocalDate;
import com.google.common.annotations.Beta;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;

/**
 * @author Foroni Marco
 */
@Beta
public class JTypes {
  private static final ImmutableMap<Class<?>, Class<?>> WRAPPER_BY_PRIMITIVE;
  private static final ImmutableMap<Class<?>, Class<?>> PRIMITIVE_BY_WRAPPER;
  static {
    Map<Class<?>, Class<?>> map = new HashMap<>();
    map.put(boolean.class, Boolean.class);
    map.put(byte.class, Byte.class);
    map.put(short.class, Short.class);
    map.put(char.class, Character.class);
    map.put(int.class, Integer.class);
    map.put(long.class, Long.class);
    map.put(float.class, Float.class);
    map.put(double.class, Double.class);
    WRAPPER_BY_PRIMITIVE = ImmutableMap.copyOf(map);
    map = new HashMap<>();
    map.put(Boolean.class, boolean.class);
    map.put(Byte.class, byte.class);
    map.put(Short.class, short.class);
    map.put(Character.class, char.class);
    map.put(Integer.class, int.class);
    map.put(Long.class, long.class);
    map.put(Float.class, float.class);
    map.put(Double.class, double.class);
    PRIMITIVE_BY_WRAPPER = ImmutableMap.copyOf(map);
  }

  private JTypes() {
    throw new AssertionError();
  }

  /**
   * Returns a new instance of the specified {@code type} calling a no arguments constructor.
   * 
   * @param type a {@code Class} object
   * @return a new instance of the specified {@code type} calling a no arguments constructor
   * @throws IllegalStateException
   * @see Class
   */
  public static <T> T newInstance(final Class<T> type) throws IllegalStateException {
    try {
      final Constructor<T> constructor = noArgsConstructor(type);
      if (constructor != null) {
        return constructor.newInstance();
      } else {
        throw new IllegalStateException("Cannot find a no args construnctor for the type " + type);
      }
    } catch (final InvocationTargetException | IllegalAccessException | InstantiationException ex) {
      throw new IllegalStateException(ex);
    }
  }

  @Nullable
  public static <T> Constructor<T> noArgsConstructor(final Class<T> type)
      throws IllegalStateException {
    try {
      return type.getConstructor();
    } catch (final NoSuchMethodException e) {
      return null;
    } catch (final SecurityException e) {
      throw new IllegalStateException(e);
    }
  }

  @Nullable
  public static Class<?> toPrimitiveWrapper(final Class<?> type) { // or boxing
    return WRAPPER_BY_PRIMITIVE.get(type);
  }

  /**
   * Returns the corresponding primitive type to specified primitive type wrapper. Returns
   * <tt>null</tt> if the specified type is not a primitive type wrapper.
   * <p>
   * The eight primitive data types in Java are:
   * <ul>
   * <li><b>boolean</b>, the type whose values are either true or false
   * <li><b>char</b>, the character type whose values are 16-bit Unicode characters</li>
   * <li>the arithmetic types:
   * <ul>
   * <li>the integral types: <b>byte short int long</b>
   * <li>the floating-point types: <b>float double</b>
   * </ul>
   * </ul>
   * 
   * @param type
   * @return the corresponding primitive type to specified primitive type wrapper. Returns
   *         <tt>null</tt> if the specified type is not a primitive type wrapper.
   * @see Class
   */
  @Nullable
  public static Class<?> toPrimitive(final Class<?> type) { // or unboxing
    return PRIMITIVE_BY_WRAPPER.get(type);
  }

  public static boolean isPrimitiveOrPrimitiveWrapper(final Class<?> type) {
    return (type.isPrimitive() && type != void.class) || type == Double.class || type == Float.class
        || type == Long.class || type == Integer.class || type == Short.class
        || type == Character.class || type == Byte.class || type == Boolean.class;
  }

  public static boolean isBoolean(final Class<?> type) {
    return type.equals(Boolean.class) || (type.isPrimitive() && type.equals(Boolean.TYPE));
  }

  public static boolean isDateOrLocalDate(final Class<?> type) {
    return type.equals(Date.class) || type.equals(LocalDate.class);
  }

  public static final Predicate<Class<?>> NOT_JAVA_LANG_TYPE = new Predicate<Class<?>>() {
    @Override
    public boolean apply(@Nullable final Class<?> input) {
      return input != null && !input.getName().startsWith("java.lang");
    }
  };
}
