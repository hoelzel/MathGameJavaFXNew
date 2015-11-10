package de.lezleoh.mathgame.game.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.lezleoh.mathgame.game.GameStateMachine;
import de.lezleoh.mathgame.game.TermWithHitPositions;
import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Sum;
import de.lezleoh.mathgame.term.TermInt;

public class GameStateMachineTest {

	@Test
	public final void testSwitchWrongHitPosition() {
		// term under test: 11 + 12 + 13
		// index__________: 012345678901
		TermInt sum1 = constructSum();

		GameStateMachine gameStateMachine = new GameStateMachine(sum1);
		gameStateMachine.switchState(0);
		gameStateMachine.switchState(12);
		Set<Integer> expectedWrongHitPositions = new HashSet<Integer>();
		expectedWrongHitPositions.add(0);

		assertEquals(expectedWrongHitPositions, gameStateMachine.getWrongHitPositions());
	}

	@Test
	public final void testSwitchSelectHitPositionTwice() {
		// term under test: 11 + 12 + 13
		// index__________: 012345678901
		TermInt sum1 = constructSum();

		GameStateMachine gameStateMachine = new GameStateMachine(sum1);
		gameStateMachine.switchState(0);
		gameStateMachine.switchState(0);
		Set<Integer> expectedHitPositions = new HashSet<Integer>();

		assertEquals(expectedHitPositions, gameStateMachine.getHitPositions());
	}

	@Test
	public final void testSwitchSolutionDegreeWrongHitPosition() {
		// term under test: 11 + 12 + 13
		// index__________: 012345678901
		TermInt sum1 = constructSum();

		GameStateMachine gameStateMachine = new GameStateMachine(sum1);
		gameStateMachine.switchState(0);
		gameStateMachine.switchState(12);
		Double expectedSolutionDegree = new Double(0);

		assertEquals(expectedSolutionDegree, (Double) gameStateMachine.getSolutionDegree());
	}

	@Test
	public final void testSwitchSolutionDegreeOneHitPositionCorrect() {
		// term under test: 11 + 12 + 13
		// index__________: 012345678901
		TermInt sum1 = constructSum();

		GameStateMachine gameStateMachine = new GameStateMachine(sum1);
		gameStateMachine.switchState(3);
		gameStateMachine.switchState(12);
		Double expectedSolutionDegree = new Double(0.5);

		assertEquals(expectedSolutionDegree, (Double) gameStateMachine.getSolutionDegree());
	}

	@Test
	public final void testSwitchSolutionDegreeAllHitPositionCorrect() {
		// term under test: 11 + 12 + 13
		// index__________: 012345678901
		TermInt sum1 = constructSum();

		GameStateMachine gameStateMachine = new GameStateMachine(sum1);
		gameStateMachine.switchState(3);
		gameStateMachine.switchState(8);
		gameStateMachine.switchState(12);
		Double expectedSolutionDegree = new Double(1);

		assertEquals(expectedSolutionDegree, (Double) gameStateMachine.getSolutionDegree());
	}

	@Test
	public final void testGetHistory() {
		// term under test: 11 + 12 + 13
		// index__________: 012345678901
		TermInt sum1 = constructSum();

		GameStateMachine gameStateMachine = new GameStateMachine(sum1);
		gameStateMachine.switchState(0);
		gameStateMachine.switchState(12);
		
		Set<Integer> hitPositions = new HashSet<Integer>();
		hitPositions.add(0);
		TermWithHitPositions termWithHitPositions = new TermWithHitPositions(sum1, hitPositions);
		List<TermWithHitPositions> listOfTermsWithHitPositions = new ArrayList<TermWithHitPositions>();
		listOfTermsWithHitPositions.add(termWithHitPositions);
		
		assertEquals(listOfTermsWithHitPositions, gameStateMachine.getHistory());
	}

	private TermInt constructSum() {
		// term under test: 11 + 12 + 13
		// index__________: 012345678901
		Number summand11 = new Number(11);
		Number summand12 = new Number(12);
		Number summand13 = new Number(13);
		Sum sum1 = new Sum();
		sum1.addOperand(summand11);
		sum1.addOperand(summand12);
		sum1.addOperand(summand13);
		return sum1;
	}

}
