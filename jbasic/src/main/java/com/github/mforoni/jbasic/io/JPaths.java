package com.github.mforoni.jbasic.io;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.Nonnull;
import com.google.common.annotations.Beta;
import com.google.common.base.Optional;

/**
 * @author Foroni Marco
 */
@Beta
public final class JPaths {
  private JPaths() {
    throw new AssertionError();
  }

  @Nonnull
  public static Path getResource(@Nonnull final String name)
      throws FileNotFoundException, URISyntaxException {
    final URL url = JFiles._getResource(name);
    if (url == null) {
      throw new FileNotFoundException("Cannot find resource " + name);
    }
    return Paths.get(url.toURI());
  }

  public static Optional<Path> getOptionalResource(@Nonnull final String name) {
    final URL url = JFiles._getResource(name);
    try {
      return url == null ? Optional.<Path>absent() : Optional.of(Paths.get(url.toURI()));
    } catch (final URISyntaxException e) {
      return Optional.absent();
    }
  }
}
