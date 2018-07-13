package com.github.mforoni.jbasic.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import com.github.mforoni.jbasic.reflect.JBeans;
import com.github.mforoni.jsupport.beans.PersonBean;
import com.google.common.base.Optional;

/**
 * @author Foroni Marco
 */
public class JBeansTest {

	@Test
	public void testGetter() {
		try {
			final Method getName = JBeans.getter(PersonBean.class, "name");
			assertEquals("getName", getName.getName());
			final Method isDeceased = JBeans.getter(PersonBean.class, "deceased");
			assertEquals("isDeceased", isDeceased.getName());
		} catch (final IntrospectionException ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testOptionalGetter() {
		final Optional<Method> optionalGetName = JBeans.optionalGetter(PersonBean.class, "name");
		assertTrue(optionalGetName.isPresent());
		assertEquals("getName", optionalGetName.get().getName());
		final Optional<Method> optionalIsDeceased = JBeans.optionalGetter(PersonBean.class, "deceased");
		assertTrue(optionalIsDeceased.isPresent());
		assertEquals("isDeceased", optionalIsDeceased.get().getName());
		final Optional<Method> optionalGetFirstName = JBeans.optionalGetter(PersonBean.class, "firstName");
		assertFalse(optionalGetFirstName.isPresent());
	}

	@Test
	public void testSetter() {
		try {
			final Method setName = JBeans.setter(PersonBean.class, "name");
			assertEquals("setName", setName.getName());
			final Method setDeceased = JBeans.setter(PersonBean.class, "deceased");
			assertEquals("setDeceased", setDeceased.getName());
		} catch (final IntrospectionException ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void testOptionalSetter() {
		final Optional<Method> optionalSetName = JBeans.optionalSetter(PersonBean.class, "name");
		assertEquals("setName", optionalSetName.get().getName());
		final Optional<Method> optionalSetDeceased = JBeans.optionalSetter(PersonBean.class, "deceased");
		assertEquals("setDeceased", optionalSetDeceased.get().getName());
		final Optional<Method> optionalSetEmail = JBeans.optionalSetter(PersonBean.class, "email");
		assertFalse(optionalSetEmail.isPresent());
	}

	@Test
	public void testInvokeSetter() {
		final PersonBean personBean = new PersonBean();
		personBean.setName("Mario Rossi");
		try {
			JBeans.invokeSetter(PersonBean.class, "name", personBean, "Franco Neri");
			assertEquals("Franco Neri", personBean.getName());
		} catch (IllegalStateException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
