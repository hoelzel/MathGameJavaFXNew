package de.lezleoh.mathgame.term;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Number implements TermInt {
	private Integer value;

	public Number(Integer value) {
		this.value = value;
	}

	public Number(int value) {
		this(new Integer(value));
	}

	@Override
	public Set<Integer> getPossibleHitPositions() {
		return new HashSet<Integer>();
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

	@Override
	public Boolean isNumber() {
		return true;
	}

	@Override
	public TermInt copy() {
		return new Number(value);
	}
}
