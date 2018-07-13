package com.github.mforoni.jbasic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.annotations.Beta;
import com.google.common.base.Function;

/**
 * @author Foroni Marco
 */
@Beta
public final class JMaps {

	// Suppresses default constructor, ensuring non-instantiability.
	private JMaps() {
		throw new AssertionError();
	}

	/**
	 * Returns a new {@code HashMap} of generic types {@code K}, {@code V} from the given {@code Collection}
	 * where the keys are obtained applying the given {@code function} to each {@code Collection}'s value.
	 * 
	 * @param values
	 *            a {@code Collection} of generic objects having type {@code V}
	 * @param function
	 *            a {@code Function}
	 * @return a new {@code HashMap} of generic types {@code K}, {@code V} from the given {@code Collection}
	 * @see JMaps#putEnsuringNoCollision(Map, Object, Object)
	 */
	public static <K, V> Map<K, V> newHashMapNoCollisions(final Collection<V> values, final Function<V, K> function) {
		final Map<K, V> map = new HashMap<>();
		for (final V value : values) {
			putEnsuringNoCollision(map, function.apply(value), value);
		}
		return map;
	}

	public static <K, V> Map<K, List<V>> newHashMap(final Collection<V> values, final Function<V, K> function) {
		final Map<K, List<V>> map = new HashMap<>();
		for (final V value : values) {
			add(map, function.apply(value), value);
		}
		return map;
	}

	/**
	 * Returns a new {@code HashMap} of generic types {@code K}, {@code V} containing the key-value mapping
	 * specified as parameters.
	 * 
	 * @param key
	 *            a key of generic type {@code K}
	 * @param value
	 *            a value of generic type {@code V}
	 * @return a new {@code HashMap} containing the specified key-value mapping
	 * @see HashMap
	 */
	public static <K, V> Map<K, V> newHashMap(final K key, final V value) {
		final Map<K, V> map = new HashMap<>();
		map.put(key, value);
		return map;
	}

	/**
	 * Puts the specified key-value mapping in the given {@code map} ensuring that no previous value are
	 * mapped with the specified {@code key}.
	 * 
	 * @param map
	 *            a {@code Map}
	 * @param key
	 *            a key of generic type {@code K}
	 * @param value
	 *            a value of generic type {@code V}
	 * @throws IllegalStateException
	 *             if the specified {@code key} is already in map associated with another value
	 */
	public static <K, V> void putEnsuringNoCollision(final Map<K, V> map, final K key, final V value) throws IllegalStateException {
		final V previous = map.put(key, value);
		if (previous != null) {
			throw new IllegalStateException("The key '" + key + "' is already in map associated with value '" + previous + "'.");
		}
	}

	public static <K, V> void add(final Map<K, List<V>> map, final K key, final V value) {
		List<V> list = map.get(key);
		if (list == null) {
			list = new ArrayList<>();
		}
		final boolean result = list.add(value);
		if (result == false) {
			throw new IllegalStateException();
		}
		map.put(key, list);
	}
}
