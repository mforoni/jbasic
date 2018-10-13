package com.github.mforoni.jbasic.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JFilesTest {
  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testIsCsv() {
    assertTrue(JFiles.isCsv(new File("test.csv")));
    assertFalse(JFiles.isCsv(new File("testcsv")));
  }

  @Test
  public void testFromResource() {
    try {
      final File file = JFiles.fromResource("test.txt");
      assertNotNull(file);
      assertTrue(file.exists());
    } catch (final IOException e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void testNewFileReader() {}

  @Test
  public void testReadLinesFile() {
    try {
      final List<String> lines = JFiles.readLines(JFiles.fromResource("test.txt"));
      assertTrue(lines.size() == 1);
      assertTrue(lines.get(0).startsWith("Lorem ipsum"));
    } catch (final IOException e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void testReadLinesPath() {}

  @Test
  public void testListPath() {}

  @Test
  public void testListPathPredicateOfPath() {}

  @Test
  public void testDelete() {}
}
