package com.github.mforoni.jbasic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.annotations.Beta;
import com.google.common.base.Stopwatch;

/**
 * {@code abstract} class representing a generic portion of code to be tested and monitored using a
 * {@link Stopwatch} to trace the time required.
 * 
 * @author Foroni Marco
 * @see Stopwatch
 */
@Beta
public abstract class JTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(JTest.class);
  private final String name;
  private final Stopwatch stopwatch;

  public JTest(final String name) {
    this.name = name;
    stopwatch = Stopwatch.createUnstarted();
  }

  public JTest start() {
    stopwatch.start();
    run();
    stopwatch.stop();
    return this;
  }

  protected abstract void run();

  public String getName() {
    return name;
  }

  public Stopwatch getStopwatch() {
    return stopwatch;
  }

  public void log() {
    LOGGER.info("{} took {}.", name, stopwatch);
  }

  @Override
  public String toString() {
    return "JTest [name=" + name + "]";
  }
}
