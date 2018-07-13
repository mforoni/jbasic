package com.github.mforoni.jbasic.demo;

import java.util.Arrays;
import com.github.mforoni.jbasic.MoreInts;
import com.github.mforoni.jbasic.util.JTest;

/**
 * Demo class showing some sorting tests on random {@code int} arrays.
 * 
 * @author Foroni Marco
 * @see JTest
 */
final class JTestDemo {

	private JTestDemo() {}

	static class SortingTest extends JTest {

		private final int[] a;

		SortingTest(final int[] a) {
			super(getDescription(a));
			this.a = a;
		}

		private static String getDescription(final int[] a) {
			return a.length > 10 ? "Sorting test on array of " + a.length + " elements" : "Sorting test on array " + Arrays.toString(a);
		}

		@Override
		protected void run() {
			Arrays.sort(a);
			assertIsSorted(a);
		}

		private static void assertIsSorted(final int[] a) {
			for (int i = 0; i < a.length - 1; i++) {
				if (a[i] > a[i + 1]) {
					throw new AssertionError();
				}
			}
		}
	}

	public static void main(final String[] args) {
		new SortingTest(MoreInts.newRandomArray(25000, -20, 650)).start().log();;
		new SortingTest(MoreInts.newRandomArray(150000, -20, 650)).start().log();;
	}
}
