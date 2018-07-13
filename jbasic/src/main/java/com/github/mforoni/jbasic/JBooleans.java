package com.github.mforoni.jbasic;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Provides {@code static} utility methods for parsing strings into {@link Boolean} objects and the primitive boolean
 * type.
 * 
 * @author Foroni Marco
 */
@Beta
public final class JBooleans {

	public static final String TRUE = "TRUE";
	public static final String FALSE = "FALSE";
	public static final String NO = "NO";
	public static final String YES = "YES";

	// Suppresses default constructor, ensuring non-instantiability.
	private JBooleans() {
		throw new AssertionError();
	}

	/**
	 * Returns:
	 * <ul>
	 * <li><tt>null</tt> if the specified string parameter is <tt>null</tt>
	 * <li><tt>true</tt> if the specified string parameter is equals ignoring case to yes or true</li>
	 * <li><tt>false</tt> if the specified string parameter is equals ignoring case to no or false</li>
	 * <li>otherwise throws an {@code IllegalArgumentException}</li>
	 * </ul>
	 * 
	 * @param s
	 *            a string
	 * @return a boolean primitive type
	 * @throws IllegalArgumentException
	 */
	@Nullable
	public static Boolean parseNullable(@Nullable final String s) {
		return Strings.isNullOrEmpty(s) ? null : parse(s);
	}

	/**
	 * Returns:
	 * <ul>
	 * <li><tt>true</tt> if the specified string parameter is equals ignoring case to yes or true</li>
	 * <li><tt>false</tt> if the specified string parameter is equals ignoring case to no or false</li>
	 * <li>otherwise throws an {@code IllegalArgumentException}</li>
	 * </ul>
	 * 
	 * @param s
	 *            a string
	 * @return a boolean primitive type
	 * @throws IllegalArgumentException
	 * @see Preconditions#checkNotNull(Object)
	 */
	public static boolean parse(@Nonnull final String s) {
		Preconditions.checkNotNull(s);
		if (s.equalsIgnoreCase(YES) || s.equalsIgnoreCase(TRUE)) {
			return true;
		} else if (s.equalsIgnoreCase(NO) || s.equalsIgnoreCase(FALSE)) {
			return false;
		} else {
			throw new IllegalArgumentException("Cannot parse " + s + " to boolean");
		}
	}
}
