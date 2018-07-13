package com.github.mforoni.jbasic.math;

import com.google.common.annotations.Beta;

/**
 * Provides {@code static} utility methods for performing numeric operations.
 * 
 * @author Foroni Marco
 */
@Beta
public final class MoreMath {

	// Suppresses default constructor, ensuring non-instantiability.
	private MoreMath() {
		throw new AssertionError();
	}

	/**
	 * Returns the first power of 2 greater than the given number {@code n}.
	 *
	 * @param n
	 *            the int number
	 * @return the nearest superior {@code int} of the given number {@code n} that is a power of 2
	 */
	public static int nearestSuperiorPowerOf2(final int n) {
		return nearestSuperiorPowerOf(n, 2);
	}

	/**
	 * Returns the first power of the specified {@code base} greater than the given number {@code n}.
	 * 
	 * @param n
	 *            the int number
	 * @param base
	 *            the base
	 * @return the first power of the specified {@code base} greater than the given number {@code n}.
	 */
	public static int nearestSuperiorPowerOf(final int n, final int base) {
		if (base == 2) {
			// https://stackoverflow.com/questions/7685838/how-to-obtain-the-next-power-of-two-of-a-given-number
			return Math.max(1, Integer.highestOneBit(n) << 1);
		}
		int power = 1;
		while (power < n) {
			power = power * base;
		}
		return power;
	}
}
