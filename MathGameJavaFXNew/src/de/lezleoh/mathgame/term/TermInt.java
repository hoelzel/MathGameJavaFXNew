package de.lezleoh.mathgame.term;

import java.util.ArrayList;

public interface TermInt {
	ArrayList<Integer> getHitPositions();
	Integer getValue();
	Integer getLength();
	String getString();
	TermInt simplifyOneStep();
	TypeOfTerm getTypeOfTerm();
}