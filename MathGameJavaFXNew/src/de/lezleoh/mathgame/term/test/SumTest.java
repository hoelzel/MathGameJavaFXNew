/**
 * 
 */
package de.lezleoh.mathgame.term.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Sum;

/**
 * @author hoel
 *
 */
public class SumTest {

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#getHitPositions()}.
	 */
	@Test
	public final void testGetHitPositionsMonad() {
		// term under test: 1
		Number number = new Number(1);
		Sum sum = new Sum();
		sum.addOperand(number);
		assertTrue(sum.getHitPositions().isEmpty());

	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#getHitPositions()}.
	 */
	@Test
	public final void testGetHitPositionsSimpleSum() {

		// term under test: 11 + 12 + 13
		// index__________: 012345678901
		Number summand11 = new Number(11);
		Number summand12 = new Number(12);
		Number summand13 = new Number(13);
		Sum sum1 = new Sum();
		sum1.addOperand(summand11);
		sum1.addOperand(summand12);
		sum1.addOperand(summand13);

		ArrayList<Integer> hitPositionsExpected = new ArrayList<Integer>();
		hitPositionsExpected.add(new Integer(3));
		hitPositionsExpected.add(new Integer(8));

		assertEquals(hitPositionsExpected, sum1.getHitPositions());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#getHitPositions()}.
	 */
	@Test
	public final void testGetHitPositionsStructuredSum() {

		// term under test: 11 + 12 + (21 + 22) + 13
		// index__________: 0123456789_0123456_789012345
		Number summand11 = new Number(11);
		Number summand12 = new Number(12);
		Number summand13 = new Number(13);
		Number summand21 = new Number(21);
		Number summand22 = new Number(22);
		Sum sum1 = new Sum();
		Sum sum2 = new Sum();
		sum1.addOperand(summand11);
		sum1.addOperand(summand12);
		sum1.addOperand(sum2);
		sum1.addOperand(summand13);
		sum2.addOperand(summand21);
		sum2.addOperand(summand22);

		ArrayList<Integer> hitPositionsExpected = new ArrayList<Integer>();

		hitPositionsExpected.add(new Integer(3));
		hitPositionsExpected.add(new Integer(8));
		hitPositionsExpected.add(new Integer(13));
		hitPositionsExpected.add(new Integer(18));
		assertEquals(hitPositionsExpected, sum1.getHitPositions());

	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#getValue()}.
	 */
	@Test
	public final void testGetValue() {
		Number summand1 = new Number(1);
		Number summand2 = new Number(2);
		Sum sum = new Sum();
		sum.addOperand(summand1);
		sum.addOperand(summand2);
		assertEquals(new Integer(3), sum.getValue());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#getLength()}.
	 */
	@Test
	public final void testGetLength() {
		Number summand1 = new Number(1);
		Number summand2 = new Number(2);
		Number summand3 = new Number(3);
		Sum sum = new Sum();
		sum.addOperand(summand1);
		sum.addOperand(summand2);
		sum.addOperand(summand3);
		assertEquals("1 + 2 + 3".length(), sum.getLength().intValue());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#getString()}.
	 */
	@Test
	public final void testGetString() {
		Number summand1 = new Number(1);
		Number summand2 = new Number(2);
		Number summand3 = new Number(3);
		Sum sum = new Sum();
		sum.addOperand(summand1);
		sum.addOperand(summand2);
		sum.addOperand(summand3);
		assertEquals("1 + 2 + 3", sum.getString());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#simplifyOneStep()}.
	 */
	@Test
	public final void testSimplifyOneStepMonad() {
		// term under test (1)
		Number number = new Number(1);
		Sum sum = new Sum();
		sum.addOperand(number);
		
		sum.simplifyOneStep();
		
		assertEquals("1", sum.getString());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#simplifyOneStep()}.
	 */
	@Test
	public final void testSimplifyOneStepSimpleSum() {
		// term under test: 11 + 12 + 13
		Number summand11 = new Number(11);
		Number summand12 = new Number(12);
		Number summand13 = new Number(13);
		Sum sum = new Sum();
		sum.addOperand(summand11);
		sum.addOperand(summand12);
		sum.addOperand(summand13);

		sum.simplifyOneStep();
		
		assertEquals("36", sum.getString());
	}

	/**
	 * Test method for {@link de.lezleoh.mathgame.term.Sum#simplifyOneStep()}.
	 */
	@Test
	public final void testSimplifyOneStepStructuredSum() {
		// term under test: 11 + 12 + (21 + 22) + 13
		Number summand11 = new Number(11);
		Number summand12 = new Number(12);
		Number summand13 = new Number(13);
		Number summand21 = new Number(21);
		Number summand22 = new Number(22);
		Sum sum1 = new Sum();
		Sum sum2 = new Sum();
		sum1.addOperand(summand11);
		sum1.addOperand(summand12);
		sum1.addOperand(sum2);
		sum1.addOperand(summand13);
		sum2.addOperand(summand21);
		sum2.addOperand(summand22);
		
		sum1.simplifyOneStep();
		
		assertEquals("79", sum1.getString());
	}

}
