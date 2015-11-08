/**
 * 
 */
package de.lezleoh.mathgame.term.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Product;

/**
 * @author hoel
 *
 */
public class ProductTest {

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.term.Product#getPossibleHitPositions()}.
	 */
	@Test
	public final void testGetHitPositionsMonad() {
		// term under test: 1
		// index__________: 0
		Number number = new Number(1);
		Product product = new Product();
		product.addOperand(number);
		assertTrue(product.getPossibleHitPositions().isEmpty());

	}

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.term.Product#getPossibleHitPositions()}.
	 */
	@Test
	public final void testGetHitPositionsSimpleProduct() {

		// term under test: 11 * 12 * 13
		// index__________: 01234567890123
		Number factor11 = new Number(11);
		Number factor12 = new Number(12);
		Number factor13 = new Number(13);
		Product product1 = new Product();
		product1.addOperand(factor11);
		product1.addOperand(factor12);
		product1.addOperand(factor13);

		Set<Integer> hitPositionsExpected = new HashSet<Integer>();
		hitPositionsExpected.add(new Integer(3));
		hitPositionsExpected.add(new Integer(8));

		assertEquals(hitPositionsExpected, product1.getPossibleHitPositions());
	}

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.term.Product#getPossibleHitPositions()}.
	 */
	@Test
	public final void testGetHitPositionsStructuredProduct() {

		// term under test: 11 * 12 * (21 * 22) * 13
		// index__________: 0123456789_0123456_789012345
		Number factor11 = new Number(11);
		Number factor12 = new Number(12);
		Number factor13 = new Number(13);
		Number factor21 = new Number(21);
		Number factor22 = new Number(22);
		Product product1 = new Product();
		Product product2 = new Product();
		product1.addOperand(factor11);
		product1.addOperand(factor12);
		product1.addOperand(product2);
		product1.addOperand(factor13);
		product2.addOperand(factor21);
		product2.addOperand(factor22);

		Set<Integer> hitPositionsExpected = new HashSet<Integer>();

		hitPositionsExpected.add(new Integer(3));
		hitPositionsExpected.add(new Integer(8));
		hitPositionsExpected.add(new Integer(13));
		hitPositionsExpected.add(new Integer(18));
		assertEquals(hitPositionsExpected, product1.getPossibleHitPositions());

	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Product#getValue()}.
	 */
	@Test
	public final void testGetValue() {
		Number factor1 = new Number(1);
		Number factor2 = new Number(2);
		Product product = new Product();
		product.addOperand(factor1);
		product.addOperand(factor2);
		assertEquals(new Integer(2), product.getValue());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Product#getValue()}.
	 */
	@Test
	public final void testGetValueProductWithZero() {
		Number factor1 = new Number(0);
		Number factor2 = new Number(1);
		Product product = new Product();
		product.addOperand(factor1);
		product.addOperand(factor2);
		assertEquals(new Integer(0), product.getValue());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Product#getLength()}.
	 */
	@Test
	public final void testGetLength() {
		Number factor1 = new Number(1);
		Number factor2 = new Number(2);
		Number factor3 = new Number(3);
		Product product = new Product();
		product.addOperand(factor1);
		product.addOperand(factor2);
		product.addOperand(factor3);
		assertEquals("1 * 2 * 3".length(), product.getLength().intValue());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Product#getString()}.
	 */
	@Test
	public final void testGetString() {
		Number factor1 = new Number(1);
		Number factor2 = new Number(2);
		Number factor3 = new Number(3);
		Product product = new Product();
		product.addOperand(factor1);
		product.addOperand(factor2);
		product.addOperand(factor3);
		assertEquals("1 * 2 * 3", product.getString());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Product#getString()}.
	 */
	@Test
	public final void testGetStringStructuredProduct() {
		// term under test: 11 * 12 * (21 * 22) * 13
		// index__________: 0123456789_0123456_789012345
		Number factor11 = new Number(11);
		Number factor12 = new Number(12);
		Number factor13 = new Number(13);
		Number factor21 = new Number(21);
		Number factor22 = new Number(22);
		Product product1 = new Product();
		Product product2 = new Product();
		product1.addOperand(factor11);
		product1.addOperand(factor12);
		product1.addOperand(product2);
		product1.addOperand(factor13);
		product2.addOperand(factor21);
		product2.addOperand(factor22);
		System.out.println(product1.getString());
		assertEquals("11 * 12 * 21 * 22 * 13", product1.getString());
	}

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.term.Product#simplifyOneStep()}.
	 */
	@Test
	public final void testSimplifyOneStepMonad() {
		// term under test: 1
		Number number = new Number(1);
		Product product = new Product();
		product.addOperand(number);

		product.simplifyOneStep();

		assertEquals("1", product.getString());
	}

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.term.Product#simplifyOneStep()}.
	 */
	@Test
	public final void testSimplifyOneStepSimpleProduct() {
		// term under test: 11 * 12 * 13
		Number factor11 = new Number(11);
		Number factor12 = new Number(12);
		Number factor13 = new Number(13);
		Product product = new Product();
		product.addOperand(factor11);
		product.addOperand(factor12);
		product.addOperand(factor13);

		product.simplifyOneStep();

		assertEquals("1716", product.getString());
	}

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.term.Product#simplifyOneStep()}.
	 */
	@Test
	public final void testSimplifyOneStepStructuredProduct() {
		// term under test: 11 * 12 * (21 * 22) * 13
		Number factor11 = new Number(11);
		Number factor12 = new Number(12);
		Number factor13 = new Number(13);
		Number factor21 = new Number(21);
		Number factor22 = new Number(22);
		Product product1 = new Product();
		Product product2 = new Product();
		product1.addOperand(factor11);
		product1.addOperand(factor12);
		product1.addOperand(product2);
		product1.addOperand(factor13);
		product2.addOperand(factor21);
		product2.addOperand(factor22);

		product1.simplifyOneStep();

		assertEquals("792792", product1.getString());
	}

}
