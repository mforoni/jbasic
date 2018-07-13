package com.github.mforoni.jbasic;

import javax.annotation.Nonnull;
import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;

/**
 * Provides {@code static} utility methods for conveniently creating {@link Exception} objects with formatted
 * text messages.
 * 
 * @author Foroni Marco
 * @see Exception
 */
public final class JExceptions {

	// Suppresses default constructor, ensuring non-instantiability.
	private JExceptions() {
		throw new AssertionError();
	}

	/**
	 * Returns a new {@code IllegalArgumentException} with the given string message and arguments conveniently
	 * formatted.
	 * 
	 * @param formatMsg
	 *            the detailed message to be formatted
	 * @param firstArg
	 *            the first argument
	 * @param otherArgs
	 *            the other arguments
	 * @return a new {@code IllegalArgumentException} with the given string message and arguments conveniently
	 *         formatted
	 * @see IllegalArgumentException
	 * @see String#format(String, Object...)
	 */
	@Nonnull
	public static IllegalArgumentException newIllegalArgument(@Nonnull final String formatMsg, @Nonnull final Object firstArg,
			final Object... otherArgs) {
		Preconditions.checkNotNull(formatMsg);
		Preconditions.checkNotNull(firstArg);
		return new IllegalArgumentException(String.format(formatMsg, ObjectArrays.concat(firstArg, otherArgs)));
	}

	/**
	 * Returns a new {@code IllegalStateException} with the given string message and arguments conveniently
	 * formatted.
	 * 
	 * @param formatMsg
	 *            the detailed message to be formatted
	 * @param firstArg
	 *            the first argument
	 * @param otherArgs
	 *            the other arguments
	 * @return a new {@code IllegalStateException} with the given string message and arguments conveniently
	 *         formatted
	 * @see IllegalStateException
	 * @see String#format(String, Object...)
	 */
	@Nonnull
	public static IllegalStateException newIllegalState(@Nonnull final String formatMsg, @Nonnull final Object firstArg, final Object... otherArgs) {
		Preconditions.checkNotNull(formatMsg);
		Preconditions.checkNotNull(firstArg);
		return new IllegalStateException(String.format(formatMsg, ObjectArrays.concat(firstArg, otherArgs)));
	}

	/**
	 * Returns a string containing the stack trace of the specified {@code Throwable}.
	 *
	 * @param t
	 *            the {@code Throwable} to convert
	 * @return the stack trace of the specified {@code Throwable} as a new string
	 */
	@Beta
	public static String toString(final Throwable t) {
		final StackTraceElement[] stackTraceElements = t.getStackTrace();
		final StringBuilder s = new StringBuilder();
		for (int i = 0; i < stackTraceElements.length; i++) {
			s.append(stackTraceElements[i].toString());
			if (i != stackTraceElements.length - 1) {
				s.append(JStrings.NEWLINE);
			}
		}
		return s.toString();
	}
}
