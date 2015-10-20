package de.lezleoh.mathgame.term;

import java.util.ArrayList;

public class Number implements TermInt {
	private Integer value;

	public Number(Integer value) {
		this.value = value;
	}

	public Number(int value) {
		this(new Integer(value));
	}

	@Override
	public ArrayList<Integer> getHitPositions() {
		return new ArrayList<Integer>();
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer getLength() {
		return getString().length();
	}

	@Override
	public String getString() {
		if (value >= 0) {
			return value.toString();
		} else {
			return "(" + value.toString() + ")";
		}
	}

	@Override
	public TypeOfTerm getTypeOfTerm() {
		return TypeOfTerm.NUMBER;
	}

	@Override
	public TermInt simplifyOneStep() {
		return this;
	}
}
