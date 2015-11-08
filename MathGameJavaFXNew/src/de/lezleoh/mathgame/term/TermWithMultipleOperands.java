package de.lezleoh.mathgame.term;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class TermWithMultipleOperands implements TermInt {
	protected ArrayList<TermInt> operands;
	private IntOperation operation;
	private IntOperationSign sign;
	private Integer neutralElement;

	public TermWithMultipleOperands(IntOperation intOperation, IntOperationSign intOperationSign,
			Integer neutralElement) {
		operands = new ArrayList<TermInt>();
		this.operation = intOperation;
		this.sign = intOperationSign;
		this.neutralElement = neutralElement;
	}

	public void addOperand(TermInt operand) {
		operands.add(operand);
	}

	@Override
	public Set<Integer> getPossibleHitPositions() {
		Set<Integer> hitPositions = new HashSet<Integer>();
		int actualIndex = 0;
		Iterator<TermInt> iterator = operands.iterator();

		while (iterator.hasNext()) {
			TermInt operand = iterator.next();
			String shiftString = "";
			int shift = 0;
			if (operand.getTypeOfTerm().isNumber() || this.hasEqualOperatorLevel(operand)) {
				if (actualIndex > 0) {
					shiftString = " " + sign.getSign();
					shift = shiftString.length();
					hitPositions.add(actualIndex - shift);
				}
			}
			ArrayList<Integer> relativeHitPositions = new ArrayList<Integer>();
			relativeHitPositions.addAll(operand.getPossibleHitPositions());

			if (this.hasGreaterOperatorLevel(operand)) {
				shiftString = "(";
				shift = shiftString.length();
			} else {
				shift = 0;
			}
			hitPositions.addAll(shiftToAbsoluteHitPositions(actualIndex + shift, relativeHitPositions));

			shiftString = " " + sign.getSign() + " ";
			shift = shiftString.length();
			actualIndex = actualIndex + operand.getString().length() + shift;
		}
		return hitPositions;

	}

	private ArrayList<Integer> shiftToAbsoluteHitPositions(Integer shift, ArrayList<Integer> relativeHitPositions) {
		ArrayList<Integer> absoluteHitPositions = new ArrayList<Integer>();
		for (Integer position : relativeHitPositions) {
			absoluteHitPositions.add(position + shift);
		}
		return absoluteHitPositions;
	}

	private boolean isNumber(TermInt term) {
		return term.getTypeOfTerm().isNumber();
	}

	@Override
	public Integer getValue() {
		Integer value = neutralElement;
		for (TermInt operand : operands) {
			value = operation.executeWith(value, operand.getValue());
		}
		return value;
	}

	@Override
	public Integer getLength() {
		return getString().length();
	}

	@Override
	public String getString() {
		String resultString = "";
		Iterator<TermInt> iterator = operands.iterator();
		while (iterator.hasNext()) {
			TermInt operand = iterator.next();
			if (operand.getTypeOfTerm().isNumber()) {
				if (operand.getValue() < 0) {
					resultString = resultString + "(" + operand.getValue().toString() + ")";
				} else {
					resultString = resultString + operand.getValue().toString();
				}
			} else {
				if (this.hasGreaterOperatorLevel(operand)) {
					resultString = resultString + "(" + operand.getString() + ")";
				} else {
					resultString = resultString + operand.getString();
				}
			}
			if (iterator.hasNext()) {
				resultString = resultString + " " + sign.getSign() + " ";
			}
		}
		return resultString;
	}

	@Override
	public String toString() {
		return getString();
	}

	@Override
	public TermInt simplifyOneStep() {
		Integer value = neutralElement;
		ArrayList<TermInt> newOperands = new ArrayList<TermInt>();

		for (TermInt operand : operands) {
			if (isNumber(operand) || this.hasEqualOperatorLevel(operand)) {
				value = operation.executeWith(value, operand.getValue());
			} else {
				operand.simplifyOneStep();
				if (operand.isNumber()) {
					operand = new Number(operand.getValue());
				}
				newOperands.add(operand);
			}
		}
		newOperands.add(0, new Number(value));
		operands = newOperands;
		if (isNumber()) {
			return new Number(this.getValue());
		} else {
			return this;
		}
	}

	@Override
	public Boolean isNumber() {
		return operands.size() == 1 && isNumber(operands.get(0));
	}

	private boolean hasGreaterOperatorLevel(TermInt term) {

		return this.getTypeOfTerm().hasGreaterOperatorLevel(term.getTypeOfTerm());

	}

	private boolean hasEqualOperatorLevel(TermInt term) {

		return this.getTypeOfTerm().hasEqualOperatorLevel(term.getTypeOfTerm());

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operands == null) ? 0 : operands.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof TermInt) {

			TermInt other = (TermInt) obj;

			return other.getString().equals(getString());
		}
		return false;
	}
}
