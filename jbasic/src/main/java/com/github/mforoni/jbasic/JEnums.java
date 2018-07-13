package com.github.mforoni.jbasic;

import java.lang.reflect.Array;
import javax.annotation.Nonnull;
import com.google.common.annotations.Beta;
import com.google.common.base.Optional;

/**
 * Provides {@code static} utility methods for dealing with {@code Enum}.
 * 
 * @author Foroni Marco
 * @see Enum
 */
public final class JEnums {

	// Suppresses default constructor, ensuring non-instantiability.
	private JEnums() {
		throw new AssertionError();
	}

	@Beta
	@Nonnull
	public static <E extends Enum<E>> E[] newArray(@Nonnull final Class<E> enumType, @Nonnull final E first, @Nonnull final E second,
			final E... others) {
		final int length = 2 + others.length;
		// Use Array native method to create array of a type only known at run time
		@SuppressWarnings("unchecked")
		final E[] array = (E[]) Array.newInstance(enumType, length);
		array[0] = first;
		array[1] = second;
		System.arraycopy(others, 0, array, 2, others.length);
		return array;
	}

	/**
	 * Returns an array of strings containing all {@code Enum} constants' name property of the given {@code Enum} type.
	 * 
	 * @param enumClass
	 *            an {@code Enum} type
	 * @return an array of strings containing all {@code Enum} constants' name property of the given {@code Enum} type
	 * @see Enum
	 */
	@Nonnull
	public static <E extends Enum<E>> String[] names(@Nonnull final Class<E> enumClass) {
		final E[] array = enumClass.getEnumConstants();
		final String[] names = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			names[i] = array[i].name();
		}
		return names;
	}

	/**
	 * Returns an {@link Optional} objects containing the {@code Enum} constant of the specified {@code Enum} type
	 * having name equals to the given strings {@code constantName}.
	 * 
	 * @param enumClass
	 *            an {@code Enum} type
	 * @param constantName
	 *            the name of the {@code Enum} constant
	 * @return an {@link Optional} objects containing the {@code Enum} constant of the specified {@code Enum} type
	 *         having name equals to the given strings {@code constantName}
	 * @see Optional
	 */
	public static <E extends Enum<E>> Optional<E> optionalValueOf(@Nonnull final Class<E> enumClass, @Nonnull final String constantName) {
		try {
			return Optional.of(Enum.valueOf(enumClass, constantName));
		} catch (final IllegalArgumentException e) {
			return Optional.absent();
		}
	}

	/**
	 * Returns the {@code Enum} constant of the specified {@code Enum} type having name equals to the given strings
	 * {@code name} converted to upper case and whose occurrences of spaces are replaced with the underscore character.
	 * 
	 * @param enumClass
	 *            an {@code Enum} type
	 * @param name
	 * @return the {@code Enum} constant of the specified {@code Enum} type having name equals to the given strings
	 *         {@code name} converted to upper case and whose occurrences of spaces are replaced with the underscore
	 *         character
	 */
	@Beta
	@Nonnull
	public static <E extends Enum<E>> E conventionalValueOf(@Nonnull final Class<E> enumClass, @Nonnull final String name) {
		return Enum.valueOf(enumClass, name.toUpperCase().replaceAll(" ", "_"));
	}
}
