package com.github.mforoni.jbasic.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.base.Optional;

/**
 * Provides {@code static} utility methods for retrieving information about, and access to, methods of a class
 * conforming to the <a href="https://en.wikipedia.org/wiki/JavaBeans">JavaBeans</a> specification.
 * 
 * @author Foroni Marco
 */
public final class JBeans {

	// Suppresses default constructor, ensuring non-instantiability.
	private JBeans() {
		throw new AssertionError();
	}

	/**
	 * Retrieves the getter {@code Method} of the specified {@code beanClass} corresponding to the given
	 * {@code propertyName}.
	 * 
	 * @param beanClass
	 *            the JavaBean class
	 * @param propertyName
	 *            the name of the property
	 * @return the getter {@code Method} of the specified {@code beanClass} corresponding to the given
	 *         {@code propertyName}
	 * @throws IntrospectionException
	 * @see Method
	 * @see PropertyDescriptor#getReadMethod()
	 */
	@Nullable
	public static Method getter(@Nonnull final Class<?> beanClass, @Nonnull final String propertyName) throws IntrospectionException {
		final PropertyDescriptor pd = new PropertyDescriptor(propertyName, beanClass);
		return pd.getReadMethod();
	}

	/**
	 * Retrieves the {@code Optional} getter {@code Method} of the specified {@code beanClass} corresponding
	 * to the given {@code propertyName}.
	 * 
	 * @param beanClass
	 *            the JavaBean class
	 * @param propertyName
	 *            the name of the property
	 * @return the {@code Optional} getter {@code Method} of the specified {@code beanClass} corresponding to
	 *         the given {@code propertyName}.
	 * @see Optional
	 * @see Method
	 * @see PropertyDescriptor#getReadMethod()
	 */
	public static Optional<Method> optionalGetter(@Nonnull final Class<?> beanClass, @Nonnull final String propertyName) {
		try {
			return Optional.of(getter(beanClass, propertyName));
		} catch (final IntrospectionException e) {
			return Optional.absent();
		}
	}

	/**
	 * Retrieves the setter {@code Method} of the specified {@code beanClass} corresponding to the given
	 * {@code propertyName}.
	 * 
	 * @param beanClass
	 *            the JavaBean class
	 * @param propertyName
	 *            the name of the property
	 * @return the setter {@code Method} of the specified {@code beanClass} corresponding to the given
	 *         {@code propertyName}.
	 * @return the setter {@code Method} of the specified {@code beanClass} corresponding to the given
	 *         {@code propertyName}
	 * @throws IntrospectionException
	 * @see Method
	 * @see PropertyDescriptor#getReadMethod()
	 */
	@Nullable
	public static Method setter(@Nonnull final Class<?> beanClass, @Nonnull final String propertyName) throws IntrospectionException {
		final PropertyDescriptor pd = new PropertyDescriptor(propertyName, beanClass);
		return pd.getWriteMethod();
	}

	/**
	 * Retrieves the {@code Optional} setter {@code Method} of the specified {@code beanClass} corresponding
	 * to the given {@code propertyName}.
	 * 
	 * @param beanClass
	 *            the JavaBean class
	 * @param propertyName
	 *            the name of the property
	 * @return the {@code Optional} setter {@code Method} of the specified {@code beanClass} corresponding to
	 *         the given {@code propertyName}.
	 * @see Optional
	 * @see Method
	 * @see PropertyDescriptor#getReadMethod()
	 */
	public static Optional<Method> optionalSetter(@Nonnull final Class<?> beanClass, @Nonnull final String propertyName) {
		try {
			return Optional.of(setter(beanClass, propertyName));
		} catch (final IntrospectionException e) {
			return Optional.absent();
		}
	}

	/**
	 * Sets the specified {@code value} to the property {@code propertyName} of the provided bean
	 * {@code beanClass} using the corresponding setter method.
	 * 
	 * @param beanClass
	 *            the JavaBean class
	 * @param propertyName
	 *            the name of the property
	 * @param instance
	 *            the instance to modify
	 * @param value
	 *            the value to set
	 * @throws IllegalStateException
	 *             if it is impossible to find the method corresponding to the given property name
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @see JBeans#optionalSetter(Class, String)
	 * @see Method#invoke(Object, Object...)
	 */
	public static <T> void invokeSetter(@Nonnull final Class<T> beanClass, @Nonnull final String propertyName, @Nonnull final T instance,
			@Nonnull final Object value) throws IllegalStateException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Optional<Method> method = optionalSetter(beanClass, propertyName);
		if (method.isPresent()) {
			method.get().invoke(instance, value);
		} else {
			throw new IllegalStateException(String.format("Cannot find setter method of property %s in bean %s", beanClass, propertyName));
		}
	}
}
