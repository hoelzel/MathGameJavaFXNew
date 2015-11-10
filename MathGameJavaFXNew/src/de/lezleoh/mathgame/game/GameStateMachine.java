package de.lezleoh.mathgame.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.lezleoh.mathgame.term.TermInt;

public class GameStateMachine {

	private TermInt term;
	private Set<Integer> hitPositions;
	private Set<Integer> wrongHitPositions;
	private List<TermWithHitPositions> history;
	private double solutionDegree;
	private String faultDescription = "kein Fehler";

	private static int state;
	private static final int SET_HIT_POSITIONS = 0;
	private static final int EVALUATE = 1;
	private static final int TERM_SOLVED = 2;

	public GameStateMachine(TermInt term) {
		this.term = term;
		if (term.isNumber()) {
			state = TERM_SOLVED;
		} else {
			state = SET_HIT_POSITIONS;
		}
		this.hitPositions = new HashSet<Integer>();
		this.wrongHitPositions = new HashSet<Integer>();
		this.history = new ArrayList<TermWithHitPositions>();
	}

	public void switchState(int hitPosition) {
		switch (state) {
		case SET_HIT_POSITIONS:
			if (hitPositionInRange(hitPosition)) {
				invertIsElementOfHitPositions(hitPosition);
			} else {
				state = EVALUATE;
				switchState(hitPosition);
			}
			break;
		case EVALUATE:
			history.add(new TermWithHitPositions(term.copy(), new HashSet<Integer>(hitPositions)));
			calculateWrongHitPositions();
			calculateSolutionDegree();

			if (solutionDegree == 1) {
				faultDescription = "kein Fehler";
				hitPositions.clear();
				term.simplifyOneStep();
			} else if (wrongHitPositions.isEmpty()) {
				faultDescription = "nicht alle erwischt";
			} else {
				faultDescription = "falsches erwischt";
			}
			if (term.isNumber()) {
				state = TERM_SOLVED;
			} else {
				state = SET_HIT_POSITIONS;
			}
			break;
		case TERM_SOLVED:
			;
			break;
		}
	}

	private void invertIsElementOfHitPositions(int hitPosition) {
		if (hitPositions.contains(hitPosition)) {
			hitPositions.remove(hitPosition);
		} else {
			hitPositions.add(hitPosition);
		}

	}

	private boolean hitPositionInRange(int hitPosition) {
		return hitPosition < term.getLength() && hitPosition >= 0;
	}

	private void calculateWrongHitPositions() {
		wrongHitPositions.clear();
		for (int hitPosition : this.hitPositions) {
			if (!term.getPossibleHitPositions().contains(hitPosition)) {
				wrongHitPositions.add(hitPosition);
			}
		}
	}

	private void calculateSolutionDegree() {
		if (!wrongHitPositions.isEmpty()) {
			solutionDegree = 0;
		} else {
			Double numberOfHits = new Double(hitPositions.size());
			Double numberOfPossibleHits = new Double(term.getPossibleHitPositions().size());
			solutionDegree = numberOfHits / numberOfPossibleHits;
		}
	}

	public TermInt getTerm() {
		return term;
	}

	public Set<Integer> getHitPositions() {
		return hitPositions;
	}

	public Set<Integer> getWrongHitPositions() {
		return wrongHitPositions;
	}

	public List<TermWithHitPositions> getHistory() {
		return history;
	}

	public double getSolutionDegree() {
		return solutionDegree;
	}

	public String getFaultDescription() {
		return faultDescription;
	}

	public boolean isTermSolved() {
		return term.isNumber();
	}
}
