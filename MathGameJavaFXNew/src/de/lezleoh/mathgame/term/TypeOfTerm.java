package de.lezleoh.mathgame.term;


public enum TypeOfTerm {
	NUMBER(0), SUM(1), DIFFERENCE(1), PRODUCT(2), QUOTIENT(2), POWER(3);

	private int operatorLevel;

	TypeOfTerm(int operatorLevel) {
		this.operatorLevel = operatorLevel;
	}

	public int getOperatorLevel() {
		return operatorLevel;
	}

	public boolean hasGreaterOperatorLevel(TypeOfTerm typeOfTerm) {
		return this.operatorLevel > typeOfTerm.operatorLevel;
	}

	public boolean hasEqualOperatorLevel(TypeOfTerm typeOfTerm) {
		return this.operatorLevel == typeOfTerm.operatorLevel;
	}

	public boolean isNumber() {
		return this.getOperatorLevel() == NUMBER.getOperatorLevel();
	}

}