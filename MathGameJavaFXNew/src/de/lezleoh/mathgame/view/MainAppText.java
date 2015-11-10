package de.lezleoh.mathgame.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.lezleoh.mathgame.game.GameStateMachine;
import de.lezleoh.mathgame.game.TermWithHitPositions;
import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Product;
import de.lezleoh.mathgame.term.Sum;
import de.lezleoh.mathgame.term.TermInt;

public class MainAppText {

	public static void main(String[] args) {

		GameStateMachine gameStateMachine = new GameStateMachine(getStructuredTerm());
		while (true) {

			System.out.println("NächsteRunde-------------------------------------------");
			System.out.print("HitPositions: ");
			System.out.println(gameStateMachine.getHitPositions().toString());

			System.out.print("WrongHitPositions: ");
			System.out.println(gameStateMachine.getWrongHitPositions().toString());

			System.out.print("Lösungsgrad: ");
			System.out.print(gameStateMachine.getSolutionDegree());
			System.out.print(" Fehlerbeschreibung: ");
			System.out.println(gameStateMachine.getFaultDescription());
			System.out.println();

			for (TermWithHitPositions termWithHitPositions : gameStateMachine.getHistory()) {
				System.out.print("Rechnung_____: ");
				System.out.println(termWithHitPositions.getTerm());

			}
			System.out.print("AktuellerTerm: ");
			System.out.println(gameStateMachine.getTerm().toString());
			System.out.println("Index________: 0123456789012345678901234567890");

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String input = br.readLine();
				int hitPosition = Integer.parseInt(input);
				gameStateMachine.switchState(hitPosition);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
			System.out.println();

		}

	}

	private static TermInt getStructuredTerm() {
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
