package com.github.mforoni.jbasic.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.github.mforoni.jbasic.math.MoreMath;

/**
 * @author Foroni Marco
 */
public class MoreMathTest {

	@Test
	public void testNearestSuperiorPowerOf2() {
		assertEquals(1, MoreMath.nearestSuperiorPowerOf2(0));
		assertEquals(2, MoreMath.nearestSuperiorPowerOf2(1));
		assertEquals(4, MoreMath.nearestSuperiorPowerOf2(2));
		assertEquals(64, MoreMath.nearestSuperiorPowerOf2(33));
		assertEquals(256, MoreMath.nearestSuperiorPowerOf2(128));
		assertEquals(256, MoreMath.nearestSuperiorPowerOf2(133));
		assertEquals(1024, MoreMath.nearestSuperiorPowerOf2(833));
		assertEquals(4096, MoreMath.nearestSuperiorPowerOf2(3560));
	}

	@Test
	public void testNearestSuperiorPowerOf() {
		assertEquals(125, MoreMath.nearestSuperiorPowerOf(35, 5));
	}
}
