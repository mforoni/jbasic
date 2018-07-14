package com.github.mforoni.jbasic.util;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.mforoni.jbasic.JArrays;
import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Optional;

/**
 * Provides {@code static} utility methods for conveniently logging frequently used objects like
 * {@code Collection}
 * 
 * @author Foroni Marco
 * @see Logger
 */
@Beta
public final class JLogger {
  private static final Logger LOGGER = LoggerFactory.getLogger(JLogger.class);

  // Suppresses default constructor, ensuring non-instantiability.
  private JLogger() {
    throw new AssertionError();
  }

  public enum Level {
    TRACE, DEBUG, INFO, WARN, ERROR
  }

  @Deprecated
  public static void trace(final Optional<Logger> logger, final String msg) {
    log(Level.TRACE, logger, Optional.<Throwable>absent(), msg);
  }

  @Deprecated
  public static void trace(final Optional<Logger> logger, final String msg, final Object... args) {
    log(Level.TRACE, logger, Optional.<Throwable>absent(), msg, args);
  }

  @Deprecated
  public static void info(final Optional<Logger> logger, final String msg) {
    log(Level.INFO, logger, Optional.<Throwable>absent(), msg);
  }

  @Deprecated
  public static void info(final Optional<Logger> logger, final String msg, final Object... args) {
    log(Level.INFO, logger, Optional.<Throwable>absent(), msg, args);
  }

  @Deprecated
  public static void error(final Optional<Logger> logger, final String msg, final Throwable t) {
    log(Level.ERROR, logger, Optional.of(t), msg);
  }

  @Deprecated
  private static void log(final Level level, final Optional<Logger> optLogger,
      final Optional<Throwable> t, String msg, final Object... args) {
    if (args != null && args.length > 0) {
      msg = String.format(msg, args);
    }
    if (optLogger.isPresent()) {
      if (t.isPresent()) {
        log(level, optLogger.get(), msg, t.get());
      } else {
        log(level, optLogger.get(), msg);
      }
    } else {
      if (level == Level.ERROR) {
        System.err.println(msg);
        if (t.isPresent()) {
          t.get().printStackTrace();
        }
      } else {
        System.out.println(msg);
      }
    }
  }

  // TODO make private
  public static void log(@Nonnull final Level level, @Nonnull final Logger logger,
      @Nonnull final String msg) {
    switch (level) {
      case DEBUG:
        logger.debug(msg);
        break;
      case ERROR:
        logger.error(msg);
        break;
      case INFO:
        logger.info(msg);
        break;
      case TRACE:
        logger.trace(msg);
        break;
      case WARN:
        logger.warn(msg);
        break;
      default:
        throw new AssertionError();
    }
  }

  private static void log(final Level level, final Logger logger, final String msg,
      final Throwable t) {
    switch (level) {
      case DEBUG:
        logger.debug(msg, t);
        break;
      case ERROR:
        logger.error(msg, t);
        break;
      case INFO:
        logger.info(msg, t);
        break;
      case TRACE:
        logger.trace(msg, t);
        break;
      case WARN:
        logger.warn(msg, t);
        break;
      default:
        logger.trace(msg, t);
        break;
    }
  }

  public static void trace(@Nonnull final Collection<?> collection) {
    log(Level.TRACE, collection);
  }

  public static void info(@Nonnull final Collection<?> collection) {
    log(Level.INFO, collection);
  }

  private static void log(@Nonnull final Level level, @Nonnull final Collection<?> collection) {
    for (final Object obj : collection) {
      log(level, LOGGER, obj.toString());
    }
  }

  public static <T> void info(@Nonnull final Collection<T> collection,
      @Nonnull final Function<T, String> toMessage) {
    for (final T element : collection) {
      LOGGER.info("{}", toMessage.apply(element));
    }
  }

  public static <T, U> void info(@Nonnull final Set<Entry<T, U>> entrySet) {
    int i = 1;
    for (final Entry<T, U> entry : entrySet) {
      final Object key = entry.getKey();
      final Object value = entry.getValue();
      LOGGER.info("{}: {} = {}", i++, key, value);
    }
  }

  public static void info(@Nonnull final List<?> list) {
    for (int i = 0; i < list.size(); i++) {
      LOGGER.info("{} = {}", i + 1, list.get(i));
    }
  }

  public static void info(@Nonnull final List<?> list, final int limit) {
    for (int i = 0; i < list.size() && i < limit; i++) {
      LOGGER.info("{} = {}", i + 1, list.get(i));
    }
  }

  public static <T> void info(@Nonnull final T[] array) {
    LOGGER.info("{}", JArrays.toString(array));
  }

  public static <T> void info(@Nonnull final T[] array, final int limit,
      @Nonnull final Function<T, String> toMessage) {
    LOGGER.info("Logging the first {} elements of {}", limit, array.length);
    final int max = array.length > limit ? limit : array.length;
    for (int i = 0; i < max; i++) {
      LOGGER.info(toMessage.apply(array[i]));
    }
  }

  @Beta
  public static <T> void tailInfo(@Nonnull final T[] array, final int limit,
      final Function<T, String> toMessage) {
    LOGGER.info("Logging the first {} elements of {} starting from the end", array.length);
    final int max = array.length > limit ? limit : array.length;
    final int last = array.length;
    for (int i = last - 1; i >= last - max; i--) {
      LOGGER.info(toMessage.apply(array[i]));
    }
  }
}
