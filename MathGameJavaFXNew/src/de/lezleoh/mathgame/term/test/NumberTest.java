package de.lezleoh.mathgame.term.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;
import de.lezleoh.mathgame.term.Number;

public class NumberTest {

	@Test
	public final void testGetHitPositions() {
		Number number = new Number(new Integer(0));
		Set<Integer> hitPositions = number.getPossibleHitPositions();
		assertTrue(hitPositions.isEmpty());
	}

	@Test
	public final void testGetValue() {
		Number number = new Number(new Integer(1));
		assertEquals(new Integer(1), number.getValue());

		number = new Number(0);
		assertEquals(new Integer(0), number.getValue());

		number = new Number(-1);
		assertEquals(new Integer(-1), number.getValue());
	}

	@Test
	public final void testGetLengthPositiveNumber() {
		Number number = new Number(123456789);
		assertEquals(new Integer(9), number.getLength());
	}

	@Test
	public final void testGetLengthNegativeNumber() {
		Number number = new Number(-1);
		assertEquals(new Integer(4), number.getLength());
	}

	@Test
	public final void testGetStringPositiveNumber() {
		Number number = new Number(123456789);
		assertEquals("123456789", number.getString());
	}
	
	@Test
	public final void testGetStringNegativeNumber() {
		Number number = new Number(-123456789);
		assertEquals("(-123456789)", number.getString());
	}
	
	@Test
	public final void testGetStringZero() {
		Number number = new Number(0);
		assertEquals("0", number.getString());
	}
}
