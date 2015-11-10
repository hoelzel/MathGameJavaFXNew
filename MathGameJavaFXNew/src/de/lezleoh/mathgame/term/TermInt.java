package de.lezleoh.mathgame.term;

import java.util.Set;

public interface TermInt {
	Set<Integer> getPossibleHitPositions();
	Set<Integer> getPossibleHitPositionsBasic();
	Integer getValue();
	Integer getLength();
	String getString();
	TermInt simplifyOneStep();
	TypeOfTerm getTypeOfTerm();
	Boolean isNumber();
	TermInt copy();
}