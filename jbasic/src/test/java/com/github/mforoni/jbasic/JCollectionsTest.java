package com.github.mforoni.jbasic;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import com.github.mforoni.jbasic.JCollections;
import com.google.common.collect.ImmutableList;

/**
 * @author Foroni Marco
 */
public class JCollectionsTest {

	@Test
	public final void testToArray() {
		final List<String> list = ImmutableList.of("In", "mathematics,", "a", "sequence", "is", "an", "enumerated", "collection", "of", "objects",
				"in", "which", "repetitions", "are", "allowed");
		final String[] strings = JCollections.toArray(list, String.class);
		for (int i = 0; i < strings.length; i++) {
			assertEquals(list.get(i), strings[i]);
		}
	}
}
