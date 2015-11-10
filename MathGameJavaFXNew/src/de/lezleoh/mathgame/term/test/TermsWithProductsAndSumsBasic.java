package de.lezleoh.mathgame.term.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Sum;
import de.lezleoh.mathgame.term.TermInt;

public class TermsWithProductsAndSumsBasic {

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

		Set<Integer> hitPositionsExpected = new HashSet<Integer>();
		hitPositionsExpected.add(new Integer(3));
		hitPositionsExpected.add(new Integer(8));
		hitPositionsExpected.add(new Integer(13));
		hitPositionsExpected.add(new Integer(18));

		assertEquals(hitPositionsExpected, sum1.getPossibleHitPositionsBasic());

	}

	@Test
	public final void testSimplifyOneStepBasicStructuredSum() {

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

		Number summand1Expected = new Number(36);
		Number summand2Expected = new Number(43);
		Sum sumExpected = new Sum();
		sumExpected.addOperand(summand1Expected);
		sumExpected.addOperand(summand2Expected);
		TermInt sumActual = sum1.simplifyOneStepBasic();
		//System.out.println(sumActual.getString());
		
		assertEquals(sumExpected, sumActual);
	}

}
