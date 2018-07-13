package com.github.mforoni.jbasic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import com.github.mforoni.jbasic.MoreInts;

public class MoreIntsTest {

	@Test
	public final void testToIntExact() {
		assertTrue(MoreInts.toIntExact(12600L) == 12600);
		try {
			final long l = ((long) Integer.MAX_VALUE) + 1;
			MoreInts.toIntExact(l);
			fail();
		} catch (final ArithmeticException e) {
			assertNotNull(e);
		}
	}

	@Test
	public final void testNewRandomInt() {
		int random = MoreInts.newRandom(0);
		assertEquals(0, random);
		random = MoreInts.newRandom(1);
		assertTrue(random == 0 || random == 1);
	}

	@Test
	public final void testNewRandomIntInt() {
		int random = MoreInts.newRandom(11, 11);
		assertEquals(11, random);
		random = MoreInts.newRandom(11, 12);
		assertTrue(random == 11 || random == 12);
		random = MoreInts.newRandom(1, 5);
		assertTrue(random >= 1 && random <= 5);
		random = MoreInts.newRandom(1, 10);
		assertTrue(random >= 1 && random <= 10);
	}

	@Test
	public final void testNewRandomArrayIntIntInt() {
		final int[] randoms = MoreInts.newRandomArray(50, -45, 230_000);
		assertTrue(randoms.length == 50);
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (final int n : randoms) {
			if (n < min) {
				min = n;
			}
			if (n > max) {
				max = n;
			}
		}
		assertTrue(min >= -45);
		assertTrue(max <= 230_000);
	}

	@Test
	public final void testNewRandomArrayIntInt() {
		// do nothing: relies on MoreInts.newRandomArray(int, int, int)
	}

	@Test
	public final void testConsecutives() {
		final int[] consecutives = MoreInts.consecutives(5, 17);
		assertTrue(consecutives.length == 17 - 5 + 1);
		assertArrayEquals(new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 }, consecutives);
	}
}
