package com.github.mforoni.jbasic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import com.github.mforoni.jbasic.JLists;
import com.google.common.collect.ImmutableList;

public class JListsTest {
  @Test
  public void testContainsIgnoreCase() {
    final List<String> list =
        ImmutableList.of("In", "mathematics,", "a", "sequence", "is", "an", "enumerated",
            "collection", "of", "objects", "in", "which", "repetitions", "are", "allowed");
    assertTrue(JLists.containsIgnoreCase(list, "in"));
    assertTrue(JLists.containsIgnoreCase(list, "A"));
    assertFalse(JLists.containsIgnoreCase(list, "mathematics"));
    assertFalse(JLists.containsIgnoreCase(list, " is"));
  }

  @Test
  public void testIndexOfIgnoreCaseListOfStringString() {
    final List<String> list =
        ImmutableList.of("In", "mathematics,", "a", "sequence", "is", "an", "enumerated",
            "collection", "of", "objects", "in", "which", "repetitions", "are", "allowed");
    assertEquals(0, JLists.indexOfIgnoreCase(list, "in"));
    assertEquals(2, JLists.indexOfIgnoreCase(list, "A"));
    assertEquals(1, JLists.indexOfIgnoreCase(list, "mathEmaticS,"));
    assertEquals(4, JLists.indexOfIgnoreCase(list, "is"));
  }

  @Test
  public void testIndexOfIgnoreCaseListOfStringStringInt() {
    final List<String> list =
        ImmutableList.of("In", "mathematics,", "a", "sequence", "is", "an", "enumerated",
            "collection", "of", "objects", "in", "which", "repetitions", "are", "allowed");
    assertEquals(10, JLists.indexOfIgnoreCase(list, "in", 1));
    assertEquals(-1, JLists.indexOfIgnoreCase(list, "A", 4));
    assertEquals(1, JLists.indexOfIgnoreCase(list, "mathEmaticS,", 1));
    assertEquals(4, JLists.indexOfIgnoreCase(list, "is", 3));
  }

  @Test
  public void testNewSortedArrayListCollectionOfFFunctionOfQsuperFQextendsT() {
    // do nothing: relies on guava transform and java collection sort
  }

  @Test
  public void testNewSortedArrayListCollectionOfT() {
    // do nothing: relies on java collection sort
  }

  @Test
  public void testNewSortedArrayListCollectionOfTComparatorOfQsuperT() {
    // do nothing: relies on java collection sort
  }
}
