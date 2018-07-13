package com.github.mforoni.jbasic.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.annotations.Beta;

/**
 * Workaround for different computer systems not always working with {@code Desktop.getDesktop.open()}. This was taken
 * from http://stackoverflow.com/questions/18004150/desktop-api-is-not-supported-on-the-current-platform, with some
 * modifications made for simplicity and logging with slf4j2
 */
@Beta
public final class JDesktop {

	private static final Logger LOGGER = LoggerFactory.getLogger(JDesktop.class);

	// Suppresses default constructor, ensuring non-instantiability.
	private JDesktop() {
		throw new AssertionError();
	}

	public static boolean browse(final URI uri) {
		return openSystemSpecific(uri.toString()) || _browse(uri);
	}

	public static boolean open(final File file) {
		return openSystemSpecific(file.getPath()) || _open(file);
	}

	public static boolean edit(final File file) {
		return openSystemSpecific(file.getPath()) || _edit(file);
	}

	private static boolean openSystemSpecific(final String what) {
		final OS os = getOs();
		if (os.isLinux()) {
			if (runCommand("kde-open", "%s", what)) {
				return true;
			}
			if (runCommand("gnome-open", "%s", what)) {
				return true;
			}
			if (runCommand("xdg-open", "%s", what)) {
				return true;
			}
		}
		if (os.isMac()) {
			if (runCommand("open", "%s", what)) {
				return true;
			}
		}
		if (os.isWindows()) {
			if (runCommand("explorer", "%s", what)) {
				return true;
			}
		}
		return false;
	}

	private static boolean _browse(final URI uri) {
		LOGGER.debug("Trying to use Desktop.getDesktop().browse() with " + uri.toString());
		try {
			if (!Desktop.isDesktopSupported()) {
				LOGGER.error("Platform is not supported.");
				return false;
			}
			if (!Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				LOGGER.error("BROWSE is not supported.");
				return false;
			}
			Desktop.getDesktop().browse(uri);
			return true;
		} catch (final Throwable t) {
			LOGGER.error("Error using desktop browse.", t);
			return false;
		}
	}

	private static boolean _open(final File file) {
		LOGGER.debug("Trying to use Desktop.getDesktop().open() with " + file.toString());
		try {
			if (!Desktop.isDesktopSupported()) {
				LOGGER.error("Platform is not supported.");
				return false;
			}
			if (!Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
				LOGGER.error("OPEN is not supported.");
				return false;
			}
			Desktop.getDesktop().open(file);
			return true;
		} catch (final Throwable t) {
			LOGGER.error("Error using desktop open.", t);
			return false;
		}
	}

	private static boolean _edit(final File file) {
		LOGGER.debug("Trying to use Desktop.getDesktop().edit() with " + file);
		try {
			if (!Desktop.isDesktopSupported()) {
				LOGGER.error("Platform is not supported.");
				return false;
			}
			if (!Desktop.getDesktop().isSupported(Desktop.Action.EDIT)) {
				LOGGER.error("EDIT is not supported.");
				return false;
			}
			Desktop.getDesktop().edit(file);
			return true;
		} catch (final Throwable t) {
			LOGGER.error("Error using desktop edit.", t);
			return false;
		}
	}

	private static boolean runCommand(final String command, final String args, final String file) {
		LOGGER.debug("Trying to exec:\n   cmd = " + command + "\n   args = " + args + "\n   %s = " + file);
		final String[] parts = prepareCommand(command, args, file);
		try {
			final Process p = Runtime.getRuntime().exec(parts);
			if (p == null) {
				return false;
			}
			try {
				final int retval = p.exitValue();
				if (retval == 0) {
					LOGGER.error("Process ended immediately.");
					return false;
				} else {
					LOGGER.error("Process crashed.");
					return false;
				}
			} catch (final IllegalThreadStateException itse) {
				LOGGER.error("Process is running.");
				return true;
			}
		} catch (final IOException e) {
			LOGGER.error("Error running command.", e);
			return false;
		}
	}

	private static String[] prepareCommand(final String command, final String args, final String file) {
		final List<String> parts = new ArrayList<>();
		parts.add(command);
		if (args != null) {
			for (String s : args.split(" ")) {
				s = String.format(s, file); // put in the filename thing
				parts.add(s.trim());
			}
		}
		return parts.toArray(new String[parts.size()]);
	}

	public enum OS {
		LINUX, MACOS, SOLARIS, UNKNOWN, WINDOWS;

		public boolean isLinux() {
			return this == LINUX || this == SOLARIS;
		}

		public boolean isMac() {
			return this == MACOS;
		}

		public boolean isWindows() {
			return this == WINDOWS;
		}
	}

	public static OS getOs() {
		final String s = System.getProperty("os.name").toLowerCase();
		if (s.contains("win")) {
			return OS.WINDOWS;
		}
		if (s.contains("mac")) {
			return OS.MACOS;
		}
		if (s.contains("solaris")) {
			return OS.SOLARIS;
		}
		if (s.contains("sunos")) {
			return OS.SOLARIS;
		}
		if (s.contains("linux")) {
			return OS.LINUX;
		}
		if (s.contains("unix")) {
			return OS.LINUX;
		} else {
			return OS.UNKNOWN;
		}
	}
}