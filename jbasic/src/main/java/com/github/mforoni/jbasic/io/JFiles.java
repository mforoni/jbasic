package com.github.mforoni.jbasic.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.annotations.Beta;
import com.google.common.base.Predicate;

/**
 * Provides {@code static} utility methods for manipulating files and directories.
 *
 * @author Foroni Marco
 * @see File
 * @see Path
 * @see Files
 * @see com.google.common.io.Files
 */
@Beta
public final class JFiles {
  public static final String USER_DIR = System.getProperty("user.dir");
  public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
  public static final Path SRC_MAIN_JAVA = Paths.get("src", "main", "java");
  private static final String CSV = "csv";

  // Suppresses default constructor, ensuring non-instantiability.
  private JFiles() {
    throw new AssertionError();
  }

  public static boolean isCsv(@Nonnull final File file) {
    return com.google.common.io.Files.getFileExtension(file.getName()).equalsIgnoreCase(CSV);
  }

  /**
   * Returns a new {@code File} from the given resource.
   * 
   * @param name the resource's name
   * @return a new {@code File} from the given resource.
   * @throws FileNotFoundException if no resource with the specified {@code name} is found
   * @see ClassLoader#getResource(String)
   */
  @Nonnull
  public static File fromResource(@Nonnull final String name) throws FileNotFoundException {
    final URL url = _getResource(name);
    if (url == null) {
      throw new FileNotFoundException("Cannot find resource " + name);
    }
    final String resource = url.getFile();
    return new File(resource);
  }

  @Nullable
  static URL _getResource(@Nonnull final String name) {
    return Thread.currentThread().getContextClassLoader().getResource(name);
  }

  /**
   * Returns a new {@link FileReader} from the given {@code resource}.
   * 
   * @param resource the resource's name
   * @return a new {@link FileReader} from the given {@code resource}.
   * @throws FileNotFoundException
   * @see JFiles#fromResource(String)
   */
  public static FileReader newFileReader(@Nonnull final String resource)
      throws FileNotFoundException {
    return new FileReader(fromResource(resource));
  }

  public static List<String> readLines(@Nonnull final File file) throws IOException {
    return com.google.common.io.Files.readLines(file, CHARSET_UTF8);
  }

  public static List<String> readLines(@Nonnull final Path path) throws IOException {
    return Files.readAllLines(path, CHARSET_UTF8);
  }

  public static List<Path> list(@Nonnull final Path dirPath) throws IOException {
    return list(dirPath, null);
  }

  public static List<Path> list(@Nonnull final Path dirPath,
      @Nullable final Predicate<Path> predicate) throws IOException {
    final List<Path> paths = new ArrayList<>();
    try (final DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath)) {
      final Iterator<Path> iterator = directoryStream.iterator();
      while (iterator.hasNext()) {
        final Path next = iterator.next();
        if (predicate == null || predicate.apply(next)) {
          paths.add(next);
        }
      }
    }
    return paths;
  }

  public static int delete(final List<Path> paths) throws IOException {
    int deleted = 0;
    for (final Path path : paths) {
      if (Files.deleteIfExists(path)) {
        deleted++;
      }
    }
    return deleted;
  }
}
