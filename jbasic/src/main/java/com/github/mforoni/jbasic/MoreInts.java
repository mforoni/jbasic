package com.github.mforoni.jbasic;

import java.util.concurrent.ThreadLocalRandom;
import com.google.common.annotations.Beta;

/**
 * Provides {@code static} utility methods useful for dealing with the primitive {@code int} type.
 * 
 * @author Foroni Marco
 */
public final class MoreInts {

	// Suppresses default constructor, ensuring non-instantiability.
	private MoreInts() {
		throw new AssertionError();
	}

	/**
	 * Returns the value of the {@code long} argument; throwing an exception if the value overflows an {@code int}.
	 * <p>
	 * Available in JDK 1.8.
	 *
	 * @param value
	 *            the long value
	 * @return the argument as an int primitive type
	 * @throws ArithmeticException
	 *             if the {@code argument} overflows an int
	 */
	public static int toIntExact(final long value) {
		if ((int) value != value) {
			throw new ArithmeticException("integer overflow");
		}
		return (int) value;
	}

	/**
	 * Returns a random integer between <tt>0</tt> and {@code max} extremes included.
	 * 
	 * @param max
	 *            the maximum possible value
	 * @return a random integer between <tt>0</tt> and {@code max} extremes included
	 */
	public static int newRandom(final int max) {
		return newRandom(0, max);
	}

	/**
	 * Returns a random integer between {@code min} and {@code max} extremes included.
	 * 
	 * @param min
	 *            the minimum possible value
	 * @param max
	 *            the maximum possible value
	 * @return a random integer between {@code min} and {@code max} extremes included
	 */
	public static int newRandom(final int min, final int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	/**
	 * Returns an array of int primitives of length {@code n}, filled with random numbers between {@code min} and
	 * {@code max} extremes included.
	 * 
	 * @param n
	 *            the required array length
	 * @param min
	 *            the minimum integer value allowed
	 * @param max
	 *            the maximum integer value allowed
	 * @return an array of int primitives of length {@code n}, filled with random numbers between {@code min} and
	 *         {@code max} extremes included.
	 */
	public static int[] newRandomArray(final int n, final int min, final int max) {
		final int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = newRandom(min, max);
		}
		return a;
	}

	/**
	 * Returns an array of int primitives of length {@code n}, filled with random numbers between {@code 0} and
	 * {@code max} extremes included.
	 * 
	 * @param n
	 *            the required array length
	 * @param max
	 *            the maximum integer value allowed
	 * @return an array of int primitives of length {@code n}, filled with random numbers between {@code 0} and
	 *         {@code max} extremes included.
	 */
	public static int[] newRandomArray(final int n, final int max) {
		return newRandomArray(n, 0, max);
	}

	/**
	 * Returns a new array of int elements having the value {@code start} in the first position, {@code start+1} in the
	 * second position and so on till {@code max} at the end.
	 * 
	 * @param start
	 * @param end
	 * @return a new array of int elements having the value {@code start} in the first position, {@code start+1} in the
	 *         second position and so on till {@code max} at the end.
	 */
	@Beta
	public static int[] consecutives(final int start, final int end) {
		final int size = end - start + 1;
		final int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = start + i;
		}
		return array;
	}
}