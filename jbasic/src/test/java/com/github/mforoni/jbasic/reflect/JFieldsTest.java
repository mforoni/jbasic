package com.github.mforoni.jbasic.reflect;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.Test;
import com.github.mforoni.jbasic.reflect.JFields;
import com.github.mforoni.jsupport.Book;
import com.github.mforoni.jsupport.ImmutablePerson;
import com.github.mforoni.jsupport.MutablePerson;
import com.github.mforoni.jsupport.Person;

/**
 * @author Foroni Marco
 */
public class JFieldsTest {

	private static final Field IMMUTABLE_PERSON_FIRST_NAME = JFields.nonnull(ImmutablePerson.class, "firstName");
	private static final Field BOOK_ISBN = JFields.nonnull(Book.class, "isbn");
	private static final Field BOOK_YEAR = JFields.nonnull(Book.class, "year");
	private static final Field BOOK_AUTHOR = JFields.nonnull(Book.class, "author");
	private static final Field PERSON_GET_LASTNAME = JFields.nonnull(Person.class, "GET_LASTNAME");
	private static final Field MUTABLEPERSON_GET_LASTNAME = JFields.nonnull(MutablePerson.class, "GET_LASTNAME");

	@Test
	public final void testIsFinal() {
		assertFalse(JFields.isFinal(IMMUTABLE_PERSON_FIRST_NAME));
		assertTrue(JFields.isFinal(BOOK_ISBN));
		assertTrue(JFields.isFinal(BOOK_YEAR));
		assertTrue(JFields.isFinal(BOOK_AUTHOR));
		assertTrue(JFields.isFinal(PERSON_GET_LASTNAME));
		assertTrue(JFields.isFinal(MUTABLEPERSON_GET_LASTNAME));
	}

	@Test
	public final void testIsPublic() {
		assertFalse(JFields.isPublic(IMMUTABLE_PERSON_FIRST_NAME));
		assertFalse(JFields.isPublic(BOOK_ISBN));
		assertFalse(JFields.isPublic(BOOK_YEAR));
		assertFalse(JFields.isPublic(BOOK_AUTHOR));
		assertTrue(JFields.isPublic(PERSON_GET_LASTNAME));
		assertTrue(JFields.isPublic(MUTABLEPERSON_GET_LASTNAME));
	}

	@Test
	public final void testIsStatic() {
		assertFalse(JFields.isStatic(IMMUTABLE_PERSON_FIRST_NAME));
		assertFalse(JFields.isStatic(BOOK_ISBN));
		assertFalse(JFields.isStatic(BOOK_YEAR));
		assertFalse(JFields.isStatic(BOOK_AUTHOR));
		assertTrue(JFields.isStatic(PERSON_GET_LASTNAME));
		assertTrue(JFields.isStatic(MUTABLEPERSON_GET_LASTNAME));
	}

	@Test
	public final void testIsPrivate() {
		assertFalse(JFields.isPrivate(IMMUTABLE_PERSON_FIRST_NAME));
		assertTrue(JFields.isPrivate(BOOK_ISBN));
		assertTrue(JFields.isPrivate(BOOK_YEAR));
		assertTrue(JFields.isPrivate(BOOK_AUTHOR));
		assertFalse(JFields.isPrivate(PERSON_GET_LASTNAME));
		assertFalse(JFields.isPrivate(MUTABLEPERSON_GET_LASTNAME));
	}

	@Test
	public final void testFromTypeClassOfQStringStringArray() {
		// TODO
	}

	@Test
	public final void testFromTypeClassOfQ() {
		final List<Field> fields = JFields.fromType(Book.class);
		final Field[] declaredFields = Book.class.getDeclaredFields();
		assertEquals(declaredFields.length, fields.size());
		assertArrayEquals(declaredFields, fields.toArray(new Field[fields.size()]));
	}

	@Test
	public final void testFromTypeClassOfQPredicateOfField() {
		// TODO
	}

	@Test
	public final void testOptional() {
		// TODO
	}

	@Test
	public final void testNonnull() {
		assertNotNull(IMMUTABLE_PERSON_FIRST_NAME);
		assertNotNull(BOOK_ISBN);
		assertNotNull(BOOK_YEAR);
		assertNotNull(BOOK_AUTHOR);
		assertNotNull(PERSON_GET_LASTNAME);
		assertNotNull(MUTABLEPERSON_GET_LASTNAME);
	}

	@Test
	public final void testNullable() {
		// TODO
	}

	@Test
	public final void testConventionalName() {}
}
