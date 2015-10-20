package de.lezleoh.mathgame.term;

public class Sum extends TermWithMultipleOperands {

	public Sum() {
		super((a, b) -> a + b, () -> "+", new Integer(0));
	}

	@Override
	public TypeOfTerm getTypeOfTerm() {
		return TypeOfTerm.SUM;
	}

}
