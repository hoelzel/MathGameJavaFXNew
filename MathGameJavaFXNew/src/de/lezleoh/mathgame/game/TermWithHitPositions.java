package de.lezleoh.mathgame.game;

import java.util.Set;

import de.lezleoh.mathgame.term.TermInt;

public class TermWithHitPositions {
	TermInt term;
	Set<Integer> hitPositions;
	
	public TermWithHitPositions(TermInt term, Set<Integer> hitPositions) {
		super();
		this.term = term;
		this.hitPositions = hitPositions;
	}

	public TermInt getTerm() {
		return term;
	}

	public Set<Integer> getHitPositions() {
		return hitPositions;
	}

	@Override
	public String toString() {
		return "TermWithHitPositions [term=" + term + ", hitPositions=" + hitPositions + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TermWithHitPositions other = (TermWithHitPositions) obj;
		if (hitPositions == null) {
			if (other.hitPositions != null)
				return false;
		} else if (!hitPositions.equals(other.hitPositions))
			return false;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}

	

	
	

	
}
