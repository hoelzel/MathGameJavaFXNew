package de.lezleoh.mathgame.term;

public class Product extends TermWithMultipleOperands {

	public Product() {
		super((a, b) -> a * b, () -> "*", new Integer(1));
	}

	@Override
	public TypeOfTerm getTypeOfTerm() {
		return TypeOfTerm.PRODUCT;
	}

}