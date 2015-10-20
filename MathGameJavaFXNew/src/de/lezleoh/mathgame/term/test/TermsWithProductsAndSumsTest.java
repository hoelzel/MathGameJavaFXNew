package de.lezleoh.mathgame.term.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Product;
import de.lezleoh.mathgame.term.Sum;
import de.lezleoh.mathgame.term.TermInt;

public class TermsWithProductsAndSumsTest {

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.term.TermWithMultipleOPerands#getString()}.
	 */
	@Test
	public final void testGetString() {
		// term under test: 11 + 21 * (31 + 32) + (-12)
		// index__________: 01234567890123456789012345
		Number summand11 = new Number(11);
		Number factor21 = new Number(21);
		Number summand31 = new Number(31);
		Number summand32 = new Number(32);
		Number summand12 = new Number(-12);
		Sum sum3 = new Sum();
		sum3.addOperand(summand31);
		sum3.addOperand(summand32);
		Product product2 = new Product();
		product2.addOperand(factor21);
		product2.addOperand(sum3);
		Sum sum1 = new Sum();
		sum1.addOperand(summand11);
		sum1.addOperand(product2);
		sum1.addOperand(summand12);
		// System.out.println(sum1.getString());
		assertEquals("11 + 21 * (31 + 32) + (-12)", sum1.getString());
	}

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.term.TermWithMultipleOPerands#getString()}.
	 */
	@Test
	public final void testGetHitPositions() {
		// term under test: 11 + 21 * (31 + 32) + (-12)
		// index__________: 012345678901234567890123456
		TermInt term = getStructuredTerm();

		ArrayList<Integer> hitPositionsExpected = new ArrayList<Integer>();

		hitPositionsExpected.add(new Integer(14));
		hitPositionsExpected.add(new Integer(20));

		assertEquals(hitPositionsExpected, term.getHitPositions());
	}

	@Test
	public final void testSimplifyOneStep1Time() {
		// term under test: 11 + 21 * (31 + 32) + (-12)
		// index__________: 012345678901234567890123456
		TermInt term = getStructuredTerm();
		term.simplifyOneStep();

		String expected = "(-1) + 21 * 63";
		System.out.println("testSimplifyOneStep1Time");
		System.out.println("expected: " + expected);
		System.out.println("actual: " + term.getString());
		System.out.println();
		assertEquals(expected, term.getString());
	}

	@Test
	public final void testSimplifyOneStep2Times() {
		// term under test: 11 + 21 * (31 + 32) + (-12)
		// index__________: 012345678901234567890123456
		TermInt term = getStructuredTerm();
		term.simplifyOneStep();
		term.simplifyOneStep();

		String expected = "(-1) + 1323";
		System.out.println("testSimplifyOneStep2Times");
		System.out.println("expected: " + expected);
		System.out.println("actual: " + term.getString());
		System.out.println();
		assertEquals(expected, term.getString());
	}
	@Test
	public final void testSimplifyOneStepProduct() {
		//term under test: 11 * (12 * 13)
		Number factor11 = new Number(11);
		Number factor12 = new Number(12);
		Number factor13 = new Number(13);
		Product innerProduct = new Product();
		innerProduct.addOperand(factor12);
		innerProduct.addOperand(factor13);
		Product outerProduct =  new Product();
		outerProduct.addOperand(factor11);
		outerProduct.addOperand(innerProduct);
		
		outerProduct.simplifyOneStep();
		
		String expected ="1716";
		System.out.println("testSimplifyOneStepProduct");
		System.out.println("expected: " + expected);
		System.out.println("actual: " + outerProduct.getString());
		System.out.println();
		assertEquals(expected, outerProduct.getString());
	}

	private TermInt getStructuredTerm() {
		// term under test: 11 + 21 * (31 + 32) + (-12)
		// index__________: 012345678901234567890123456
		Number summand11 = new Number(11);
		Number factor21 = new Number(21);
		Number summand31 = new Number(31);
		Number summand32 = new Number(32);
		Number summand12 = new Number(-12);
		Sum sum3 = new Sum();
		sum3.addOperand(summand31);
		sum3.addOperand(summand32);
		Product product2 = new Product();
		product2.addOperand(factor21);
		product2.addOperand(sum3);
		Sum sum1 = new Sum();
		sum1.addOperand(summand11);
		sum1.addOperand(product2);
		sum1.addOperand(summand12);
		return sum1;

	}
}
